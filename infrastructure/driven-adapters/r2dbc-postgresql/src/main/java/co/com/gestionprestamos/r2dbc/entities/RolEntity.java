package co.com.gestionprestamos.r2dbc.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("rol")
public class RolEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
}