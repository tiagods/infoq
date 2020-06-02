UPDATE os SET tipo = 'OS' WHERE tipo in ('os','Os');
UPDATE os SET tipo = 'GARANTIA' WHERE tipo in ('Garantia','garatina');
UPDATE os SET situacao = 'NA_BANCADA' WHERE situacao in ('NA BANCADA','Na Bancada');
UPDATE os SET situacao = 'ENTREGA_FEITA' WHERE situacao in ('ENTREGA FEIRA','Entrega Feita');
UPDATE os SET situacao = 'ORCAMENTO_REPROVADO' WHERE situacao in ('ORÇAMENTO REPROVADO','Orçamento Reprovado');
UPDATE os SET situacao = 'AGUARDANDO_APROVACAO' WHERE situacao in ('AGUARDANDO APROVAÇÃO','Aguardando Aprovação');
UPDATE os SET situacao = 'AGUARDANDO_PECA' WHERE situacao in ('AGUARDANDO PEÇA','Aguardando Peças');
UPDATE os SET situacao = 'ABANDONADO_CLIENTE' WHERE situacao = upper('ABANDOANDO PELO CLIENTE');
UPDATE os SET situacao = 'RETORNADO' WHERE situacao in ('RETORNOU','Retornou');
UPDATE os SET situacao = 'PRONTO_AVISAR' WHERE situacao in ('ESTÁ PRONTO, AVISAR CLIENTE','Está Pronto, Avisar Cliente');
UPDATE os SET situacao = 'SEM_CONSERTO' WHERE situacao = upper('SEM CONSERTO');