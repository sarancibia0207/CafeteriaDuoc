CREATE TABLE tipo (
    tipo_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(255) NOT NULL
);
INSERT INTO tipo (nombre_tipo) VALUES ('Café Caliente'), ('Bebida Fría'), ('Té'), ('Bollería');