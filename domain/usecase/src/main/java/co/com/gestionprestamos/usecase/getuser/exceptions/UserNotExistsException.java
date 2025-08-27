package co.com.gestionprestamos.usecase.getuser.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String id) {
        super("El usuario con id %s no existe: " + id);
    }
}
