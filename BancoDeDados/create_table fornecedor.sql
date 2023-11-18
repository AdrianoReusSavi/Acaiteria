CREATE TABLE fornecedor (
  [id] int PRIMARY KEY NOT NULL IDENTITY,
  [razao] varchar(100) NOT NULL,
  [cnpj] varchar(14) NOT NULL,
  [cep] char(8) NOT NULL,
  [uf] char(2) NOT NULL,
  [cidade] varchar(150) NOT NULL
)