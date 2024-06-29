CREATE TABLE paises (
    id_pais SERIAL PRIMARY KEY,
    nome_pais VARCHAR(255)
);

CREATE TABLE nacionalidades (
    id_nacionalidade SERIAL PRIMARY KEY,
    id_pais INTEGER REFERENCES paises(id_pais),
    nome_nacionalidade VARCHAR(30)
);

CREATE TABLE diretores (
    id_diretor SERIAL PRIMARY KEY,
    nome_diretor VARCHAR(255),
    id_nacionalidade INTEGER REFERENCES nacionalidades(id_nacionalidade)
);

CREATE TABLE filmes (
    id_filme SERIAL PRIMARY KEY,
    nome_filme VARCHAR(255),
    duracao INTEGER,
    ano INTEGER,
    id_diretor INTEGER REFERENCES diretores(id_diretor),
    id_pais INTEGER REFERENCES paises(id_pais),
    sinopse VARCHAR(500)
);

CREATE TABLE watchlist (
    id_filme INTEGER PRIMARY KEY,
    data_insercao_filme DATE,
    CONSTRAINT fk_watchlists_filmes FOREIGN KEY (id_filme) REFERENCES filmes(id_filme)
);

-- POPULANDO TABELAS
INSERT INTO paises (nome_pais)
VALUES
    ('Estados Unidos'),
    ('Espanha'),
	('Brasil'),
	('Irã'),
    ('China'),
    ('Coreia do Sul'),
    ('França'),
    ('Japão'),
    ('Rússia'),
    ('México'),
    ('Reino Unido'),
    ('Dinamarca'),
    ('Suécia'),
    ('Itália');
	
INSERT INTO nacionalidades (id_pais, nome_nacionalidade)
VALUES
	(1, 'Estadunidense'),
	(2, 'Espanhol(a)'),
    (3, 'Brasileiro(a)'),
    (4, 'Iraniano(a)'),
    (5, 'Chinês(a)'),
    (6, 'Sul coreano(a)'),
	(7, 'Francês'),
    (8, 'Japonês(a)'),
    (9, 'Russo(a)'),
    (10, 'Mexicano(a)'),
    (11, 'Britânico(a)'),
    (12, 'Dinamarquês(a)'),
    (13, 'Sueco(a)'),
    (14, 'Italiano(a)');
	
INSERT INTO diretores (nome_diretor, id_nacionalidade) 
VALUES 
    ('Rogério Sganzerla', 1),
	('Abbas Kiarostami', 2),
    ('Pedro Almodóvar', 3),
    ('Wong Kar-wai', 4),
    ('Park Chan-wook', 5),
    ('Spike Lee', 6),
    ('Hayao Miyazaki', 7),
    ('Andrei Tarkovsky', 8),
    ('Alejandro González Iñárritu', 9),
    ('Martin Scorsese', 6),
    ('Christopher Nolan', 10),
    ('Greta Gerwig', 6),
    ('Bong Joon-ho', 5),
    ('David Fincher', 6),
    ('Lars von Trier', 11),
    ('Guillermo del Toro', 9),
    ('Stanley Kubrick', 6),
    ('Steven Spielberg', 6),
    ('Ingmar Bergman', 12),
    ('Federico Fellini', 13),
    ('Francis Ford Coppola', 6),
    ('Alfred Hitchcock', 10);

