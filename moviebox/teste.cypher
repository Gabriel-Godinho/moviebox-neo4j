CREATE (EstadosUnidos:Pais {nome: 'Estados Unidos da América'})
CREATE (Espanha:Pais {nome: 'Espanha'})
CREATE (Brasil:Pais {nome: 'Brasil'})
CREATE (Ira:Pais {nome: 'Irã'})
CREATE (China:Pais {nome: 'China'})
CREATE (CoreiaDoSul:Pais {nome: 'Coreia do Sul'})
CREATE (Franca:Pais {nome: 'França'})
CREATE (Japao:Pais {nome: 'Japão'})
CREATE (Russia:Pais {nome: 'Rússia'})
CREATE (Mexico:Pais {nome: 'México'})
CREATE (ReinoUnido:Pais {nome: 'Reino Unido'})
CREATE (Dinamarca:Pais {nome: 'Dinamarca'})
CREATE (Suecia:Pais {nome: 'Suécia'})
CREATE (Italia:Pais {nome: 'Itália'})

CREATE (Rogerio:Diretor {nome: 'Rogério Sganzerla'})
CREATE (BandidoDaLuzVermelha:Filme {nome: 'O Bandido da Luz Vermelha', duracao: 92, ano: 1968, sinopse: 'Um filme que retrata a história do famoso criminoso homônimo do título.'})
CREATE (CopacabanaMonAmour:Filme {nome: 'Copacabana Mon Amour', duracao: 85, ano: 1970, sinopse: 'Um conto de amor e traição se desenrola nas areias de Copacabana, onde os destinos revelam os encantos e as complexidades da vida à beira-mar.'})
CREATE (MulherDeTodos:Filme {nome: 'A Mulher de Todos', duracao: 93, ano: 1969, sinopse: 'Um cineasta em busca de inspiração e redenção se envolve em um turbilhão de desejos e ilusões na efervescente cena cultural do Rio de Janeiro dos anos 60.'})
CREATE
  (Rogerio)-[:PAIS_NASCIMENTO]->(Brasil),
  (Rogerio)-[:DIRIGIU]->(BandidoDaLuzVermelha),
  (Rogerio)-[:DIRIGIU]->(CopacabanaMonAmour),
  (Rogerio)-[:DIRIGIU]->(MulherDeTodos),
  (BandidoDaLuzVermelha)-[:PAIS_ORIGEM]->(Brasil),
  (CopacabanaMonAmour)-[:PAIS_ORIGEM]->(Brasil),
  (MulherDeTodos)-[:PAIS_ORIGEM]->(Brasil)

CREATE (Abbas:Diretor {nome: 'Abbas Kiarostami'})
CREATE (GostoDeCereja:Filme {nome: 'Gosto de Cereja', duracao: 95, ano: 1997, sinopse: 'O filme acompanha um homem em busca de alguém que o ajude com seu plano de suicídio.'})
CREATE (CloseUp:Filme {nome: 'Close-Up', duracao: 98, ano: 1990, sinopse: 'Um mergulho intrigante na fronteira entre realidade e ficção, onde um homem comum se torna o protagonista de sua própria história ao ser confundido com uma celebridade.'})
CREATE (OndeFicaACasaDoMeuAmigo:Filme {nome: 'Onde Fica a Casa do Meu Amigo?', duracao: 83, ano: 1987, sinopse: 'Um menino empenhado em corrigir um erro inocente embarca em uma busca determinada para encontrar a casa de seu colega de classe, refletindo sobre amizade e responsabilidade.'})
CREATE
  (Abbas)-[:PAIS_NASCIMENTO]->(Ira),
  (Abbas)-[:DIRIGIU]->(GostoDeCereja),
  (Abbas)-[:DIRIGIU]->(CloseUp),
  (Abbas)-[:DIRIGIU]->(OndeFicaACasaDoMeuAmigo),
  (OndeFicaACasaDoMeuAmigo)-[:PAIS_ORIGEM]->(Ira),
  (CloseUp)-[:PAIS_ORIGEM]->(Ira),
  (GostoDeCereja)-[:PAIS_ORIGEM]->(Ira)

