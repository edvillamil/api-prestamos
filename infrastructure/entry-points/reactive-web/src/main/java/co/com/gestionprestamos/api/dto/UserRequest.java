package co.com.gestionprestamos.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserRequest {

    private String nombres;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private String telefono;
    private String correo_electronico;
    private BigDecimal salario_base;
}
