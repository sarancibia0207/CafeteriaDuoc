CREATE TABLE tipos (
    tipos_id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    tipo_id INT NOT NULL,
    CONSTRAINT fk_tipos_producto FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
    CONSTRAINT fk_tipos_tipo FOREIGN KEY (tipo_id) REFERENCES tipo(tipo_id)
);
INSERT INTO tipos (producto_id, tipo_id) VALUES (1, 1), (2, 1), (3, 3), (4, 4), (5, 4), (6, 4);