package dao.relatorios;

import connection.DataBaseConnection;
import model.Filme;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.Record;
import view.MensagensView;
import java.util.HashSet;
import java.util.Set;

public class RelatoriosDAO {

    private final MensagensView mensagem = new MensagensView();

    /**
     * Busca todos os filmes dirigidos por um determinado diretor
     * inseridos na watchlist.
     *
     * @param idDiretor ID do diretor.
     * @return Todos os filmes da watchlist que foram dirigidos pelo diretor escolhido.
     */
    public final Set<Filme> buscarFilmesNaWatchlistPorDiretor(long idDiretor) {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = """
                MATCH ((f:Filme)-[:PERTENCE_A]->(w:Watchlist)), (f)<-[:DIRIGIU]-(d:Diretor), (f)-[:PAIS_ORIGEM]->(p:Pais)
                WHERE ID(d) = $idDiretor
                RETURN f, ID(f) AS idFilme, f.nome AS nomeFilme, f.duracao AS duracao, f.ano AS ano, ID(d) AS idDiretor, ID(p) AS idPais, f.sinopse AS sinopse
            """;

            Result result = session.run(query, Values.parameters("idDiretor", idDiretor));

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nomeFilme").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setIdDiretor(record.get("idDiretor").asLong());
                filme.setIdPais(record.get("idPais").asLong());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar filmes na watchlist pelo diretor! " + e.getMessage());
        }

        return filmes;
    }

    /**
     * Busca todos os filmes dirigidos por um determinado diretor
     *
     * @param idDiretor ID do diretor.
     * @return Todos os filmes dirigidos pelo diretor escolhido.
     * */
    public final Set<Filme> buscarFilmesPorDiretor(long idDiretor) {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = """
                MATCH (f:Filme), (f)<-[:DIRIGIU]-(d:Diretor), (f)-[:PAIS_ORIGEM]->(p:Pais)
                WHERE ID(d) = $idDiretor
                RETURN f, ID(f) AS idFilme, f.nome AS nomeFilme, f.duracao AS duracao, f.ano AS ano, ID(d) AS idDiretor, ID(p) AS idPais, f.sinopse AS sinopse
            """;

            Result result = session.run(query, Values.parameters("idDiretor", idDiretor));

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nomeFilme").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setIdDiretor(record.get("idDiretor").asLong());
                filme.setIdPais(record.get("idPais").asLong());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar filmes na watchlist pelo diretor! " + e.getMessage());
        }

        return filmes;
    }

    /**
     * Busca todos os filmes de um determinado país
     * inseridos na watchlist.
     *
     * @param idPais ID do país.
     * @return Todos os filmes da watchlist cuja origem seja o país escolhido.
     * */
    public final Set<Filme> buscarFilmesNaWatchlistPorPais(long idPais) {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = """
                MATCH ((f:Filme)-[:PERTENCE_A]->(w:Watchlist)), (f)-[:PAIS_ORIGEM]->(p:Pais)
                OPTIONAL MATCH (f)<-[:DIRIGIU]-(d:Diretor)
                WHERE ID(p) = $idPais
                RETURN f, ID(f) AS idFilme, f.nome AS nomeFilme, f.duracao AS duracao,
                   f.ano AS ano, ID(d) AS idDiretor, ID(p) AS idPais, f.sinopse AS sinopse
            """;

            Result result = session.run(query, Values.parameters("idPais", idPais));

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nomeFilme").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setIdDiretor(record.containsKey("idDiretor") ? record.get("idDiretor").asLong() : 0);
                filme.setIdPais(record.get("idPais").asLong());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar filmes na watchlist pelo país! " + e.getMessage());
        }

        return filmes;
    }

    /**
     * Busca todos os filmes de um determinado país
     *
     * @param idPais ID do país.
     * @return Todos os filmes de determinado país.
     * */
    public final Set<Filme> buscarFilmesPorPais(long idPais) {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = """
                MATCH (f:Filme)-[:PAIS_ORIGEM]->(p:Pais), (f)<-[:DIRIGIU]-(d:Diretor)
                WHERE ID(p) = $idPais
                RETURN f, ID(f) AS idFilme, f.nome AS nomeFilme, f.duracao AS duracao,
                   f.ano AS ano, ID(p) AS idPais, f.sinopse AS sinopse, ID(d) AS idDiretor
            """;

            Result result = session.run(query, Values.parameters("idPais", idPais));

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setNomeFilme(record.get("nomeFilme").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setIdDiretor(record.get("idDiretor").asLong());
                filme.setIdPais(record.get("idPais").asLong());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar filmes pelo país inserido! " + e.getMessage());
        }

        return filmes;
    }

    /**
     * Busca todos os filmes adicionados na watchlist
     * em um determinado ano.
     *
     * @param anoInsercao Ano de inserção do filme.
     * @return Todos os filmes da watchlist adicionados dentro do período especificado.
     * */
    public final Set<Filme> buscarFilmesNaWatchlistPorAnoInserido(long anoInsercao) {
        Set<Filme> filmes = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = """
                MATCH ((f:Filme)-[r:PERTENCE_A]->(w:Watchlist))
                WHERE date(r.adicionado_em).year = $anoInsercao
                OPTIONAL MATCH (f)-[:PAIS_ORIGEM]->(p:Pais),
                    (f)<-[:DIRIGIU]-(d:Diretor)
                RETURN f, ID(f) AS idFilme, f.nome AS nomeFilme, ID(d) AS idDiretor, f.duracao AS duracao,
                    f.ano AS ano, ID(p) AS idPais, f.sinopse AS sinopse
            """;

            Result result = session.run(query, Values.parameters("anoInsercao", anoInsercao));

            while (result.hasNext()) {
                Record record = result.next();
                Filme filme = new Filme();
                filme.setIdFilme(record.get("idFilme").asLong());
                filme.setIdDiretor(record.get("idDiretor").asLong());
                filme.setNomeFilme(record.get("nomeFilme").asString());
                filme.setDuracao(record.get("duracao").asInt());
                filme.setAno(record.get("ano").asInt());
                filme.setIdPais(record.get("idPais").asLong());
                filme.setSinopse(record.get("sinopse").asString());
                filmes.add(filme);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar filmes pelo ano inserido! " + e.getMessage());
        }

        return filmes;
    }
}
