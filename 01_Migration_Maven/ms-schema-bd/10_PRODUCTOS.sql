CREATE TABLE PRODUCTOS(
    ID_PRODUCTO NUMERIC(6,0) NOT NULL PRIMARY KEY,
    ID_MARCA NUMERIC(6,0) REFERENCES CAT_MARCAS(ID_MARCA),
    ID_CATEGORIA NUMERIC(6,0) REFERENCES CAT_CATEGORIAS(ID_CATEGORIA),
    ID_PROVEEDOR NUMERIC(6,0) REFERENCES CAT_PROVEEDORES(ID_PROVEEDOR),
    NOMBRE VARCHAR2(120) NOT NULL,
    EXISTENCIA_FISICA NUMERIC(9,0) NOT NULL,
    STOCK NUMERIC(9,0) NOT NULL,
    CODIGO_BARRAS VARCHAR2(120) NOT NULL,
    PRECIO NUMERIC(9,2) NOT NULL,
    IMAGEN VARCHAR2(999) NOT NULL,
    DESCRIPCION VARCHAR2(120) NOT NULL,
    DESCUENTO VARCHAR2(120) NOT NULL,
    ESTATUS NUMERIC(6,0) NOT NULL
);

CREATE SEQUENCE SEQ_PRODUCTOS;


