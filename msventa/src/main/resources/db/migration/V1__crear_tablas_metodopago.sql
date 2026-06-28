CREATE TABLE Metodopago (
    metodopago_id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_metodo_pago VARCHAR(255) NOT NULL
);
INSERT INTO Metodopago (tipo_metodo_pago) VALUES ('Efectivo'), ('Tarjeta Débito'), ('Tarjeta Crédito');