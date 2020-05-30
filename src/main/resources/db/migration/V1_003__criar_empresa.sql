create table empresa (
	id int not null,
	nome varchar(255),
	cnpj varchar(50),
	endereco varchar(255),
	bairro varchar(255),
	cep varchar(10),
	cidade varchar(50),
	estado varchar(2),
	celular varchar(50),
	telefone varchar(50),
	email varchar(100),
	site varchar(100),
	mensagem text,
	primary key(id)
)