
-- una sola vez en la BD:
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS rol (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  birth_date DATE,
  address VARCHAR(200),
  mobile_number VARCHAR(50),
  email VARCHAR(150) UNIQUE NOT NULL,
  base_salary NUMERIC(14,2) NOT NULL,
  rol_id UUID,
  CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- asegura tipo y default en la columna id
ALTER TABLE usuarios
  ALTER COLUMN id SET DATA TYPE uuid USING id::uuid,
  ALTER COLUMN id SET DEFAULT gen_random_uuid(),
  ALTER COLUMN id SET NOT NULL;


-- INSERT ROLES
INSERT INTO rol (name, description)
SELECT 'AGENTE', 'AGENTE'
WHERE NOT EXISTS (SELECT 1 FROM rol WHERE name = 'AGENTE');

-- Insertar ADMIN si no existe
INSERT INTO rol (name, description)
SELECT 'ADMIN', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM rol WHERE name = 'ADMIN');