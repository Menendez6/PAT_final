DROP TABLE IF EXISTS PEDIDO_PLATO;
DROP TABLE IF EXISTS REST_PLATO;
DROP TABLE IF EXISTS PLATOS;
DROP TABLE IF EXISTS PEDIDOS;
DROP TABLE IF EXISTS RESTAURANTES;

CREATE TABLE PLATOS (
    PLATO_ID INTEGER IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR NOT NULL,
    PRECIO NUMERIC NOT NULL,
    FOTO VARCHAR NOT NULL,
    DESCRIPCION VARCHAR,
    SECCION INTEGER NOT NULL
);

CREATE TABLE RESTAURANTES (
    RESTAURANTE_ID INTEGER IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR NOT NULL,
    DIRECCION VARCHAR NOT NULL,
    FOTO VARCHAR NOT NULL
);

CREATE TABLE PEDIDOS (
    PEDIDO_ID INTEGER IDENTITY PRIMARY KEY,
    MESA INTEGER NOT NULL,
    PRECIO NUMERIC NOT NULL,
    ESTADO INTEGER NOT NULL,
    RESTAURANTE_ID INTEGER NOT NULL,
    FOREIGN KEY (RESTAURANTE_ID) REFERENCES RESTAURANTES(RESTAURANTE_ID) ON DELETE CASCADE
);

CREATE TABLE PEDIDO_PLATO (
    PEDIDO_ID INTEGER,
    PLATO_ID INTEGER,
    NUM_PLATOS INTEGER NOT NULL,
    PRIMARY KEY (PEDIDO_ID,PLATO_ID),
    FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDOS(PEDIDO_ID) ON DELETE CASCADE,
    FOREIGN KEY (PLATO_ID) REFERENCES PLATOS(PLATO_ID) ON DELETE CASCADE
);

CREATE TABLE REST_PLATO (
    RESTAURANTE_ID INTEGER,
    PLATO_ID INTEGER,
    PRIMARY KEY (RESTAURANTE_ID,PLATO_ID),
    FOREIGN KEY (PLATO_ID) REFERENCES PLATOS(PLATO_ID) ON DELETE CASCADE,
    FOREIGN KEY (RESTAURANTE_ID) REFERENCES RESTAURANTES(RESTAURANTE_ID) ON DELETE CASCADE
);