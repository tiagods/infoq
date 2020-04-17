CREATE TABLE `cliente` (
  `id` int AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `num` varchar(15) DEFAULT NULL,
  `comp` varchar(15) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  `rg` varchar(14) DEFAULT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `cel` varchar(15) DEFAULT NULL,
  `bairro` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `os` (
  `id` int AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo` varchar(10) NOT NULL,
  `aparelho` varchar(150) NOT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `entrada` decimal(10,0) DEFAULT NULL,
  `cli_id` int(11) NOT NULL,
  `obs` text DEFAULT NULL,
  `tecnico` varchar(20) DEFAULT NULL,
  `situacao` varchar(28) NOT NULL,
  `garantia` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `os_ibfk_1` FOREIGN KEY (`cli_id`) REFERENCES `cliente` (`id`)
  ON DELETE CASCADE
);

CREATE TABLE `usuario` (
  `id` int AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
);