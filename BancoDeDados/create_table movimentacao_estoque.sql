CREATE TABLE movimentacao_estoque (
  [id] int PRIMARY KEY NOT NULL IDENTITY,
  [id_item] int NOT NULL,
  [quantidade_movimento] decimal NOT NULL,
  [data_hora] datetime NOT NULL,
  [tipo] char(1) NOT NULL,
  [valor] decimal(10, 2) NOT NULL
)