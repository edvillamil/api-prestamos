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

        if (u.getName() == null || u.getName().trim().isEmpty()) errors.add("nombres: requerido");
        if (u.getLastName() == null || u.getLastName().trim().isEmpty()) errors.add("apellidos: requerido");
        if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            errors.add("correo_electronico: requerido");
        } else if (!EMAIL_RX.matcher(u.getEmail()).matches()) {
            errors.add("correo_electronico: formato inválido");
        }
        if (u.getBaseSalary() == null) {
            errors.add("salario_base: requerido");
        } else if (u.getBaseSalary().compareTo(BigDecimal.ZERO) < 0
                || u.getBaseSalary().compareTo(new BigDecimal("15000000")) > 0) {
            errors.add("salario_base: fuera de rango (0..15000000)");
        }
        return errors;
    }

}
