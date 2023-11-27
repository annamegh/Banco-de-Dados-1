CREATE TABLE Usuario (
    usuarioID SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(14),
    data_nascimento DATE,
    cidade VARCHAR(255),
    estado VARCHAR(255),
    data_inclusao DATE
);

-- Criar tabela Cartao
CREATE TABLE Cartao (
    cartaoID SERIAL PRIMARY KEY,
    usuarioID INT REFERENCES Usuario(usuarioID),
    data_emissao DATE,
    situacao VARCHAR(255),
    saldo NUMERIC,
    segmento VARCHAR(255)
);

-- Criar tabela Compra
CREATE TABLE Compra (
    compraID SERIAL PRIMARY KEY,
    cartaoID INT REFERENCES Cartao(cartaoID),
	redeID INT REFERENCES Rede(redeID),
    data DATE,
    valor NUMERIC,
    segmento VARCHAR(255)
);

-- Criar tabela Rede
CREATE TABLE Rede (
    redeID SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cnpj VARCHAR(18),
    segmento VARCHAR(255),
    data_inclusao DATE
);