CREATE TABLE cafeteria (
    cafeteria_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cafeteria VARCHAR(255) NOT NULL,
    comuna_id INT NOT NULL,
    CONSTRAINT fk_cafeteria_comuna FOREIGN KEY (comuna_id) REFERENCES comuna(comuna_id)
);
INSERT INTO cafeteria (nombre_cafeteria, comuna_id) VALUES
('Cafetería DUOC Maipú', 1),
('Cafetería DUOC Viña', 2),
('Cafetería DUOC Laja', 3);