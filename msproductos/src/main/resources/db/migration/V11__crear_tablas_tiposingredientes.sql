CREATE TABLE tiposIngrediente (
    tiposIngredientes_id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_ingrediente_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    CONSTRAINT fk_tiposIngrediente_tipo FOREIGN KEY (tipo_ingrediente_id) REFERENCES tipo_ingrediente(tipo_ingrediente_id),
    CONSTRAINT fk_tiposIngrediente_ingrediente FOREIGN KEY (ingrediente_id) REFERENCES ingrediente(ingrediente_id)
);
INSERT INTO tiposIngrediente (tipo_ingrediente_id, ingrediente_id) VALUES
(1, 1), (2, 2), (3, 3), (6, 4), (7, 5), (4, 6), (2, 7), (5, 8), (9, 9),
(11, 10), (10, 11), (8, 12), (12, 13), (1, 14), (5, 15), (12, 16), (6, 17), (11, 18), (2, 19);