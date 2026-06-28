CREATE TABLE cliente (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(255) NOT NULL,
    rut_cliente VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    puntos_acumulables DOUBLE NOT NULL
);

INSERT INTO cliente (nombre_cliente, rut_cliente, fecha_nacimiento, puntos_acumulables) VALUES
('Juan Pérez', '12.345.678-9', '1990-05-15', 100.0),
('Pedro Pascal', '14.196.357-0', '2000-05-15', 90.0),
('Lola Loquita', '22.319.094-7', '2004-02-14', 80.0);