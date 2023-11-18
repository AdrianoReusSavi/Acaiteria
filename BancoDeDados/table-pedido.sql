CREATE TABLE pedido (
  [id] int PRIMARY KEY NOT NULL IDENTITY,
  [data_hora] datetime NOT NULL,
  [valor_total] decimal(10, 2) NOT NULL,
  [desconto] decimal(10,2) NOT NULL,
  [id_metodo] int NOT NULL,
  [id_condicao] int NOT NULL,
  [status] int NOT NULL,
  [cliente] varchar(100) NOT NULL
)

ALTER TABLE pedido ADD FOREIGN KEY (status) REFERENCES status (id)
