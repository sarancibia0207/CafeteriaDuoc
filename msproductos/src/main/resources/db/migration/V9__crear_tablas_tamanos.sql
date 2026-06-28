CREATE TABLE tamanos (
    tamanos_id INT AUTO_INCREMENT PRIMARY KEY,
    tamano_id INT NOT NULL,
    producto_id INT NOT NULL,
    CONSTRAINT fk_tamanos_tamano FOREIGN KEY (tamano_id) REFERENCES tamano(tamano_id),
    CONSTRAINT fk_tamanos_producto FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
);
INSERT INTO  tamanos (producto_id, tamano_id) VALUES (1, 2), (2, 2), (3, 2), (4, 1), (5, 2), (6, 3);