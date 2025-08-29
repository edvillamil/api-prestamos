package co.com.gestionprestamos.model.user;
import co.com.gestionprestamos.model.rol.Rol;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    @With
    UUID id;
    String name;
    String lastName;
    LocalDate birthDate;
    String address;
    String mobileNumber;
    String email;
    BigDecimal baseSalary;
    Rol rol;
}
