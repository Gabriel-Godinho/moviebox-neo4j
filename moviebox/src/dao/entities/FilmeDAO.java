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
                filme.setIdPais(record.get("idFilme").asLong());
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
            String query = "MATCH (f:Filme) WHERE ID(f) = $id RETURN f";
            Result result = session.run(query, Values.parameters("id", idFilme));

            if (result.hasNext()) {
                Record record = result.next();
                filme.setIdFilme(record.get("f").get("id").asLong());
                filme.setNomeFilme(record.get("f").get("nome").asString());
                filme.setDuracao(record.get("f").get("duracao").asInt());
                filme.setAno(record.get("f").get("ano").asInt());
                filme.setSinopse(record.get("f").get("sinopse").asString());
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar o filme especificado! " + e.getMessage());
        }

        return filme;
    }

    public final void save(Filme filme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "CREATE (f:Filme {nome: $nome, duracao: $duracao, ano: $ano, sinopse: $sinopse})";
            session.run(query, Values.parameters(
                    "nome", filme.getNomeFilme(),
                    "duracao", filme.getDuracao(),
                    "ano", filme.getAno(),
                    "sinopse", filme.getSinopse()
            ));

            mensagem.layoutMensagem("Filme adicionado com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao inserir o novo filme! " + e.getMessage());
        }
    }

    public final void update(Filme filme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            StringBuilder sb = new StringBuilder("MATCH (f:Filme) WHERE ID(f) = $id SET ");

            Map<String, Object> params = new HashMap<>();
            params.put("id", filme.getIdFilme());

            if (filme.getNomeFilme() != null && !filme.getNomeFilme().isBlank()) {
                sb.append("f.nome = $nome, ");
                params.put("nome", filme.getNomeFilme());
            }

            if (filme.getDuracao() != 0) {
                sb.append("f.duracao = $duracao, ");
                params.put("duracao", filme.getDuracao());
            }

            if (filme.getAno() != 0) {
                sb.append("f.ano = $ano, ");
                params.put("ano", filme.getAno());
            }

            if (filme.getSinopse() != null && !filme.getSinopse().isBlank()) {
                sb.append("f.sinopse = $sinopse, ");
                params.put("sinopse", filme.getSinopse());
            }

            if (sb.toString().endsWith(", ")) {
                sb.setLength(sb.length() - 2);  // Remove a última vírgula e espaço
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
