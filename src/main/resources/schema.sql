DROP TABLE IF EXISTS ITENS_VENDA;
DROP TABLE IF EXISTS VENDA;
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS fornecedores;
DROP TABLE IF EXISTS funcionarios;
DROP TABLE IF EXISTS clientes;

CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) ,
    email VARCHAR(255),
    telefone VARCHAR(20),
    endereco VARCHAR(255)
);

CREATE TABLE fornecedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(20),
    cnpj VARCHAR(20) UNIQUE
);

CREATE TABLE funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) ,
    cargo VARCHAR(255),
    salario DECIMAL(10, 2),
    data_contratacao DATE
);

CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    quantidade INT,
    preco DECIMAL(10, 2),
    categoria_id BIGINT,
    fornecedor_id BIGINT,
    cliente_id BIGINT,
    funcionario_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
); 

CREATE TABLE VENDA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT ,
    funcionario_id BIGINT ,
    data_venda TIMESTAMP,
    valor_total DECIMAL(10, 2),
    total_venda DECIMAL(10, 2), -- COLUNA ADICIONADA AQUI
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);

CREATE TABLE ITENS_VENDA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade_vendida INT NOT NULL,
    preco_unitario_venda DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES VENDA(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);