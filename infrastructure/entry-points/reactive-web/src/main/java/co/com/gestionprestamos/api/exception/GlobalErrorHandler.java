package co.com.gestionprestamos.api.exception;

import co.com.gestionprestamos.usecase.getuser.exceptions.UserNotExistsException;
import co.com.gestionprestamos.usecase.registeruser.RegisterUserUseCase;
import co.com.gestionprestamos.usecase.registeruser.exceptions.DuplicateEmailException;
import co.com.gestionprestamos.usecase.registeruser.exceptions.RegistrationValidationException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Primary
@Order(-2)
@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Object body = ApiError.of("INTERNAL_ERROR", "Error interno del servidor", List.of());

        if (ex instanceof RegistrationValidationException ve) {
            status = HttpStatus.BAD_REQUEST;
            body = ApiError.of("VALIDATION_ERROR", ve.getMessage(), ve.getDetails());
        }

        if (ex instanceof DuplicateEmailException de) {
            status = HttpStatus.CONFLICT;
            body = ApiError.of("EMAIL_DUPLICATE", de.getMessage(), List.of("correo_electronico duplicado"));
        }

        if (ex instanceof UserNotExistsException de) {
            status = HttpStatus.NOT_FOUND;
            body = ApiError.of("USER_NOT_FOUND", de.getMessage(), List.of("el usuario no fue encontrado"));
        }

        byte[] bytes = Jsons.toJson(body).getBytes(StandardCharsets.UTF_8);
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @Value
    static class ApiError {
        String code;
        String message;
        List<String> details;

        static ApiError of(String code, String message, List<String> details) {
            return new ApiError(code, message, details);
        }
    }

    static class Jsons {
        static String toJson(Object o) {
            try {
                // Usa Jackson del classpath
                return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(o);
            } catch (Exception e) {
                return "{\"code\":\"SER\",\"message\":\"serialization error\"}";
            }
        }
    }
}