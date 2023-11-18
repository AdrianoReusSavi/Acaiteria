CREATE TABLE item (
  [id] int PRIMARY KEY NOT NULL IDENTITY,
  [descricao] varchar(255) NOT NULL,
  [id_unidade_medida] int NOT NULL,
  [id_tipo] int NOT NULL,
  [quantidade_estoque] decimal NOT NULL,
  [preco_compra] decimal(10, 2) NOT NULL,
  [preco_venda] decimal(10, 2) NOT NULL
)

ALTER TABLE item ADD FOREIGN KEY (id_unidade_medida) REFERENCES unidade_medida (id)

ALTER TABLE item ADD FOREIGN KEY (id_tipo) REFERENCES tipo_item (id)
