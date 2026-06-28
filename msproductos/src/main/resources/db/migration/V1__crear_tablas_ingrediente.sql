CREATE TABLE ingrediente (
    ingrediente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ingrediente VARCHAR(255) NOT NULL,
    cantidad_ingrediente DOUBLE NOT NULL
);
INSERT INTO ingrediente (nombre_ingrediente, cantidad_ingrediente) VALUES
('Leche Entera', 10.0), ('Azúcar Flor', 5.0), ('Café Espresso', 15.0), ('Chocolate', 12.0),
('Agua', 100.0), ('Bolsa de Té', 1.0), ('Azúcar', 8.0), ('Harina', 50.0), ('Huevos', 2.0),
('Mantequilla', 20.0), ('Levadura', 3.0), ('Frutilla', 10.0), ('Crema Pastelera', 25.0),
('Queso Cream', 30.0), ('Galletas Trituradas', 40.0), ('Crema', 15.0), ('Cacao en Polvo', 5.0),
('Aceite', 50.0), ('Mermelada', 20.0);