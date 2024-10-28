CREATE TABLE `tbl_productos` (
    `Id_Producto` INT NOT NULL AUTO_INCREMENT,
	`Nombre` varchar(100) NOT NULL,
	`Precio` DOUBLE NOT NULL,
	`Cantidad` INT NOT NULL ,
	`Image_Url` varchar(100),
	PRIMARY KEY (`Id_Producto`)
);