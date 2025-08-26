package co.com.gestionprestamos.api.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class UserResponse {
    UUID id;
    String name;
    String last_name;
    LocalDate birth_date;
    String address;
    String mobile_number;
    String email;
    BigDecimal base_salary;
}