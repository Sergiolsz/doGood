# doGood
API REST prueba técnica de sistema de puntaje

API con 3 puntos de entrada (Controller):

1) CRUD: Donde se establecen los servicios básicos: Añadir usuario, Obtener usuario por su code y obtener el listado de todos los usuarios.
2) API Consult Data: Servicios para obtener los datos de los usuarios: Puntos, Logros y el Ranking.
3) API Score Management: Servicios para añadir o restar puntos (Reportes).

La distribución del proyecto se centra en un Microservicio que he intentado estructurar en una arquitectura hexagonal, definiendo 3 paquetes: Application, Domain e Infrastructure, bajo un mismo POM (No lo he separado por modulos).
Se ha querido evitar en la medida de los posible el posible acoplamiento entre piezas e implementar el uso por capa.
Uso del desacople mediante la inyección de Beans por Interfaces funcionales para los servicios de API y mediante una implementación clásica por interface directa para el CRUD.

Los servicios están definidos en 3 capas:

1) Domain: Definición de los DTO, Modelos de datos RQ y RS, Mapper e Interfaces Funcionales.
2) Application: Capa de Servicio, para la implementación de la posible lógica de negocio requerida en cada servicio.
3) Infrastructure: Punto de entrada mediante API REST - Swagger, definición de los repositorios, de la configuración de los Beans (Swagger y Adaptadores de acceso al repositorio).

Se utiliza la definición de Port-Adapter para gestionar la definición de los puntos de entrada y la implementación.
Se han definido los POJOS mediante Builder para la creación y Value para la inmutabilidad.
Para los objetos de respuesta de cada capa se ha pretendido estandarizar y modular el uso de cada caso de uso:
- Entity-DTO para los casos de uso de repositorio-adaptadores mediante mappers
- RQ-RS para la interacción de entrada y salida del controller-service

Se ha utilizado el inglés para la definición de nombres de clases, variables y mensajes de respuesta.
Testeado sólo alguna clase por capa para tener cubierta la fase de test.
Se hace uso de Mapper para la transferencia de datos y también la inyección mediante Builder.

** PUNTO AÑADIDO

1) Creado el CRUD para la gestión directa de los usuarios a nivel básico (Creación y obtención)
2) Creado un servicio de Histórico (HistoryScore), para tener un registro de añadido y reporte de puntos para cada usuario.

** PUNTO MEJORA

1) Se podría implementar @RestControllerAdvice para la gestión de excepciones.
2) Securización para el uso de las APIS.

Tecnologías:
-----------

- Java 17
- Spring boot 3.1.5
- Swagger (OpenApi ya que es la que soporta SpringBoot 3)
- Lombok
- MapStruct
- JPA
- JUNIT 5 - Mockito

Base de datos:
--------------

Se ha creado en Mysql.
Configurado para Mysql.

Caso de uso con Workbench.

------
Tablas
------

1) USER_DATA
2) HISTORY_SCORE

USER_DATA
- USER_CODE
- NAME
- COMPANY
- JOB
- SCORE
- ACHIEVEMENT
- REGISTERED
  
HISTORY_SCORE
- ID
- USER_CODE
- ACTION
- SCORE
- ACHIEVEMENT
- MESSAGE
- REGISTERED

----------------------------------
CREADO DE TABLAS E INSERT DE DATOS
----------------------------------

CREATE TABLE `user_data` (
  `USER_CODE` varchar(100) NOT NULL,
  `NAME` varchar(200) NOT NULL DEFAULT 'not especified',
  `COMPANY` varchar(45) NOT NULL DEFAULT 'not especified',
  `JOB` varchar(45) NOT NULL DEFAULT 'not especified',
  `SCORE` int NOT NULL DEFAULT '0',
  `ACHIEVEMENT` varchar(45) DEFAULT 'sin logro',
  `REGISTERED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`USER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `history_score` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `USER_CODE` varchar(100) NOT NULL,
  `ACTION` varchar(45) NOT NULL DEFAULT 'sin proceso',
  `SCORE` int NOT NULL DEFAULT '0',
  `ACHIEVEMENT` varchar(100) NOT NULL DEFAULT 'sin logro',
  `MESSAGE` varchar(200) DEFAULT 'sin dato',
  `REGISTERED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `userCode_FK_idx` (`USER_CODE`),
  CONSTRAINT `userCode_FK` FOREIGN KEY (`USER_CODE`) REFERENCES `user_data` (`USER_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

INSERT INTO dogood.user (USER_CODE, NAME, COMPANY, JOB, REGISTERED)
VALUES("1457FOR", "Fernando Otero Rodríguez", "Dogood", "Java developer", NOW());
INSERT INTO dogood.user (USER_CODE, NAME, COMPANY, JOB, REGISTERED)
VALUES("6578MCS", "María Castillejo Sanz", "Dogood", "Angular developer", NOW());
INSERT INTO dogood.user (USER_CODE, NAME, COMPANY, JOB, REGISTERED)
VALUES("5443LAH", "Lucas Almeida Hergueta", "Dogood", "Devops", NOW());
