package co.com.gestionprestamos.api;

import co.com.gestionprestamos.api.dto.UserRequest;
import co.com.gestionprestamos.api.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    produces = {"application/json"},
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "registerUser",
                    operation = @Operation(
                            operationId = "registerUser",
                            summary = "Register a new user",
                            description = "Create a user in the system with the information provided.",
                            tags = {"User"},
                            requestBody = @RequestBody(
                                    required = true,
                                    description = "User registration request",
                                    content = @Content(schema = @Schema(implementation = UserRequest.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User registered successfully",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/usuarios/{email}",
                    produces = {"application/json"},
                    method = RequestMethod.GET,
                    beanClass = Handler.class,
                    beanMethod = "getUser",
                    operation = @Operation(
                            operationId = "getUser",
                            summary = "Get user by email",
                            description = "Get user by email.",
                            tags = {"User"},
                            parameters = {
                                    @Parameter(
                                            name = "email",
                                            in = ParameterIn.PATH,
                                            description = "Email of the user to retrieve",
                                            required = true,
                                            schema = @Schema(type = "string")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "User found successfully",
                                            content = @Content(schema = @Schema(implementation = UserResponse.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404",
                                            description = "User not found"
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(POST("/api/v1/usuarios"), handler::registerUser)
                .andRoute(GET("/api/v1/usuarios/{email}"), handler::getUser);
    }
}
