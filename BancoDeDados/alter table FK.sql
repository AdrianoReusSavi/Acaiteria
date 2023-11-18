ALTER TABLE item ADD FOREIGN KEY (id_unidade_medida) REFERENCES unidade_medida (id)

ALTER TABLE item ADD FOREIGN KEY (id_tipo) REFERENCES tipo_item (id)

ALTER TABLE pedido ADD FOREIGN KEY (status) REFERENCES status (id)

ALTER TABLE pedido_item ADD FOREIGN KEY (id_pedido) REFERENCES pedido (id)

ALTER TABLE pedido_item ADD FOREIGN KEY (id_item) REFERENCES item (id)

ALTER TABLE compra ADD FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id)

ALTER TABLE compra ADD FOREIGN KEY (status) REFERENCES status (id)

ALTER TABLE compra_item ADD FOREIGN KEY (id_compra) REFERENCES compra (id)

ALTER TABLE compra_item ADD FOREIGN KEY (id_item) REFERENCES item (id)

ALTER TABLE movimentacao_estoque ADD FOREIGN KEY (id_item) REFERENCES item (id)