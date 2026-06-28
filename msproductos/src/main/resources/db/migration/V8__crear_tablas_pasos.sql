CREATE TABLE pasos (
    pasos_id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    pasoreceta_id INT NOT NULL,
    CONSTRAINT fk_pasos_producto FOREIGN KEY (producto_id) REFERENCES producto(producto_id),
    CONSTRAINT fk_pasos_pasoreceta FOREIGN KEY (pasoreceta_id) REFERENCES paso_receta(paso_receta_id)
);
INSERT INTO pasos (producto_id, pasoreceta_id) VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6);