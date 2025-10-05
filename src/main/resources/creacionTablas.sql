-- Tabla Organización
CREATE TABLE organizaciones (
                                id_organizacion SERIAL PRIMARY KEY,
                                nombre VARCHAR(100),
                                nombre_usuario VARCHAR(100),
                                telefono VARCHAR(100),
                                tipo VARCHAR(50),
                                correo VARCHAR(100),
                                contrasena VARCHAR(100),
                                descripcion TEXT
);

-- Tabla Voluntario
CREATE TABLE voluntarios (
                             id_voluntario SERIAL PRIMARY KEY,
                             nombre VARCHAR(100),
                             apellido VARCHAR(100),
                             nombre_usuario VARCHAR(100),
                             correo VARCHAR(100),
                             contrasena VARCHAR(100),
                             telefono VARCHAR(100),
                             habilidades TEXT,
                             experiencia TEXT,
                             disponibilidad VARCHAR(50),
                             areas_interes TEXT
);

-- Tabla Categoria
CREATE TABLE categorias (
                            id_categoria SERIAL PRIMARY KEY,
                            nombre VARCHAR(50),
                            descripcion TEXT
);

-- Tabla Proyecto
CREATE TABLE proyectos (
                           id_proyecto SERIAL PRIMARY KEY,
                           id_organizacion INT REFERENCES organizaciones(id_organizacion),
                           id_categoria INT REFERENCES categorias(id_categoria),
                           titulo VARCHAR(100),
                           descripcion TEXT,
                           ubicacion VARCHAR(100),
                           requisitos TEXT,
                           fecha_inicio DATE,
                           fecha_fin DATE,
                           voluntarios_requeridos INT
);

-- Tabla Inscripción
CREATE TABLE inscripciones (
                               id_inscripcion SERIAL PRIMARY KEY,
                               id_voluntario INT REFERENCES voluntarios(id_voluntario),
                               id_proyecto INT REFERENCES proyectos(id_proyecto),
                               motivacion TEXT,
                               estado VARCHAR(20),
                               fecha_inscripcion DATE
);

-- Tabla Evaluación
CREATE TABLE evaluaciones (
                              id_evaluacion SERIAL PRIMARY KEY,
                              id_proyecto INT REFERENCES proyectos(id_proyecto),
                              id_voluntario INT REFERENCES voluntarios(id_voluntario),
                              calificacion INT,
                              observaciones TEXT
);

-- Tabla Comentario
CREATE TABLE comentarios (
                             id_comentario SERIAL PRIMARY KEY,
                             id_proyecto INT REFERENCES proyectos(id_proyecto),
                             id_voluntario INT REFERENCES voluntarios(id_voluntario),
                             contenido TEXT,
                             fecha_comentario DATE
);

-- Tabla Reporte
CREATE TABLE reportes (
                          id_reporte SERIAL PRIMARY KEY,
                          id_organizacion INT REFERENCES organizaciones(id_organizacion),
                          id_proyecto INT REFERENCES proyectos(id_proyecto),
                          tipo VARCHAR(50),
                          contenido TEXT,
                          fecha_reporte DATE
);

-- Tabla Certificado
CREATE TABLE certificados (
                              id_certificado SERIAL PRIMARY KEY,
                              id_voluntario INT REFERENCES voluntarios(id_voluntario),
                              id_proyecto INT REFERENCES proyectos(id_proyecto),
                              fecha_emision DATE,
                              url_documento TEXT
);

-- Tabla Administrador
CREATE TABLE administradores (
                                 id_admin SERIAL PRIMARY KEY,
                                 nombre VARCHAR(100),
                                 correo VARCHAR(100),
                                 contrasena VARCHAR(100),
                                 rol VARCHAR(50)
);

-- Tabla Rol
CREATE TABLE roles (
                       id_rol SERIAL PRIMARY KEY,
                       nombre VARCHAR(50)
);

-- Tabla Permiso
CREATE TABLE permisos (
                          id_permiso SERIAL PRIMARY KEY,
                          nombre VARCHAR(100)
);

-- Tabla RolPermiso (relación N:N entre roles y permisos)
CREATE TABLE rol_permiso (
                             id_rol INT REFERENCES roles(id_rol),
                             id_permiso INT REFERENCES permisos(id_permiso),
                             PRIMARY KEY (id_rol, id_permiso)
);

-- Tabla UsuarioRol (relación N:N entre usuarios y roles)
CREATE TABLE usuario_rol (
                             id_usuario INT,
                             id_rol INT REFERENCES roles(id_rol),
                             PRIMARY KEY (id_usuario, id_rol)
);
