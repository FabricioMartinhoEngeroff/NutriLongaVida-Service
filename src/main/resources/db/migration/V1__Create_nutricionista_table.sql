CREATE TABLE nutricionistas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    registro_profissional_crm VARCHAR(6) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    estado VARCHAR(50) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    numero_casa INTEGER NOT NULL,
    complemento VARCHAR(255)
);


INSERT INTO nutricionistas (nome, cpf, registro_profissional_crm, email, telefone, especialidade, ativo, estado, cidade, bairro, rua, cep, numero_casa, complemento)
VALUES
('Ana Clara Souza', '123.456.789-01', '12345', 'ana.souza@example.com', '(11) 98765-4321', 'NUTRICAO_CLINICA_GERAL', TRUE, 'São Paulo', 'São Paulo', 'Centro', 'Rua das Flores', '01001-000', 100, 'Apto 101'),
('Bruno Santos Lima', '234.567.890-12', '23456', 'bruno.lima@example.com', '(21) 98765-1234', 'NUTRICAO_ESPORTIVA', TRUE, 'Rio de Janeiro', 'Rio de Janeiro', 'Copacabana', 'Av. Atlântica', '22010-000', 200, ''),
('Carla Oliveira Costa', '345.678.901-23', '34567', 'carla.costa@example.com', '(31) 99876-5432', 'NUTRICAO_INFANTIL', TRUE, 'Minas Gerais', 'Belo Horizonte', 'Savassi', 'Rua da Bahia', '30130-000', 300, 'Apto 202'),
('Daniel Pereira Silva', '456.789.012-34', '45678', 'daniel.silva@example.com', '(41) 91234-5678', 'NUTRICAO_GERIATRICA', TRUE, 'Paraná', 'Curitiba', 'Batel', 'Av. Sete de Setembro', '80250-000', 150, ''),
('Elisa Martins Ferreira', '567.890.123-45', '56789', 'elisa.ferreira@example.com', '(51) 92345-6789', 'NUTRICAO_ONCOLOGICA', TRUE, 'Rio Grande do Sul', 'Porto Alegre', 'Moinhos de Vento', 'Rua Padre Chagas', '90570-080', 120, ''),
('Felipe Almeida Souza', '678.901.234-56', '67890', 'felipe.souza@example.com', '(61) 93456-7890', 'NUTRICAO_MATERNAL', TRUE, 'Distrito Federal', 'Brasília', 'Asa Sul', 'SHS QD 06', '70322-000', 610, 'Bloco B'),
('Gabriela Rocha Mendes', '789.012.345-67', '78901', 'gabriela.mendes@example.com', '(71) 94567-8901', 'NUTRICAO_FUNCIONAL', TRUE, 'Bahia', 'Salvador', 'Pituba', 'Av. Manoel Dias', '41830-001', 321, ''),
('Hugo Costa Barros', '890.123.456-78', '89012', 'hugo.barros@example.com', '(81) 95678-9012', 'NUTRICAO_CLINICA_GERAL', TRUE, 'Pernambuco', 'Recife', 'Boa Viagem', 'Rua dos Navegantes', '51020-010', 100, ''),
('Isabela Carvalho Andrade', '901.234.567-89', '90123', 'isabela.andrade@example.com', '(91) 96789-0123', 'NUTRICAO_ESPORTIVA', TRUE, 'Pará', 'Belém', 'Umarizal', 'Av. Gov. José Malcher', '66055-260', 200, ''),
('João Victor Ribeiro', '012.345.678-90', '01234', 'joao.ribeiro@example.com', '(85) 97890-1234', 'NUTRICAO_INFANTIL', TRUE, 'Ceará', 'Fortaleza', 'Aldeota', 'Rua Monsenhor Bruno', '60115-190', 450, '');
