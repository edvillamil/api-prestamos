package co.com.gestionprestamos.usecase.registeruser.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("El correo ya está registrado: " + email);
    }
}
