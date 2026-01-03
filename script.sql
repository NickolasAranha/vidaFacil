CREATE SCHEMA mydb;
USE mydb;

-- --------------------------
-- Tabela Idoso
-- --------------------------
CREATE TABLE Idoso (
  ID_Idoso INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(50) NOT NULL,
  Senha VARCHAR(255) NOT NULL,
  Data_Nascimento DATE NOT NULL,
  Email VARCHAR(254) UNIQUE NOT NULL,
  CPF CHAR(11) UNIQUE NOT NULL,
  Codigo_Conexao VARCHAR(10) UNIQUE NOT NULL,
  PRIMARY KEY (ID_Idoso)
);

-- --------------------------
-- Tabela Cuidador
-- --------------------------
CREATE TABLE Cuidador (
  ID_Cuidador INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(50) NOT NULL,
  Senha VARCHAR(255) NOT NULL,
  Data_Nascimento DATE NOT NULL,
  Email VARCHAR(254) UNIQUE NOT NULL,
  CPF CHAR(11) UNIQUE NOT NULL,
  ID_Idoso INT UNIQUE,
  PRIMARY KEY (ID_Cuidador),
  FOREIGN KEY (ID_Idoso) REFERENCES Idoso(ID_Idoso)
);

-- --------------------------
-- Tabela Medicamento
-- --------------------------
CREATE TABLE Medicamento (
  ID_Medicamento INT NOT NULL AUTO_INCREMENT,
  ID_Idoso INT NOT NULL,
  Nome VARCHAR(100) NOT NULL,
  Dosagem INT NOT NULL,
  Unidade ENUM('mg', 'ml', 'gotas', 'comprimido') NOT NULL,
  Intervalo_Aviso INT NOT NULL,
  Data_Inicio DATE NOT NULL,
  Data_Fim DATE,
	Ultima_Dose DATETIME NOT NULL,
  PRIMARY KEY (ID_Medicamento),
  FOREIGN KEY (ID_Idoso) REFERENCES Idoso(ID_Idoso)
);

-- --------------------------
-- Tabela Meta_Diaria
-- --------------------------
CREATE TABLE Meta_Diaria (
  ID_Config INT NOT NULL AUTO_INCREMENT,
  ID_Idoso INT UNIQUE NOT NULL,
  Meta_Diaria INT NOT NULL,
  Intervalo_Aviso INT NOT NULL,
  Hora_Inicio TIME NOT NULL,
  Hora_Fim TIME NOT NULL,
  PRIMARY KEY (ID_Config),
  FOREIGN KEY (ID_Idoso) REFERENCES Idoso(ID_Idoso)
);

-- --------------------------
-- Tabela Registro_Consumo
-- --------------------------
CREATE TABLE Registro_Consumo (
  ID_Registro INT NOT NULL AUTO_INCREMENT,
  ID_Idoso INT NOT NULL,
  Data_Hora DATETIME NOT NULL,
  Quantidade INT NOT NULL,
  PRIMARY KEY (ID_Registro),
  FOREIGN KEY (ID_Idoso) REFERENCES Idoso(ID_Idoso)
);

-- --------------------------
-- Tabela Alerta_Medicamento
-- --------------------------
CREATE TABLE Alerta_Medicamento (
  ID_Alerta INT AUTO_INCREMENT NOT NULL,
  ID_Medicamento INT NOT NULL,
  Data_Hora DATETIME NOT NULL,
  status ENUM('Pendente', 'Confirmado', 'Recusado', 'Perdido') NOT NULL DEFAULT 'Pendente',
  PRIMARY KEY (ID_Alerta),
  FOREIGN KEY (ID_Medicamento) REFERENCES Medicamento(ID_Medicamento)
);

-- --------------------------
-- Tabela Alerta_Hidratacao
-- --------------------------
CREATE TABLE Alerta_Hidratacao (
  ID_Alerta INT AUTO_INCREMENT NOT NULL,
  ID_Idoso INT NOT NULL,
  Data_Hora DATETIME NOT NULL,
  status ENUM('Pendente', 'Confirmado', 'Recusado', 'Perdido') NOT NULL DEFAULT 'Pendente',
  PRIMARY KEY (ID_Alerta),
  FOREIGN KEY (ID_Idoso) REFERENCES Idoso(ID_Idoso)
);
