package dao.entities;

import connection.DataBaseConnection;
import model.WatchList;
import model.WatchListItem;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.neo4j.driver.Record;
import view.MensagensView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WatchListDAO {

    private final MensagensView mensagem = new MensagensView();

    public final WatchList getWatchList() {
        WatchList watchList = new WatchList(new ArrayList<>());

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH ((f:Filme)-[r:PERTENCE_A]->(w:Watchlist)) " +
                    "RETURN ID(f) AS idFilme, r.adicionado_em AS dataInsercao";

            Result result = session.run(query);

            while (result.hasNext()) {
                Record record = result.next();
                WatchListItem watchListItem = new WatchListItem();
                watchListItem.setIdFilme(record.get("idFilme").asLong());
                watchListItem.setDataInsercaoFilme(record.get("dataInsercao").toString());
                watchList.getItensWatchList().add(watchListItem);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao mostrar a watchlist! " + e.getMessage());
        }

        return watchList;
    }

    public final String getDataInsercaoFilme(long idFilme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH ((f:Filme)-[r:PERTENCE_A]->(w:Watchlist)) " +
                    "WHERE ID(f) = $idFilme " +
                    "RETURN r.adicionado_em AS dataInsercao LIMIT 1";

            Result result = session.run(query, Values.parameters("idFilme", idFilme));

            if (result.hasNext()) {
                return result.next().get("dataInsercao").toString();
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao obter a data de inserção do filme na watchlist! " + e.getMessage());
        }

        return "";
    }

    public final boolean existsFilmeInWatchList(long idFilme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH ((f:Filme)-[:PERTENCE_A]->(w:Watchlist)) " +
                    "WHERE ID(f) = $idFilme " +
                    "RETURN COUNT(w) > 0 AS exists";

            Result result = session.run(query, Values.parameters("idFilme", idFilme));

            if (result.hasNext()) {
                return result.next().get("exists").asBoolean();
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao verificar se o filme está na watchlist! " + e.getMessage());
        }

        return false;
    }

    public final void saveWatchListItem(WatchListItem watchListItem) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH ((f:Filme) WHERE id(f) = $idFilme), (w:Watchlist)\n" +
                    "CREATE (f)-[:PERTENCE_A {adicionado_em: date()}]->(w)";

            session.run(query, Values.parameters("idFilme", watchListItem.getIdFilme()));

            mensagem.layoutMensagem("Item salvo com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao salvar o filme na Watchlist! " + e.getMessage());
        }
    }

    public final void deleteWatchListItem(long idFilme) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH ((f:Filme)-[r:PERTENCE_A]->(w:Watchlist)) " +
                    "WHERE ID(f) = $idFilme\n" +
                    "DELETE r";

            session.run(query, Values.parameters("idFilme", idFilme));

            mensagem.layoutMensagem("Filme excluído da Watchlist com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao deletar filme da Watchlist! " + e.getMessage());
        }
    }
}
