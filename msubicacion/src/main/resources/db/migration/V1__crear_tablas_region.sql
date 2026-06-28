CREATE TABLE region (
    region_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_region VARCHAR(255) NOT NULL
);
INSERT INTO region (nombre_region) VALUES ('Metropolitana'), ('Valparaíso'), ('Biobío');