CREATE (Pedro:Diretor {nome: 'Pedro Almodóvar'})
CREATE (MulheresABeiraDeUmAtaqueDeNervos:Filme {nome: 'Mulheres à Beira de um Ataque de Nervos', duracao: 88, ano: 1988, sinopse: 'Um filme que mergulha na vida tumultuada de uma atriz em meio a um relacionamento complicado.'})
CREATE
  (Pedro)-[:PAIS_NASCIMENTO]->(Espanha),
  (Pedro)-[:DIRIGIU]->(MulheresABeiraDeUmAtaqueDeNervos),
  (MulheresABeiraDeUmAtaqueDeNervos)-[:PAIS_ORIGEM]->(Espanha)

CREATE (Wong:Diretor {nome: 'Wong Kar-wai'})
CREATE (AmorAFlorDaPele:Filme {nome: 'Amor à Flor da Pele', duracao: 98, ano: 2000, sinopse: 'Obra cinematográfica chinesa que explora os relacionamentos amorosos de dois personagens em Hong Kong.'})
CREATE
  (Wong)-[:PAIS_NASCIMENTO]->(China),
  (Wong)-[:DIRIGIU]->(AmorAFlorDaPele),
  (AmorAFlorDaPele)-[:PAIS_ORIGEM]->(China)

CREATE (Park:Diretor {nome: 'Park Chan-wook'})
CREATE (Oldboy:Filme {nome: 'Oldboy', duracao: 120, ano: 2003, sinopse: 'Um homem em uma busca de vingança depois de ser mantido em cativeiro por 15 anos sem explicação.'})
CREATE
  (Park)-[:PAIS_NASCIMENTO]->(CoreiaDoSul),
  (Park)-[:DIRIGIU]->(Oldboy),
  (Oldboy)-[:PAIS_ORIGEM]->(CoreiaDoSul)

CREATE (Spike:Diretor {nome: 'Spike Lee'})
CREATE (FacaACoisaCerta:Filme {nome: 'Faça a Coisa Certa', duracao: 120, ano: 1989, sinopse: 'O filme aborda questões de raça e violência em uma comunidade de Nova York durante um dia de calor intenso.'})
CREATE (InfiltradoNaKlan:Filme {nome: 'Infiltrado na Klan', duracao: 136, ano: 2018, sinopse: 'Um policial negro se infiltra na Ku Klux Klan para desmantelar suas atividades, desafiando os limites da coragem e da sobrevivência.'})
CREATE (MalcomX:Filme {nome: 'Malcom X', duracao: 202, ano: 1992, sinopse: 'Um retrato poderoso e visceral da vida e legado de Malcolm X, desde sua juventude turbulenta até sua transformação como líder dos direitos civis.'})
CREATE
  (Spike)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Spike)-[:DIRIGIU]->(FacaACoisaCerta),
  (Spike)-[:DIRIGIU]->(InfiltradoNaKlan),
  (Spike)-[:DIRIGIU]->(MalcomX),
  (FacaACoisaCerta)-[:PAIS_ORIGEM]->(EstadosUnidos),
  (InfiltradoNaKlan)-[:PAIS_ORIGEM]->(EstadosUnidos),
  (MalcomX)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Hayao:Diretor {nome: 'Hayao Miyazaki'})
CREATE (AViagemDeChihiro:Filme {nome: 'A Viagem de Chihiro', duracao: 125, ano: 2001, sinopse: 'Animação japonesa envolvente sobre uma menina que se aventura em um mundo mágico para salvar seus pais.'})
CREATE
  (Hayao)-[:PAIS_NASCIMENTO]->(Japao),
  (Hayao)-[:DIRIGIU]->(AViagemDeChihiro),
  (AViagemDeChihiro)-[:PAIS_ORIGEM]->(Japao)

CREATE (Andrei:Diretor {nome: 'Andrei Tarkovsky'})
CREATE (Solaris:Filme {nome: 'Solaris', duracao: 165, ano: 1972, sinopse: 'Adaptação russa do romance de ficção científica de mesmo nome, explorando questões de memória, identidade e solidão.'})
CREATE
  (Andrei)-[:PAIS_NASCIMENTO]->(Russia),
  (Andrei)-[:DIRIGIU]->(Solaris),
  (Solaris)-[:PAIS_ORIGEM]->(Russia)

