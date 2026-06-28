CREATE TABLE metodos_pago (
    metodos_pago_id INT AUTO_INCREMENT PRIMARY KEY,
    metodo_pago_id INT NOT NULL,
    venta_id INT NOT NULL,
    CONSTRAINT fk_metodosPago_metodopago FOREIGN KEY (metodo_pago_id) REFERENCES Metodopago(metodopago_id),
    CONSTRAINT fk_metodosPago_venta FOREIGN KEY (venta_id) REFERENCES ventas(venta_id)
);
INSERT INTO metodos_pago (metodo_pago_id, venta_id) VALUES (1, 1), (2, 2), (3, 3);