
INSERT INTO usuario (DTYPE, id, nombre, monedaV, email, password, descripcion, preguntasaprobadas, preguntascreadas, fechacreacion, fechaactualizacion) 
VALUES ('Chef', 103, 'admin', 100, 'admin@example.com', '1234', 'el boss', 5, 10, NOW(), NOW());

INSERT INTO pregunta (id, pregunta, dificultad, verificado, curiosidad, imagen, categoria_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(1,	'¿Cual es el ingrediente principal de la tortilla de patata?',	'Facil',	1,	'Increible',	'images/categorias/Española.jpg',	'Española',	NOW(),	NOW()),
(2,	'¿Cual es el ingredientes principal de la paella?',	'Facil',	1,	'wuauu',	'images/categorias/Española.jpg',	'Española',	NOW(),	NOW()),
(3, '¿Cuál es el ingrediente principal del guacamole?', 'Facil', 1, 'El guacamole se origina de México.', 'Mexicana.jpg', 'Mexicana', 103, NOW(), NOW()),
(4, '¿Qué es un risotto?', 'Media', 1, 'Un plato típico de la cocina italiana.', 'Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(5, '¿Qué tipo de carne se utiliza tradicionalmente para el sushi?', 'Dificil', 1, 'El sushi se elabora principalmente con pescado crudo.', 'Japonesa.jpg', 'Japonesa', 103, NOW(), NOW()),
(6, '¿Cuál es el ingrediente clave en la paella?', 'Media', 1, 'La paella es originaria de la Comunidad Valenciana en España.', 'Española.jpg', 'Española', 103, NOW(), NOW()),
(7, '¿Qué fruta es conocida por ser la base del guacamole?', 'Facil', 1, 'El aguacate es la base del guacamole.', 'Mexicana.jpg', 'Mexicana', 103, NOW(), NOW()),
(8, '¿Cuál es la pasta más famosa de Italia?', 'Facil', 1, 'Los espaguetis son una de las pastas más conocidas.', 'Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(9, '¿Qué bebida se suele servir con la comida china?', 'Facil', 1, 'El té es muy popular en la cocina china.', 'China.jpg', 'China', 103, NOW(), NOW()),
(10, '¿Cuál es el plato nacional de Japón?', 'Dificil', 1, 'El sushi es considerado el plato nacional de Japón.', 'Japonesa.jpg', 'Japonesa', 103, NOW(), NOW()),
(11, '¿Qué tipo de queso se utiliza en la pizza?', 'Facil', 1, 'El queso mozzarella es el más común en la pizza.', 'Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(12, '¿Qué es un ceviche?', 'Media', 1, 'El ceviche es un plato hecho a base de pescado crudo marinado en limón.', 'Peruana.jpg', 'Peruana', 103, NOW(), NOW());



INSERT INTO respuesta (id, respuesta, correcta, pregunta_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(1, 'Patata', 1, 1, 103, NOW(), NOW()),
(2, 'Pan', 0, 1, 103, NOW(), NOW()),
(3, 'Arroz', 0, 1, 103, NOW(), NOW()),
(4, 'Pasta', 0, 1, 103, NOW(), NOW()),
(5, 'Arroz', 1, 2, 103, NOW(), NOW()),
(6, 'Huevo', 0, 2, 103, NOW(), NOW()),
(7, 'Pescado', 0, 2, 103, NOW(), NOW()),
(8, 'Cebolla', 0, 2, 103, NOW(), NOW()),
(9, 'Aguacate', 1, 3, 103, NOW(), NOW()),
(10, 'Tomate', 0, 3, 103, NOW(), NOW()),
(11, 'Cebolla', 0, 3, 103, NOW(), NOW()),
(12, 'Pimiento', 0, 3, 103, NOW(), NOW()),
(13, 'Arroz', 1, 4, 103, NOW(), NOW()),
(14, 'Pasta', 0, 4, 103, NOW(), NOW()),
(15, 'Quinoa', 0, 4, 103, NOW(), NOW()),
(16, 'Lentejas', 0, 4, 103, NOW(), NOW()),
(17, 'Salmón', 1, 5, 103, NOW(), NOW()),
(18, 'Atún', 0, 5, 103, NOW(), NOW()),
(19, 'Camarón', 0, 5, 103, NOW(), NOW()),
(20, 'Pulpo', 0, 5, 103, NOW(), NOW()),
(21, 'Arroz', 1, 6, 103, NOW(), NOW()),
(22, 'Pasta', 0, 6, 103, NOW(), NOW()),
(23, 'Pan', 0, 6, 103, NOW(), NOW()),
(24, 'Patata', 0, 6, 103, NOW(), NOW()),
(25, 'Aguacate', 1, 7, 103, NOW(), NOW()),
(26, 'Tomate', 0, 7, 103, NOW(), NOW()),
(27, 'Cebolla', 0, 7, 103, NOW(), NOW()),
(28, 'Pimiento', 0, 7, 103, NOW(), NOW()),
(29, 'Espaguetis', 1, 8, 103, NOW(), NOW()),
(30, 'Fusilli', 0, 8, 103, NOW(), NOW()),
(31, 'Macarrones', 0, 8, 103, NOW(), NOW()),
(32, 'Penne', 0, 8, 103, NOW(), NOW()),
(33, 'Té', 1, 9, 103, NOW(), NOW()),
(34, 'Cerveza', 0, 9, 103, NOW(), NOW()),
(35, 'Vino', 0, 9, 103, NOW(), NOW()),
(36, 'Sake', 0, 9, 103, NOW(), NOW()),
(37, 'Sushi', 1, 10, 103, NOW(), NOW()),
(38, 'Ramen', 0, 10, 103, NOW(), NOW()),
(39, 'Tempura', 0, 10, 103, NOW(), NOW()),
(40, 'Takoyaki', 0, 10, 103, NOW(), NOW()),
(41, 'Mozzarella', 1, 11, 103, NOW(), NOW()),
(42, 'Cheddar', 0, 11, 103, NOW(), NOW()),
(43, 'Parmesano', 0, 11, 103, NOW(), NOW()),
(44, 'Gouda', 0, 11, 103, NOW(), NOW()),
(45, 'Pescado', 1, 12, 103, NOW(), NOW()),
(46, 'Carne', 0, 12, 103, NOW(), NOW()),
(47, 'Vegetales', 0, 12, 103, NOW(), NOW()),
(48, 'Pollo', 0, 12, 103, NOW(), NOW());


INSERT INTO pregunta (id, pregunta, dificultad, verificado, curiosidad, imagen, categoria_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(13, '¿Qué tipo de chocolate se utiliza para hacer un mousse?', 'Media', 1, 'El mousse de chocolate se elabora comúnmente con chocolate negro.', 'images/categorias/Postres.jpg', 'Postres', 103, NOW(), NOW()),
(14, '¿Cuál es la fruta que se utiliza para hacer guacamole?', 'Facil', 1, 'El aguacate es la base del guacamole.', 'images/categorias/Mexicana.jpg', 'Mexicana', 103, NOW(), NOW()),
(15, '¿Qué tipo de pasta se usa para hacer lasaña?', 'Facil', 1, 'La lasaña se elabora con láminas de pasta ancha.', 'images/categorias/Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(16, '¿Cuál es la base de un buen risotto?', 'Dificil', 1, 'El risotto se basa en el arroz arborio.', 'images/categorias/Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(17, '¿Qué tipo de carne se utiliza en un fajita?', 'Media', 1, 'Las fajitas pueden llevar carne de pollo, ternera o cerdo.', 'images/categorias/Mexicana.jpg', 'Mexicana', 103, NOW(), NOW()),
(18, '¿Cuál es el principal ingrediente del tabulé?', 'Facil', 1, 'El tabulé se basa en el bulgur.', 'images/categorias/Libanesa.jpg', 'Libanesa', 103, NOW(), NOW()),
(19, '¿Qué bebida se suele tomar con sushi?', 'Facil', 1, 'El sake es una bebida típica que acompaña al sushi.', 'images/categorias/Japonesa.jpg', 'Japonesa', 103, NOW(), NOW()),
(20, '¿Qué tipo de queso se usa en un cheesecake?', 'Dificil', 1, 'El queso crema es fundamental para hacer cheesecake.', 'images/categorias/Postres.jpg', 'Postres', 103, NOW(), NOW());


INSERT INTO respuesta (id, respuesta, correcta, pregunta_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(49, 'Chocolate negro', 1, 13, 103, NOW(), NOW()),
(50, 'Chocolate con leche', 0, 13, 103, NOW(), NOW()),
(51, 'Chocolate blanco', 0, 13, 103, NOW(), NOW()),
(52, 'Ninguno', 0, 13, 103, NOW(), NOW()),
(53, 'Aguacate', 1, 14, 103, NOW(), NOW()),
(54, 'Tomate', 0, 14, 103, NOW(), NOW()),
(55, 'Cebolla', 0, 14, 103, NOW(), NOW()),
(56, 'Limón', 0, 14, 103, NOW(), NOW()),
(57, 'Pasta ancha', 1, 15, 103, NOW(), NOW()),
(58, 'Fideos', 0, 15, 103, NOW(), NOW()),
(59, 'Espaguetis', 0, 15, 103, NOW(), NOW()),
(60, 'Pasta corta', 0, 15, 103, NOW(), NOW()),
(61, 'Arroz arborio', 1, 16, 103, NOW(), NOW()),
(62, 'Arroz integral', 0, 16, 103, NOW(), NOW()),
(63, 'Arroz basmati', 0, 16, 103, NOW(), NOW()),
(64, 'Arroz jazmín', 0, 16, 103, NOW(), NOW()),
(65, 'Pollo', 1, 17, 103, NOW(), NOW()),
(66, 'Cerdo', 0, 17, 103, NOW(), NOW()),
(67, 'Pescado', 0, 17, 103, NOW(), NOW()),
(68, 'Carne de res', 0, 17, 103, NOW(), NOW()),
(69, 'Bulgur', 1, 18, 103, NOW(), NOW()),
(70, 'Cuscús', 0, 18, 103, NOW(), NOW()),
(71, 'Arroz', 0, 18, 103, NOW(), NOW()),
(72, 'Quinoa', 0, 18, 103, NOW(), NOW()),
(73, 'Sake', 1, 19, 103, NOW(), NOW()),
(74, 'Cerveza', 0, 19, 103, NOW(), NOW()),
(75, 'Vino', 0, 19, 103, NOW(), NOW()),
(76, 'Agua', 0, 19, 103, NOW(), NOW()),
(77, 'Queso crema', 1, 20, 103, NOW(), NOW()),
(78, 'Mascarpone', 0, 20, 103, NOW(), NOW()),
(79, 'Ricotta', 0, 20, 103, NOW(), NOW()),
(80, 'Queso cottage', 0, 20, 103, NOW(), NOW());


INSERT INTO pregunta (id, pregunta, dificultad, verificado, curiosidad, imagen, categoria_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(21, '¿Cuál es la carne más comúnmente utilizada en el taco?', 'Facil', 1, 'Los tacos se pueden hacer con carne de res, pollo o cerdo.', 'images/categorias/Mexicana.jpg', 'Mexicana', 103, NOW(), NOW()),
(22, '¿Qué tipo de pastel se utiliza para hacer un tiramisú?', 'Dificil', 1, 'El tiramisú se elabora con bizcochos de soletilla.', 'images/categorias/Postres.jpg', 'Postres', 103, NOW(), NOW()),
(23, '¿Cuál es la sopa típica de la cocina japonesa?', 'Media', 1, 'El miso es una sopa clásica de Japón.', 'images/categorias/Japonesa.jpg', 'Japonesa', 103, NOW(), NOW()),
(24, '¿Qué ingrediente principal se utiliza en el pesto?', 'Media', 1, 'El pesto se hace con albahaca fresca.', 'images/categorias/Italiana.jpg', 'Italiana', 103, NOW(), NOW()),
(25, '¿Qué tipo de marisco se usa en una paella de mariscos?', 'Dificil', 1, 'La paella de mariscos utiliza calamares y mejillones, entre otros.', 'images/categorias/Española.jpg', 'Española', 103, NOW(), NOW());


INSERT INTO respuesta (id, respuesta, correcta, pregunta_id, usuario_id, fechacreacion, fechaactualizacion) VALUES
(81, 'Carne de res', 1, 21, 103, NOW(), NOW()),
(82, 'Pollo', 0, 21, 103, NOW(), NOW()),
(83, 'Cerdo', 0, 21, 103, NOW(), NOW()),
(84, 'Pescado', 0, 21, 103, NOW(), NOW()),
(85, 'Bizcochos de soletilla', 1, 22, 103, NOW(), NOW()),
(86, 'Galletas', 0, 22, 103, NOW(), NOW()),
(87, 'Pan', 0, 22, 103, NOW(), NOW()),
(88, 'Brownie', 0, 22, 103, NOW(), NOW()),
(89, 'Miso', 1, 23, 103, NOW(), NOW()),
(90, 'Sopa de pollo', 0, 23, 103, NOW(), NOW()),
(91, 'Sopa de miso', 0, 23, 103, NOW(), NOW()),
(92, 'Sopa de cebolla', 0, 23, 103, NOW(), NOW()),
(93, 'Albahaca fresca', 1, 24, 103, NOW(), NOW()),
(94, 'Perejil', 0, 24, 103, NOW(), NOW()),
(95, 'Cilantro', 0, 24, 103, NOW(), NOW()),
(96, 'Romero', 0, 24, 103, NOW(), NOW()),
(97, 'Calamares', 1, 25, 103, NOW(), NOW()),
(98, 'Gambas', 0, 25, 103, NOW(), NOW()),
(99, 'Mejillones', 0, 25, 103, NOW(), NOW()),
(100, 'Pulpo', 0, 25, 103, NOW(), NOW());