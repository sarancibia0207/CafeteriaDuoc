CREATE TABLE ventas (
    venta_id INT AUTO_INCREMENT PRIMARY KEY,
    total_venta DOUBLE NOT NULL,
    fecha_venta DATE NOT NULL,
    cliente_id INT NOT NULL,
    cafeteria_id INT NOT NULL
);
INSERT INTO ventas (total_venta, fecha_venta, cliente_id, cafeteria_id) VALUES 
(2800.0, '2026-05-08', 1, 1),
(1000.0, '2026-05-07', 2, 2),
(1200.0, '2026-05-06', 3, 3);