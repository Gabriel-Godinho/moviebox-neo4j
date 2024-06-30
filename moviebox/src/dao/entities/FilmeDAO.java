package dao.entities;

import connection.DataBaseConnection;
import model.Filme;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import view.MensagensView;
import org.neo4j.driver.Values;

import java.util.*;

public class FilmeDAO {

    private final MensagensView mensagem = new MensagensView();

    public final Set<Filme> getAll() {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (f:Filme) RETURN ID(f) AS idFilme, f.nome AS nome, f.duracao AS duracao, f.ano AS ano, f.sinopse AS sinopse";
            Result result = session.run(query);

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nome").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar os filmes cadastrados! " + e.getMessage());
        }

        return filmes;
    }

    public final Filme getById(long idFilme) {
        Filme filme = new Filme();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (f:Filme) WHERE ID(f) = $id RETURN ID(f) AS idFilme, f.nome AS nome, f.duracao AS duracao, f.ano AS ano, f.sinopse AS sinopse";
            Result result = session.run(query, Values.parameters("id", idFilme));

            if (result.hasNext()) {
                Record record = result.next();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nome").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setSinopse(record.get("sinopse").asString());
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar o filme especificado! " + e.getMessage());
        }

        return filme;
    }

    public final void save(Filme filme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "CREATE (f:Filme {nome: $nome, duracao: $duracao, ano: $ano, sinopse: $sinopse}) WITH f " +
                            "MATCH (p:Pais) WHERE id(p) = $idPais " +
                            "CREATE (f)-[:PAIS_ORIGEM]->(p) WITH (f) " +
                            "MATCH (d:Diretor) WHERE id(d) = $idDiretor " +
                            "CREATE (f)<-[:DIRIGIU]-(d)";

            session.run(query, Values.parameters(
                    "nome", filme.getNomeFilme(),
                    "duracao", filme.getDuracao(),
                    "ano", filme.getAno(),
                    "sinopse", filme.getSinopse(),
                    "idPais", filme.getIdPais(),
                    "idDiretor", filme.getIdDiretor()
            ));

            mensagem.layoutMensagem("Filme adicionado com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao inserir o novo filme! " + e.getMessage());
        }
    }

    public final void update(Filme filme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            StringBuilder sb = new StringBuilder("MATCH (f:Filme) WHERE ID(f) = $idFilme\n");

            Map<String, Object> params = new HashMap<>();
            params.put("idFilme", filme.getIdFilme());

            // Atualiza propriedades do nó Filme
            sb.append("SET ");

            boolean hasSetClause = false;

            if (filme.getNomeFilme() != null && !filme.getNomeFilme().isBlank()) {
                sb.append("f.nome = $nome, ");
                params.put("nome", filme.getNomeFilme());
                hasSetClause = true;
            }

            if (filme.getDuracao() != 0) {
                sb.append("f.duracao = $duracao, ");
                params.put("duracao", filme.getDuracao());
                hasSetClause = true;
            }

            if (filme.getAno() != 0) {
                sb.append("f.ano = $ano, ");
                params.put("ano", filme.getAno());
                hasSetClause = true;
            }

            if (filme.getSinopse() != null && !filme.getSinopse().isBlank()) {
                sb.append("f.sinopse = $sinopse, ");
                params.put("sinopse", filme.getSinopse());
                hasSetClause = true;
            }

            if (hasSetClause) {
                sb.setLength(sb.length() - 2);  // Remove a última vírgula e espaço
                sb.append("\n");
            } else {
                sb.setLength(sb.length() - 4);  // Remove "SET "
            }

            if (filme.getIdPais() != 0) {
                // Deleta relacionamento antigo
                sb.append("WITH f\n");
                sb.append("OPTIONAL MATCH (f)-[r:PAIS_ORIGEM]->()\n");
                sb.append("DELETE r\n");

                // Cria novo relacionamento
                sb.append("WITH f\n");
                sb.append("MATCH (p:Pais) WHERE ID(p) = $idPais\n");
                sb.append("CREATE (f)-[:PAIS_ORIGEM]->(p)\n");
                params.put("idPais", filme.getIdPais());
            }

            if (filme.getIdDiretor() != 0) {
                // Deleta relacionamento antigo
                sb.append("WITH f\n");
                sb.append("OPTIONAL MATCH (f)-[r:DIRIGIU]->()\n");
                sb.append("DELETE r\n");

                // Cria novo relacionamento
                sb.append("WITH f\n");
                sb.append("MATCH (d:Diretor) WHERE ID(d) = $idDiretor\n");
                sb.append("CREATE (f)-[:DIRIGIU]->(d)\n");
                params.put("idDiretor", filme.getIdDiretor());
            }

            session.run(sb.toString(), params);
            mensagem.layoutMensagem("Filme alterado com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao alterar filme! " + e.getMessage());
        }
    }

    public final void delete(long idFilme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {

            String query = "MATCH (f:Filme) WHERE ID(f) = $id DETACH DELETE f";
            session.run(query, Values.parameters("id", idFilme));

            mensagem.layoutMensagem("Filme excluído com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao deletar filme! " + e.getMessage());
        }
    }
}
