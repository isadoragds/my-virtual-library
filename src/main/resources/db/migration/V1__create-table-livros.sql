create table livros(

	id bigint not null auto_increment,
	titulo varchar(100) not null,
	autor varchar(100) not null, 
	paginas varchar(100) not null, 
	genero varchar(100) not null, 
	localizacao varchar(100) not null,
	status varchar(100) not null, 

	primary key(id)

);