CREATE (Alejandro:Diretor {nome: 'Alejandro González Iñárritu'})
CREATE (Birdman:Filme {nome: 'Birdman', duracao: 119, ano: 2014, sinopse: 'Um filme que segue um ator em busca de reconhecimento e redenção em meio ao caos da Broadway.'})
CREATE
  (Alejandro)-[:PAIS_NASCIMENTO]->(Mexico),
  (Alejandro)-[:DIRIGIU]->(Birdman),
  (Birdman)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Martin:Diretor {nome: 'Martin Scorsese'})
CREATE (TaxiDriver:Filme {nome: 'Taxi Driver', duracao: 113, ano: 1976, sinopse: 'Clássico que retrata a vida de um veterano de guerra atormentado que se torna um vigilante.'})
CREATE
  (Martin)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Martin)-[:DIRIGIU]->(TaxiDriver),
  (TaxiDriver)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Christopher:Diretor {nome: 'Christopher Nolan'})
CREATE (AOrigem:Filme {nome: 'A Origem', duracao: 148, ano: 2010, sinopse: 'Complexa e emocionante obra de ficção científica que explora a natureza da realidade e dos sonhos.'})
CREATE
  (Christopher)-[:PAIS_NASCIMENTO]->(ReinoUnido),
  (Christopher)-[:DIRIGIU]->(AOrigem),
  (AOrigem)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Greta:Diretor {nome: 'Greta Gerwig'})
CREATE (LadyBirdAHoraDeVoar:Filme {nome: 'Lady Bird: A Hora de Voar', duracao: 94, ano: 2017, sinopse: 'História comovente sobre uma adolescente que busca sua identidade e independência em Sacramento, Califórnia.'})
CREATE
  (Greta)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Greta)-[:DIRIGIU]->(LadyBirdAHoraDeVoar),
  (LadyBirdAHoraDeVoar)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Bong:Diretor {nome: 'Bong Joon-ho'})
CREATE (Parasita:Filme {nome: 'Parasita', duracao: 132, ano: 2019, sinopse: 'Thriller sul-coreano sobre a disparidade de classes através da história de uma família que se infiltra na vida de outra.'})
CREATE
  (Bong)-[:PAIS_NASCIMENTO]->(CoreiaDoSul),
  (Bong)-[:DIRIGIU]->(Parasita),
  (Parasita)-[:PAIS_ORIGEM]->(CoreiaDoSul)

CREATE (David:Diretor {nome: 'David Fincher'})
CREATE (ClubeDaLuta:Filme {nome: 'Clube da Luta', duracao: 139, ano: 1999, sinopse: 'Adaptação cinematográfica do livro de Chuck Palahniuk, explorando a alienação e a insatisfação na sociedade moderna.'})
CREATE (GarotaExemplar:Filme {nome: 'Garota Exemplar', duracao: 149, ano: 2014, sinopse: 'Um thriller intenso que desvenda os segredos de um casamento quando a esposa desaparece e o marido se torna o principal suspeito.'})
CREATE (Se7enOsSeteCrimesCapitais:Filme {nome: 'Se7en - Os Sete Crimes Capitais', duracao: 127, ano: 1995, sinopse: 'Um sombrio jogo de gato e rato entre dois detetives e um serial killer que baseia seus crimes nos sete pecados capitais.'})
CREATE
  (David)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (David)-[:DIRIGIU]->(Se7enOsSeteCrimesCapitais),
  (David)-[:DIRIGIU]->(ClubeDaLuta),
  (David)-[:DIRIGIU]->(GarotaExemplar),
  (ClubeDaLuta)-[:PAIS_ORIGEM]->(EstadosUnidos),
  (GarotaExemplar)-[:PAIS_ORIGEM]->(EstadosUnidos),
  (Se7enOsSeteCrimesCapitais)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Lars:Diretor {nome: 'Lars von Trier'})
