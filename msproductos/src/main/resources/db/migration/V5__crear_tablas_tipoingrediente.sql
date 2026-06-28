CREATE TABLE tipo_ingrediente (
    tipo_ingrediente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo_ingrediente VARCHAR(255) NOT NULL
);
INSERT INTO tipo_ingrediente (nombre_tipo_ingrediente) VALUES
('Lácteos'), ('Endulzantes'), ('Granos de café'), ('Bolsas de té'), ('Harina'), ('Chocolates'),
('Líquidos'), ('Frutas'), ('Huevos'), ('Levaduras'), ('Aceites y grasas'), ('Cremas y rellenos');