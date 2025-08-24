package co.com.gestionprestamos.usecase.registeruser.exceptions;

import java.util.List;

public class RegistrationValidationException extends RuntimeException {
    private final List<String> details;
    public RegistrationValidationException(List<String> details) {
        super("Errores de validación");
        this.details = details;
    }
    public List<String> getDetails() { return details; }
}