CREATE (Dogville:Filme {nome: 'Dogville', duracao: 178, ano: 2003, sinopse: 'Drama épico que desafia as convenções cinematográficas ao contar a história de uma mulher fugindo da máfia.'})
CREATE
  (Lars)-[:PAIS_NASCIMENTO]->(Dinamarca),
  (Lars)-[:DIRIGIU]->(Dogville),
  (Dogville)-[:PAIS_ORIGEM]->(Dinamarca)

CREATE (Guillermo:Diretor {nome: 'Guillermo del Toro'})
CREATE (OLabirintoDoFauno:Filme {nome: 'O Labirinto do Fauno', duracao: 118, ano: 2006, sinopse: 'O filme combina a realidade cruel da guerra civil espanhola com o mundo mágico da fantasia neste conto sombrio e belo.'})
CREATE
  (Guillermo)-[:PAIS_NASCIMENTO]->(Mexico),
  (Guillermo)-[:DIRIGIU]->(OLabirintoDoFauno),
  (OLabirintoDoFauno)-[:PAIS_ORIGEM]->(Mexico)

CREATE (Stanley:Diretor {nome: 'Stanley Kubrick'})
CREATE (OIluminado:Filme {nome: 'O Iluminado', duracao: 146, ano: 1980, sinopse: 'Clássico do horror que mergulha na insanidade de um homem isolado em um hotel no inverno.'})
CREATE
  (Stanley)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Stanley)-[:DIRIGIU]->(OIluminado),
  (OIluminado)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Steven:Diretor {nome: 'Steven Spielberg'})
CREATE (ETOExtraterrestre:Filme {nome: 'E.T.: O Extraterrestre', duracao: 115, ano: 1982, sinopse: 'A jornada emocional e fantástica sobre a amizade entre um menino e um extraterrestre perdido na Terra.'})
CREATE
  (Steven)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Steven)-[:DIRIGIU]->(ETOExtraterrestre),
  (ETOExtraterrestre)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Ingmar:Diretor {nome: 'Ingmar Bergman'})
CREATE (Persona:Filme {nome: 'Persona', duracao: 85, ano: 1966, sinopse: 'Filme que mergulha nas complexidades da identidade e da interação humana através da relação entre duas mulheres.'})
CREATE
  (Ingmar)-[:PAIS_NASCIMENTO]->(Suecia),
  (Ingmar)-[:DIRIGIU]->(Persona),
  (Persona)-[:PAIS_ORIGEM]->(Suecia)

CREATE (Federico:Diretor {nome: 'Federico Fellini'})
CREATE (Amarcord:Filme {nome: 'Amarcord', duracao: 123, ano: 1973, sinopse: 'Federico Fellini nos transporta para uma pequena cidade italiana durante os anos 1930, explorando memória, nostalgia e desejo em sua obra característica.'})
CREATE
  (Federico)-[:PAIS_NASCIMENTO]->(Italia),
  (Federico)-[:DIRIGIU]->(Amarcord),
  (Amarcord)-[:PAIS_ORIGEM]->(Italia)

CREATE (Francis:Diretor {nome: 'Francis Ford Coppola'})
CREATE (OPoderosoChefao:Filme {nome: 'O Poderoso Chefão', duracao: 175, ano: 1972, sinopse: 'Clássico do crime que segue a ascensão e queda de uma família mafiosa ítalo-americana.'})
CREATE
  (Francis)-[:PAIS_NASCIMENTO]->(EstadosUnidos),
  (Francis)-[:DIRIGIU]->(OPoderosoChefao),
  (OPoderosoChefao)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (Alfred:Diretor {nome: 'Alfred Hitchcock'})
CREATE (Psicose:Filme {nome: 'Psicose', duracao: 109, ano: 1960, sinopse: 'Filme icônico sobre uma secretária em fuga que encontra um destino aterrorizante em um motel isolado.'})
CREATE
  (Alfred)-[:PAIS_NASCIMENTO]->(ReinoUnido),
  (Alfred)-[:DIRIGIU]->(Psicose),
  (Psicose)-[:PAIS_ORIGEM]->(EstadosUnidos)

CREATE (WatchlistUser:Watchlist {titulo: 'Watchklist'})

MATCH (n) RETURN n
