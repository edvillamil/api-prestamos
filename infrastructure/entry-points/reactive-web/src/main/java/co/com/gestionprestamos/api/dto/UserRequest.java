package co.com.gestionprestamos.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserRequest {

    private String name;
    private String last_name;
    private LocalDate birth_date;
    private String address;
    private String mobile_number;
    private String email;
    private BigDecimal base_salary;
    private UUID rolId;
}
