CREATE TABLE paso_receta (
    paso_receta_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo_receta VARCHAR(255) NOT NULL,
    descripcion_paso VARCHAR(255) NOT NULL
);
INSERT INTO paso_receta (titulo_receta, descripcion_paso) VALUES
('Cappucino', 'Preparar un espresso; Calentar y espumar la leche; Verter la leche sobre el café; Agregar espuma de leche encima; Servir caliente.'),
('Mocaccino', 'Preparar un espresso; Agregar chocolate caliente o jarabe de chocolate; Calentar y espumar la leche; Verter la leche sobre el café; Decorar con cacao o crema si se desea.'),
('Té', 'Colocar agua hirviendo en una taza; Colocar bolsa de té junto al endulzante y revolver.'),
('Donut Frutilla', 'Preparar la masa de donut; Freír hasta dorar; Dejar enfriar; Agregar glaseado de frutilla; Decorar con chispas si se desea.'),
('Berlín', 'Preparar la masa; Freír hasta dorar; Dejar enfriar; Rellenar con crema pastelera o mermelada; Espolvorear azúcar flor.'),
('Cheesecake', 'Preparar base de galleta triturada; Mezclar queso crema con azúcar y huevos; Verter sobre la base; Hornear; Refrigerar; Decorar con salsa o fruta.');