INSERT INTO filmes (nome_filme, duracao, ano, id_diretor, id_pais, sinopse)
VALUES
    ('O Bandido da Luz Vermelha', 92, 1968, 1, 3, 'Um filme que retrata a história do famoso criminoso homônimo do título.'),
	('Copacabana Mon Amour', 85, 1970, 1, 3, 'Um conto de amor e traição se desenrola nas areias de Copacabana, onde os destinos revelam os encantos e as complexidades da vida à beira-mar.'),
	('A Mulher de Todos', 93, 1969, 1, 3, 'Um cineasta em busca de inspiração e redenção se envolve em um turbilhão de desejos e ilusões na efervescente cena cultural do Rio de Janeiro dos anos 60.'),
    ('Gosto de Cereja', 95, 1997, 2, 4, 'O filme acompanha um homem em busca de alguém que o ajude com seu plano de suicídio.'),
	('Close-Up', 98, 1990, 2, 4, 'Um mergulho intrigante na fronteira entre realidade e ficção, onde um homem comum se torna o protagonista de sua própria história ao ser confundido com uma celebridade.'),
	('Onde Fica a Casa do Meu Amigo?', 83, 1987, 2, 4, 'Um menino empenhado em corrigir um erro inocente embarca em uma busca determinada para encontrar a casa de seu colega de classe, refletindo sobre amizade e responsabilidade.'),
    ('Mulheres à Beira de um Ataque de Nervos', 88, 1988, 3, 2, 'Um filme que mergulha na vida tumultuada de uma atriz em meio a um relacionamento complicado.'),
    ('Amor à Flor da Pele', 98, 2000, 4, 5, 'Obra cinematográfica chinesa que explora os relacionamentos amorosos de dois personagens em Hong Kong.'),
    ('Oldboy', 120, 2003, 5, 6,'Um homem em uma busca de vingança depois de ser mantido em cativeiro por 15 anos sem explicação.'),
    ('Faça a Coisa Certa', 120, 1989, 6, 1, 'O filme aborda questões de raça e violência em uma comunidade de Nova York durante um dia de calor intenso.'),
	('Infiltrado na Klan', 136, 2018, 6, 1, 'Um policial negro se infiltra na Ku Klux Klan para desmantelar suas atividades, desafiando os limites da coragem e da sobrevivência.'),
	('Malcom X', 202, 1992, 6, 1, 'Um retrato poderoso e visceral da vida e legado de Malcolm X, desde sua juventude turbulenta até sua transformação como líder dos direitos civis.'),
    ('A Viagem de Chihiro', 125, 2001, 7, 8, 'Animação japonesa envolvente sobre uma menina que se aventura em um mundo mágico para salvar seus pais.'),
    ('Solaris', 165, 1972, 8, 9, 'Adaptação russa do romance de ficção científica de mesmo nome, explorando questões de memória, identidade e solidão.'),
    ('Birdman', 119, 2014, 9, 1, 'Um filme que segue um ator em busca de reconhecimento e redenção em meio ao caos da Broadway.'),
    ('Taxi Driver', 113, 1976, 10, 1, 'Clássico que retrata a vida de um veterano de guerra atormentado que se torna um vigilante.'),
    ('A Origem', 148, 2010, 11, 1, 'Complexa e emocionante obra de ficção científica que explora a natureza da realidade e dos sonhos.'),
    ('Lady Bird: A Hora de Voar', 94, 2017, 12, 1, 'História comovente sobre uma adolescente que busca sua identidade e independência em Sacramento, Califórnia.'),
    ('Parasita', 132, 2019, 13, 6, 'Thriller sul-coreano sobre a disparidade de classes através da história de uma família que se infiltra na vida de outra.'),
    ('Clube da Luta', 139, 1999, 14, 1, 'Adaptação cinematográfica do livro de Chuck Palahniuk, explorando a alienação e a insatisfação na sociedade moderna.'),
	('Garota Exemplar', 149, 2014, 14, 1, 'Um thriller intenso que desvenda os segredos de um casamento quando a esposa desaparece e o marido se torna o principal suspeito.'),
	('Se7en - Os Sete Crimes Capitais', 127, 1995, 14, 1, 'Um sombrio jogo de gato e rato entre dois detetives e um serial killer que baseia seus crimes nos sete pecados capitais.'),
    ('Dogville', 178, 2003, 15, 12, 'Drama épico que desafia as convenções cinematográficas ao contar a história de uma mulher fugindo da máfia.'),
    ('O Labirinto do Fauno', 118, 2006, 16, 10, 'O filme combina a realidade cruel da guerra civil espanhola com o mundo mágico da fantasia neste conto sombrio e belo.'),
    ('O Iluminado', 146, 1980, 17, 1, 'Clássico do horror que mergulha na insanidade de um homem isolado em um hotel no inverno.'),
    ('E.T.: O Extraterrestre', 115, 1982, 18, 1, 'A jornada emocional e fantástica sobre a amizade entre um menino e um extraterrestre perdido na Terra.'),
    ('Persona', 85, 1966, 19, 13, 'Filme que mergulha nas complexidades da identidade e da interação humana através da relação entre duas mulheres.'),
    ('Amarcord', 123, 1973, 20, 14, 'Federico Fellini nos transporta para uma pequena cidade italiana durante os anos 1930, explorando memória, nostalgia e desejo em sua obra característica.'),
    ('O Poderoso Chefão', 175, 1972, 21, 1, 'Clássico do crime que segue a ascensão e queda de uma família mafiosa ítalo-americana.'),
    ('Psicose', 109, 1960, 22, 1, 'Filme icônico sobre uma secretária em fuga que encontra um destino aterrorizante em um motel isolado.');

INSERT INTO watchlist (id_filme, data_insercao_filme)
VALUES
	(17, DATE '2023-01-20'),
    (29, DATE '2022-04-12'),
    (6, DATE '2023-09-30'),
    (23, DATE '2022-08-05'),
    (12, DATE '2021-07-14'),
    (3, DATE '2024-02-18'),
    (5, DATE '2021-12-03'),
    (28, DATE '2020-11-27'),
    (10, DATE '2022-10-08'),
    (2, DATE '2024-03-22'),
    (25, DATE '2021-06-29'),
    (8, DATE '2024-01-15'),
    (21, DATE '2023-05-18'),
    (13, DATE '2020-12-07');

------- SELECTS
SELECT * FROM diretores;
SELECT * FROM paises;
SELECT * FROM nacionalidades;
SELECT * FROM filmes;
SELECT * FROM watchlist;


