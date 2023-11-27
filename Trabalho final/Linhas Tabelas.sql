-- Inserir dados fictícios para Usuario
INSERT INTO Usuario (nome, cpf, data_nascimento, cidade, estado, data_inclusao)
VALUES 
  ('Alice', '123.456.789-01', '1990-05-15', 'Joinville', 'Santa Catarina', '2022-01-10'),
  ('Bob', '987.654.321-00', '1985-08-22', 'Florianópolis', 'Santa Catarina', '2022-01-15'),
  ('Charlie', '111.222.333-44', '1988-12-05', 'Joinville', 'Santa Catarina', '2022-02-20'),
  ('David', '555.666.777-88', '1993-07-01', 'Joinville', 'Santa Catarina', '2022-03-25'),
  ('Eva', '999.888.777-66', '1996-10-18', 'Pato Branco', 'Paraná', '2022-04-30');

-- Inserir dados fictícios para Rede
INSERT INTO Rede (nome, cidade, estado, cnpj, segmento, data_inclusao)
VALUES
  ('Preco 10', 'Cidade A', 'Santa Catarina', '12345678901234', 'Varejo', '2022-02-01'),
  ('Divino Sabor', 'Cidade B', 'Santa Catarina', '56789012345678', 'Restaurante', '2022-02-05'),
  ('Hospital Santa Fé', 'Cidade C', 'Santa Catarina', '98765432109876', 'Saúde', '2022-03-10'),
  ('Posto Ranger', 'Pato Branco', 'Paraná', '54321098765432', 'Transporte', '2022-04-15');

-- Inserir dados fictícios para Cartao
INSERT INTO Cartao (usuarioID, data_emissao, situacao, saldo, segmento)
VALUES
  (1, '2022-02-10', 'Ativo', 1000.00, 'Varejo'),
  (2, '2022-02-12', 'Ativo', 500.00, 'Transporte'),
  (3, '2022-03-05', 'Ativo', 800.00, 'Restaurante'),
  (4, '2022-04-01', 'Ativo', 1200.00, 'Varejo'),
  (5, '2022-05-15', 'Ativo', 600.00, 'Restaurante');

-- Inserir dados fictícios para Compra
INSERT INTO Compra (cartaoID, redeID, data, valor, segmento)
VALUES
  (1, 1, '2022-02-20', 200.00, 'Varejo'),
  (2, 4, '2022-02-25', 50.00, 'Transporte'),
  (3, 2, '2022-03-10', 120.00, 'Restaurante'),
  (4, 1, '2022-04-05', 300.00, 'Varejo'),
  (5, 2, '2022-05-01', 80.00, 'Restaurante'),
  (1, 1, '2022-06-15', 150.00, 'Varejo'),
  (3, 2, '2022-07-02', 180.00, 'Restaurante'),
  (2, 4, '2022-08-20', 90.00, 'Transporte'),
  (4, 1, '2022-09-12', 250.00, 'Varejo'),
  (5, 2, '2022-10-05', 120.00, 'Restaurante'),
  (2, 4, '2022-11-18', 100.00, 'Transporte'),
  (1, 1, '2022-12-03', 75.00, 'Varejo'),
  (3, 2, '2023-01-08', 60.00, 'Restaurante'),
  (4, 1, '2023-02-14', 200.00, 'Varejo'),
  (5, 2, '2023-03-20', 90.00, 'Restaurante');