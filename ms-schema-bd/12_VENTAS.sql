CREATE TABLE VENTAS(
    ID_USUARIO NUMERIC(6,0) NOT NULL PRIMARY KEY,
    ID_TIPO_TRANSACCION NUMERIC(6,0) REFERENCES CAT_TIPO_TRANSACCION(ID_TIPO_TRANSACCION),
    TOTAL NUMERIC(9,2) NOT NULL,
    DESCUENTO NUMERIC(9,2) NOT NULL,
    IMPUESTO NUMERIC(9,2) NOT NULL,
    FECHA DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_VENTAS;


