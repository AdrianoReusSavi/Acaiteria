CREATE TABLE compra (
  [id] int PRIMARY KEY NOT NULL IDENTITY,
  [data_hora] datetime NOT NULL,
  [nfe] int NOT NULL,
  [frete] decimal(10, 2) NOT NULL,
  [valor_total] decimal(10, 2) NOT NULL,
  [desconto] decimal(10, 2) NOT NULL,
  [id_fornecedor] int NOT NULL,
  [status] int NOT NULL
)

ALTER TABLE compra ADD FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id)

ALTER TABLE compra ADD FOREIGN KEY (status) REFERENCES status (id)
