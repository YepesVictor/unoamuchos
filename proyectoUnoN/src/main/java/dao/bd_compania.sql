DROP DATABASE if EXISTS bd_companias;
CREATE DATABASE bd_companias;
USE bd_companias;

CREATE OR REPLACE TABLE Persona(
	cod INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	apellidoPrimero VARCHAR(30),
	fNacimiento date
);

CREATE OR REPLACE TABLE Telefono(
	numero INT(9) PRIMARY KEY,
	nombreCompania VARCHAR(30) NOT NULL,
	telCompania INT(9),
	localidadCompania VARCHAR(30),
	id_persona INT
	);
	
ALTER TABLE Telefono ADD CONSTRAINT tel_per_fk FOREIGN KEY(id_persona) REFERENCES Persona(cod) ON DELETE CASCADE ON UPDATE CASCADE;
	
	