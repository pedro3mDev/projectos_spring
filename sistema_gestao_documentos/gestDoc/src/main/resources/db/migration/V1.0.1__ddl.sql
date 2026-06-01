create table empresa(
	id integer not null,
	nome varchar(255),
	email varchar(255),
	documento varchar(255),
	foto varchar(255),
	datacriacao timestamp,
	proprietario integer,
	ativo boolean,
	primary key (id)
);

create table usuario(
	id integer not null,
	nome varchar(255),
	email varchar(255),
	senha varchar(255),
	datanascimento date,
	sexo varchar(255),
	ativo boolean,
	token varchar(255),
	datacriacao timestamp,
	primary key (id)
);

create table departamento(
	id integer not null,
	nome varchar(255),
	empresaid integer,
	ativo boolean,
	data timestamp,
	primary key (id)
);

create table departamento_usuario(
	usuarioid integer,
	departamentoid integer
);

create table empresa_usuario(
	id integer not null,
	usuarioid integer,
	empresaid integer,
	data timestamp,
	ativo boolean,
	primary key (id)
);

create table grupo(
	id integer not null,
	empresaid integer,
	nome varchar(255),
	data timestamp,
	ativo boolean,
	primary key (id)
);

create table grupo_permissao(
	grupo_id integer,
	permissao varchar(255)
);

create table grupo_usuario(
	grupo_id integer,
	usuario_id integer
);

create table documento(
	id integer not null,
	nome varchar(255),
	arquivo varchar(255),
	descricao varchar(255),
	datacriacao timestamp,
	proprietario integer,
	ativo boolean,
	nivel integer,
	ordem integer,
	tipo varchar(255),
	versao integer,
	departamentoid integer,
	documentopaiid integer,
	tamanho decimal(10,10),
	revisao decimal(10,2),
	primary key (id)
);

create sequence seq_empresa start with 1;
create sequence seq_usuario start with 1;
create sequence seq_departamento start with 1;
create sequence seq_departamento_usuario start with 1;
create sequence seq_empresa_usuario start with 1;
create sequence seq_grupo start with 1;
create sequence seq_documento start with 1;

alter table departamento add constraint fk_departamento_empresaid foreign key (empresaid) references empresa (id);
alter table departamento_usuario add constraint fk_departamento_usuario_usuarioid foreign key (usuarioid) references usuario (id);
alter table departamento_usuario add constraint fk_departamento_usuario_departamentoid foreign key (departamentoid) references departamento (id);
alter table empresa_usuario add constraint fk_empresa_usuario_usuarioid foreign key (usuarioid) references usuario (id);
alter table empresa_usuario add constraint fk_empresa_usuario_empresaid foreign key (empresaid) references empresa (id);
alter table grupo add constraint fk_grupo_empresaid foreign key (empresaid) references empresa (id);
alter table grupo_usuario add constraint fk_grupo_usuario_grupo_id foreign key (grupo_id) references grupo (id);
alter table grupo_usuario add constraint fk_grupo_usuario_usuario_id foreign key (usuario_id) references usuario (id);
alter table grupo_permissao add constraint fk_grupo_permissao_grupo_id foreign key (grupo_id) references grupo (id);
alter table documento add constraint fk_documento_departamentoid foreign key (departamentoid) references departamento (id);
alter table documento add constraint fk_documento_documentopaiid foreign key (documentopaiid) references documento (id);