INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01001,'Jamon Iberico',18,'https://cdn.shopify.com/s/files/1/1589/5089/files/Jamon-iberico_1024x1024.jpg?v=1526041502','Servido con pan tumaca',1);
INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01002,'Patatas Bravas',7.50,'https://upload.wikimedia.org/wikipedia/commons/1/16/Patatas_bravas_madrid.jpg','',1);
INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01003,'Secreto Iberico a la Plancha',17.50,'https://lacocinadelentula.files.wordpress.com/2012/08/dscn8711.jpg','con esparragos trigueros',2);
INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01004,'Entrecot',17.50,'https://i.blogs.es/31bbf8/entrecot-lomo-bajo-patatas-pimientos-padron/1366_2000.jpg','con patatas fritas y pimientos de padron',2);
INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01005,'Flan de Queso',4,'https://www.recetasderechupete.com/wp-content/uploads/2015/07/flan_de_queso.jpg?width=1200&enable=upscale','',3);
INSERT INTO PLATOS (PLATO_ID,NOMBRE,PRECIO,FOTO,DESCRIPCION,SECCION) VALUES (01006,'Flan de Queso bad',4,'https://www.recetasderechupete.com/wp-content/uploads/2015/07/flan_de_queso.jpg?width=1200&enable=upscale','',3);

INSERT INTO RESTAURANTES (RESTAURANTE_ID,NOMBRE,DIRECCION,FOTO) VALUES (01,'Casa Pablo','Calle Pechofrio Sainz 55','https://static.guiarepsol.com/fichas-gr/media/thumbnails/filer_public/b0/13/b0131f7f-888b-4a6a-b27d-c21cc0160d3a/tmptmp7kqjkuij8c2016f7a3114746bf45a7cf32afd033_1284x850_q75_middle.jpeg');

INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES (01,01001);
INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES (01,01002);
INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES (01,01003);
INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES (01,01004);
INSERT INTO REST_PLATO (RESTAURANTE_ID,PLATO_ID) VALUES (01,01005);