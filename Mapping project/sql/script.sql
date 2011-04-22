SELECT '<info_to_display>' AS ' ';

DROP TABLE IF EXISTS peliculas;

DROP DATABASE IF EXISTS cine;

CREATE DATABASE cine;

use cine;

CREATE TABLE peliculas (titulo VARCHAR(100), director VARCHAR(100), protagonista VARCHAR(100), duracion INTEGER);

INSERT INTO peliculas VALUES('El caballero oscuro', 'Christopher Nolan', 'Christian Bale', 9120);

INSERT INTO peliculas VALUES('Forrest Gump', 'Robert Zemeckis', 'Tom Hanks', 8520);

INSERT INTO peliculas VALUES('Regreso al futuro', 'Robert Zemeckis', 'Michael J. Fox', 7020);
