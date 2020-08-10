ALTER TABLE usuario ADD COLUMN tecnico boolean;
ALTER TABLE usuario ADD COLUMN ativo boolean;
UPDATE usuario set tecnico = true, ativo = true;

ALTER TABLE os ADD COLUMN usuario_id integer;
ALTER TABLE os ADD COLUMN tecnico_id integer;
