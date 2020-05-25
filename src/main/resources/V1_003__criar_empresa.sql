create table empresa (
	id int not null,
	nome varchar(255),
	razao varchar(255),
	cnpj varchar(50),
	endereco varchar(255),
	bairro varchar(255),
	cep varchar(10),
	cidade varchar(50),
	estado varchar(2),
	contato varchar(50),
	email varchar(100),
	site varchar(100),
	logo blob,
	primary key(id)
)