package co.com.gestionprestamos.model.user.validation;

import co.com.gestionprestamos.model.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidation {

    private static final Pattern EMAIL_RX = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private UserValidation(){}

    public static List<String> validateForRegistration(User u){

        List<String> errors = new ArrayList<>();

        if (u.getNombres() == null || u.getNombres().trim().isEmpty()) errors.add("nombres: requerido");
        if (u.getApellidos() == null || u.getApellidos().trim().isEmpty()) errors.add("apellidos: requerido");
        if (u.getCorreoElectronico() == null || u.getCorreoElectronico().trim().isEmpty()) {
            errors.add("correo_electronico: requerido");
        } else if (!EMAIL_RX.matcher(u.getCorreoElectronico()).matches()) {
            errors.add("correo_electronico: formato inválido");
        }
        if (u.getSalarioBase() == null) {
            errors.add("salario_base: requerido");
        } else if (u.getSalarioBase().compareTo(BigDecimal.ZERO) < 0
                || u.getSalarioBase().compareTo(new BigDecimal("15000000")) > 0) {
            errors.add("salario_base: fuera de rango (0..15000000)");
        }
        return errors;
    }

}
