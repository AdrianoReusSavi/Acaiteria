CREATE TABLE compra_item (
  [id_compra] int NOT NULL,
  [id_item] int NOT NULL,
  [descricao_item] varchar(255) NOT NULL,
  [valor_item] decimal(10, 2) NOT NULL,
  [quantidade] decimal(10, 2 ) NOT NULL
)