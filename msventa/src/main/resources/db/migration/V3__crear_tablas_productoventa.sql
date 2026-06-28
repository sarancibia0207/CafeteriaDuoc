CREATE TABLE producto_venta (
    producto_venta_id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    CONSTRAINT fk_productoVenta_venta FOREIGN KEY (venta_id) REFERENCES ventas(venta_id)
);
INSERT INTO producto_venta (producto_id, venta_id) VALUES (6, 1), (3, 2), (4, 3);