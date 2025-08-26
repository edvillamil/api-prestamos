package co.com.gestionprestamos.r2dbc.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("rol")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RolEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
}