CREATE TABLE `tbclientes` (
  `idcli` int AUTO_INCREMENT,
  `nomecli` varchar(50) NOT NULL,
  `endcli` varchar(50) DEFAULT NULL,
  `numcli` varchar(15) DEFAULT NULL,
  `compcli` varchar(15) DEFAULT NULL,
  `emailcli` varchar(40) DEFAULT NULL,
  `cpfcli` varchar(15) DEFAULT NULL,
  `cnpjcli` varchar(18) DEFAULT NULL,
  `rgcli` varchar(14) DEFAULT NULL,
  `fonecli` varchar(15) DEFAULT NULL,
  `celcli` varchar(15) DEFAULT NULL,
  `bairrocli` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idcli`)
);


CREATE TABLE `tbos` (
  `os` int AUTO_INCREMENT,
  `data_os` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo` varchar(10) NOT NULL,
  `aparelho` varchar(150) NOT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `entrada` decimal(10,0) DEFAULT NULL,
  `idcli` int(11) NOT NULL,
  `obs` varchar(250) DEFAULT NULL,
  `tecnico` varchar(20) DEFAULT NULL,
  `situacao` varchar(28) NOT NULL,
  `garantia` varchar(15) NOT NULL,
  PRIMARY KEY (`os`),
  CONSTRAINT `tbos_ibfk_1` FOREIGN KEY (`idcli`) REFERENCES `tbclientes` (`idcli`)
  ON DELETE CASCADE
);

CREATE TABLE `tbusuarios` (
  `iduser` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(15) NOT NULL,
  PRIMARY KEY (`iduser`,`login`)
);