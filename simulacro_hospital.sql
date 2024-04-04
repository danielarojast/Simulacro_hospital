CREATE DATABASE hospital;
use hospital; 

CREATE TABLE especialidad(
	id_especialidad INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(30),
    descripcion VARCHAR(100)
);

CREATE TABLE medico(
id_medico INT primary key auto_increment,
nombre VARCHAR(30),
apellido VARCHAR(30),
id_especialidad INT NOT null,
constraint fk_medico_especialidad FOREIGN KEY(id_especialidad) REFERENCES especialidad(id_especialidad) ON DELETE CASCADE
);

CREATE TABLE paciente(
id_paciente INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(30),
apellido VARCHAR(30),
fecha_nacimiento date,
documiento_identidad VARCHAR(15)
);

CREATE TABLE cita(
	id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha_cita DATE,
    hora_cita time,
    motivo VARCHAR(50),
    id_paciente INT NOT NULL,
    constraint fk_cita_paciente foreign key(id_paciente) references paciente(id_paciente) ON DELETE CASCADE, 
    id_medico INT NOT NULL, 
    constraint fk_cita_medico foreign key(id_medico) references medico(id_medico) ON DELETE CASCADE
);


