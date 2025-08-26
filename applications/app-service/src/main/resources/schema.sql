
-- una sola vez en la BD:
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS rol (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
  id UUID PRIMARY KEY,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE,
  direccion VARCHAR(200),
  telefono VARCHAR(50),
  correo_electronico VARCHAR(150) UNIQUE NOT NULL,
  salario_base NUMERIC(14,2) NOT NULL,
  rol_id UUID,
  CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- asegura tipo y default en la columna id
ALTER TABLE usuarios
  ALTER COLUMN id SET DATA TYPE uuid USING id::uuid,
  ALTER COLUMN id SET DEFAULT gen_random_uuid(),
  ALTER COLUMN id SET NOT NULL;
