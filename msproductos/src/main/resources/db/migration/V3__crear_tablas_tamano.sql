CREATE TABLE tamano (
    tamano_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tamano VARCHAR(255) NOT NULL
);
INSERT INTO tamano (nombre_tamano) VALUES ('Pequeño'), ('Mediano'), ('Grande');