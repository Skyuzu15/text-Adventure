create database text_adventure

use text_adventure

CREATE TABLE Cena (
    Id_cena INT PRIMARY KEY AUTO_INCREMENT,
    descricao longtext NOT NULL
);

select * from Cena;

CREATE TABLE Item (
    Id_item INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL, 
    id_proxima_cena INT,
    id_cena_atual INT,
    uso TEXT,
    FOREIGN KEY (id_cena_atual) REFERENCES Cena(Id_cena) 
);

select * from Item;

CREATE TABLE Use_with (
    Id_use_with INT PRIMARY KEY AUTO_INCREMENT,
    Id_cena INT,
    Id_item INT,
    FOREIGN KEY (Id_cena) REFERENCES Cena(Id_cena),
    FOREIGN KEY (Id_item) REFERENCES Item(Id_item)
);

select * from Use_with;

CREATE TABLE Jogador (
    Id_jogador INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

select * from jogador;

CREATE TABLE Inventario (
    Id_inventario INT PRIMARY KEY AUTO_INCREMENT,
    Id_item INT,
    Id_jogador INT,
    FOREIGN KEY (Id_item) REFERENCES Item(Id_item) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Id_jogador) REFERENCES Jogador(Id_jogador) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from Inventario;

CREATE TABLE Save (
    Id_save INT PRIMARY KEY AUTO_INCREMENT,
    id_cena_atual int not null,
    FOREIGN KEY (id_cena_atual) REFERENCES Cena(Id_cena)
);

select * from Save;

INSERT INTO Cena (descricao) VALUES
('Você acorda desorientado na praia de uma ilha desconhecida. O sol brilha intensamente, e você sente a areia quente sob seus pés enquanto o som suave das ondas ecoa ao seu redor. Seu barco está em pedaços, e você está sozinho.'),
('Você adentra um vale oculto, onde várias formações de cristal brilham sob a luz do sol. O ambiente é mágico, e você sente uma energia poderosa no ar. Entre os cristais, um em particular chama sua atenção.');
('Seguindo uma trilha, você adentra uma floresta densa e misteriosa. O ar é fresco, e o cheiro de terra molhada permeia a neblina que se arrasta pelo chão. As árvores parecem sussurrar segredos, e você sente que está sendo observado.'),
('Seguindo as pegadas, você chega a um antigo santuário em ruínas. Há inscrições em uma língua desconhecida nas paredes e um pedestal no centro, onde algo parece ter sido retirado.'),
('Ao abrir o portão de pedra, você encontra uma caverna escura e úmida. Ao fundo, uma serpente gigante guarda um misterioso objeto brilhante.'),
('No fundo da caverna, você encontra um velho xamã que lhe oferece uma visão do futuro em troca de uma oferta.'),
('Agora com a sabedoria adquirida, você volta à caverna para enfrentar a serpente. Sua visão revelou que a flor brilhante pode acalmá-la.'),
('Com o artefato sagrado em mãos, você retorna ao santuário. Colocá-lo de volta no pedestal ativará o portal para escapar da ilha.');

select * from Cena;

INSERT INTO Item (nome, descricao, id_proxima_cena, id_cena_atual, uso) VALUES
('Bússola quebrada', 'Uma bússola que não aponta para lugar nenhum, mas talvez sirva para alguma coisa.', NULL, 1, 'Tentar usar a bússola, mas ela não funciona.'),
('Baú trancado', 'Um baú que parece conter algo útil, mas está trancado.', NULL, 1, 'Tentar abrir o baú.'),
('Cristal de Ofertas', 'Um cristal brilhante que pode ser usado como uma oferta para o xamã.', 5, 8, 'Usar para obter uma visão do futuro.'),
('Flor brilhante', 'Uma flor que brilha intensamente e emite uma energia curiosa.', 3, 2, 'Pode acalmar a serpente.'),
('Artefato sagrado', 'Um objeto misterioso que parece ter um poder ancestral.', NULL, 4, 'Usar para realizar o ritual.'),
('Poção mística', 'Uma poção que promete visões do futuro, mas pode ser perigosa.', 6, 5, 'Usar para ter uma visão.'),
('Totem de pedra', 'Um totem antigo que parece ter um significado especial.', NULL, 4, 'Pode ser usado para ativar o poder do artefato.');

select * from item;

INSERT INTO Use_with (Id_cena, Id_item) VALUES
(4, 3), 
(5, 7), 
(8, 7); 

select * from Use_with;

INSERT INTO Jogador (nome) VALUES
('Jogador1'),
('Jogador2');

select * from Jogador;

INSERT INTO Inventario (Id_item, Id_jogador) VALUES
(1, 1),  
(2, 1),  
(3, 2), 
(4, 1),
(5, 1),
(6, 1),
(7, 1);

select * from Inventario; 

ALTER TABLE cena MODIFY descricao longtext;
