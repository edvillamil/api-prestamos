package co.com.gestionprestamos.r2dbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table("usuarios")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private UUID id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String mobileNumber;
    private String email;
    private BigDecimal baseSalary;
    private UUID rolId;
}
