-- Tabla Roles
CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       descripcion VARCHAR(500)
);

-- Tabla Usuarios
CREATE TABLE usuarios (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          correo VARCHAR(100) UNIQUE NOT NULL,
                          telefono VARCHAR(20),
                          clave VARCHAR(255) NOT NULL,
                          id_rol INT NOT NULL,
                          url_imagen VARCHAR(255),
                          nombre_usuario VARCHAR(255),
                          FOREIGN KEY (id_rol) REFERENCES roles(id)
);

-- Tabla Tipo de Organización
CREATE TABLE tipo_organizacion (
                                   id SERIAL PRIMARY KEY,
                                   tipo VARCHAR(100) NOT NULL,
                                   descripcion VARCHAR(500)
);

-- Tabla Organizaciones
CREATE TABLE organizaciones (
                                id BIGINT PRIMARY KEY,
                                tipo_organizacion_id INT NOT NULL,
                                descripcion TEXT,
                                FOREIGN KEY (id) REFERENCES usuarios(id),
                                FOREIGN KEY (tipo_organizacion_id) REFERENCES tipo_organizacion(id)
);

-- Tabla Voluntarios
CREATE TABLE voluntarios (
                             id BIGINT PRIMARY KEY,
                             apellido VARCHAR(100) NOT NULL,
                             habilidades TEXT,
                             experiencia TEXT,
                             disponibilidad VARCHAR(100),
                             areas_interes TEXT,
                             FOREIGN KEY (id) REFERENCES usuarios(id)
);

-- Tabla Categorías
CREATE TABLE categorias (
                            id SERIAL PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            descripcion VARCHAR(500)
);

-- Tabla Proyectos
CREATE TABLE proyectos (
                           id SERIAL PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion TEXT,
                           ubicacion VARCHAR(100),
                           requisitos TEXT,
                           fecha_inicio DATE,
                           fecha_fin DATE,
                           voluntarios_requeridos INT,
                           id_categoria INT NOT NULL,
                           url_imagen VARCHAR(255),
                           organizacion_id INT NOT NULL,
                           FOREIGN KEY (organizacion_id) REFERENCES organizaciones(id),
                           FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

-- Tabla Estados de Inscripción
CREATE TABLE estados_inscripcion (
                                     id SERIAL PRIMARY KEY,
                                     nombre VARCHAR(50) NOT NULL,
                                     descripcion VARCHAR(500)
);

-- Tabla Inscripcion (Voluntario "realiza" inscripción en Proyecto)
CREATE TABLE inscripciones (
                               id SERIAL PRIMARY KEY,
                               voluntario_id INT NOT NULL,
                               proyecto_id INT NOT NULL,
                               motivacion TEXT,
                               fecha_inscripcion DATE,
                               id_estado INT NOT NULL,
                               FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),
                               FOREIGN KEY (voluntario_id) REFERENCES voluntarios(id),
                               FOREIGN KEY (id_estado) REFERENCES estados_inscripcion(id)
);

-- Tabla Comentarios (Voluntario "hace" comentario en Proyecto)
CREATE TABLE comentarios (
                             id SERIAL PRIMARY KEY,
                             voluntario_id INT NOT NULL,
                             proyecto_id INT NOT NULL,
                             comentario TEXT,
                             fecha_comentario DATE,
                             FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),
                             FOREIGN KEY (voluntario_id) REFERENCES voluntarios(id)
);

-- Tabla Evaluaciones (Organización "hace" evaluación de Voluntario en Proyecto)
CREATE TABLE evaluaciones (
                              id SERIAL PRIMARY KEY,
                              organizacion_id INT NOT NULL,
                              voluntario_id INT NOT NULL,
                              proyecto_id INT NOT NULL,
                              calificacion INT,
                              observaciones TEXT,
                              fecha_evaluacion DATE,
                              FOREIGN KEY (organizacion_id) REFERENCES organizaciones(id),
                              FOREIGN KEY (voluntario_id) REFERENCES voluntarios(id),
                              FOREIGN KEY (proyecto_id) REFERENCES proyectos(id)
);

-- Tabla Participaciones (Voluntario "participa" en Proyecto)
CREATE TABLE participaciones (
                                 id SERIAL PRIMARY KEY,
                                 voluntario_id INT NOT NULL,
                                 proyecto_id INT NOT NULL,
                                 fecha_inicio DATE,
                                 fecha_fin DATE,
                                 horas_realizadas NUMERIC(5,2),
                                 FOREIGN KEY (voluntario_id) REFERENCES voluntarios(id),
                                 FOREIGN KEY (proyecto_id) REFERENCES proyectos(id)
);
