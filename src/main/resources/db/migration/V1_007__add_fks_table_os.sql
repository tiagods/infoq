alter table os add constraint fk_cliente_id foreign key (cli_id) references cliente(id);
alter table os add constraint fk_usuario_id foreign key (usuario_id) references usuario(id);
alter table os add constraint fk_tecnico_id foreign key (tecnico_id) references usuario(id);