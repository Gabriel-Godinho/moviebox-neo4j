# Sistema gerenciador de filmes
Sistema gerenciador de filmes feito para a matéria de BAN2 (banco de dados 2) da Universidade do Estado de Santa Catarina - UDESC

# Seções
- [Descritivo do sistema](#descritivo-do-sistema)
- [Tecnologias usadas](#tecnologias-usadas)
- [Banco de dados](#banco-de-dados)
- [Como executar](#como-executar)

# Descritivo do sistema

O domínio de informação escolhido foi o gerenciamento de filmes. Dentro deste domínio, os usuários têm o poder de visualizar filmes de todos os gêneros, juntamente com seus metadados, como diretor, ano de lançamento, país de origem e duração. Além disso, os usuários podem criar uma watchlist personalizada, que é uma lista de filmes que desejam assistir em algum momento. Eles podem adicionar qualquer filme à lista, visualizar os filmes já adicionados, filtrar por data de inserção e remover filmes conforme necessário.

# Tecnologias usadas
- Java 21 LTS
- PostgreSQL
- AWS RDS

# Banco de dados

Foi criada uma instância de um banco de dados Postgres no RDS (Relational Database Service) da AWS.

AWS RDS (Relationa Database Service): https://aws.amazon.com/pt/rds/

# Como executar

- Clonar o repositório
- A aplicação foi desenvolvida utilizando o Java 21, portanto, verifique a versão do Java instalada na sua máquina antes de rodar.
- **ATENÇÃO!** O projeto utiliza duas bibliotecas externas (JDBC do banco de dados e Lombok) que se encontram na pasta "lib" do projeto. Caso a IDE utilizada não inclua automaticamente as bibliotecas será necessário incluí-las. A IDE utilizada para o desenvolvimento do projeto foi o IntelliJ. Para incluir as bibliotecas no IntelliJ é necessário ir em "File" - "Project Structure" - "Libraries" e por fim clicar no símbolo de "+" e adicionar o caminho do arquivo jar.

https://github.com/Gabriel-Godinho/moviebox/assets/105725028/eb5f57ba-ab1b-4f33-9538-bd8af86b3ff9

- Abra o terminal na pasta raiz do projeto se quiser executar por linhas de comando. Caso contrário, utilize a sua IDE de preferência para rodar o projeto.
- Executar os seguintes comandos no terminal.
  - javac Main.java
  - java Main

