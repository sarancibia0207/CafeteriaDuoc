CREATE TABLE ingredientes (
    ingredientes_id INT AUTO_INCREMENT PRIMARY KEY,
    ingrediente_id INT NOT NULL,
    producto_id INT NOT NULL,
    CONSTRAINT fk_ingredientes_ingrediente FOREIGN KEY (ingrediente_id) REFERENCES ingrediente(ingrediente_id),
    CONSTRAINT fk_ingredientes_producto FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
);
INSERT INTO ingredientes (ingrediente_id, producto_id) VALUES
(3, 1), (1, 1), (5, 1),
(3, 2), (1, 2), (4, 2), (5, 2),
(6, 3), (5, 3),
(8, 4), (9, 4), (11, 4), (12, 4), (7, 4),
(8, 5), (9, 5), (13, 5), (2, 5),
(14, 6), (15, 6), (10, 6), (19, 6);