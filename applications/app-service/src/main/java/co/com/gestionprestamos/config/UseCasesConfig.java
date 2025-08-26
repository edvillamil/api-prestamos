package co.com.gestionprestamos.config;

import co.com.gestionprestamos.model.rol.gateways.RolRepository;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.usecase.registeruser.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.gestionprestamos.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository, RolRepository rolRepository) {
        return new RegisterUserUseCase(userRepository, rolRepository);
    }
}
