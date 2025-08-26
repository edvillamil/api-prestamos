package co.com.gestionprestamos.api.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class RolResponse {
    UUID id;
    String name;
    String description;
}
