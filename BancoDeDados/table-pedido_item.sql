CREATE TABLE pedido_item (
  [id_pedido] int NOT NULL,
  [id_item] int NOT NULL,
  [descricao_item] varchar(255) NOT NULL,
  [valor_venda] decimal(10, 2) NOT NULL
)

ALTER TABLE pedido_item ADD FOREIGN KEY (id_pedido) REFERENCES pedido (id)

ALTER TABLE pedido_item ADD FOREIGN KEY (id_item) REFERENCES item (id)
