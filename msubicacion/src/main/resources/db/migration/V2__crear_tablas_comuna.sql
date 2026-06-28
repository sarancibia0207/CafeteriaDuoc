CREATE TABLE comuna (
    comuna_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_comuna VARCHAR(255) NOT NULL,
    region_id INT NOT NULL,
    CONSTRAINT fk_comuna_region FOREIGN KEY (region_id) REFERENCES region(region_id)
);
INSERT INTO comuna (nombre_comuna, region_id) VALUES ('Cerrillos', 1), ('Viña del Mar', 2), ('Laja', 3);