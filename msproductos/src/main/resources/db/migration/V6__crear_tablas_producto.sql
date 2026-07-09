CREATE TABLE producto (
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    precio INT NOT NULL,
    stock INT NOT NULL DEFAULT 100
);
INSERT INTO producto (nombre, precio, stock) VALUES
('Cappucino', 1800, 50),
('Mocaccino', 1800, 45),
('Té', 1500, 60),
('Donut Frutilla', 1200, 40),
('Berlín', 1000, 35),
('Cheesecake', 2800, 25);