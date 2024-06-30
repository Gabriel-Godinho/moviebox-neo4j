package dao.entities;

import connection.DataBaseConnection;
import model.PaisOrigem;
import view.MensagensView;

import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Record;
import org.neo4j.driver.Values;

import java.util.HashSet;
import java.util.Set;

public class PaisDAO {

    private final MensagensView mensagem = new MensagensView();

    public final Set<PaisOrigem> getAll() {
        Set<PaisOrigem> paises = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (p:Pais) RETURN ID(p) AS idPais, p.nome AS nome";
            Result result = session.run(query);

            while (result.hasNext()) {
                Record record = result.next();
                PaisOrigem paisOrigem = new PaisOrigem();
                paisOrigem.setIdPais(record.get("idPais").asLong());
                paisOrigem.setNomePais(record.get("nome").asString());
                paises.add(paisOrigem);
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar os países cadastrados! " + e.getMessage());
        }

        return paises;
    }

    public final PaisOrigem getById(long idPais) {
        PaisOrigem paisOrigem = null;

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (p:Pais) WHERE ID(p) = $id RETURN ID(p) AS idPais, p.nome AS nome";
            Result result = session.run(query, Values.parameters("id", idPais));

            if (result.hasNext()) {
                Record record = result.next();
                paisOrigem = new PaisOrigem();
                paisOrigem.setIdPais(record.get("idPais").asLong());
                paisOrigem.setNomePais(record.get("nome").asString());
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar o país especificado! " + e.getMessage());
        }

        return paisOrigem;
    }

    public final PaisOrigem getByIdDiretor(long idDiretor) {
        PaisOrigem paisOrigem = null;

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (d:Diretor WHERE id(d) = $id)--(pais:Pais) RETURN pais.nome AS nome, ID(pais) AS idPais";
            Result result = session.run(query, Values.parameters("id", idDiretor));

            if (result.hasNext()) {
                Record record = result.next();
                paisOrigem = new PaisOrigem();
                paisOrigem.setIdPais(record.get("idPais").asLong());
                paisOrigem.setNomePais(record.get("nome").asString());
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar o país especificado! " + e.getMessage());
        }

        return paisOrigem;
    }

    public final PaisOrigem getByIdFilme(long idFilme) {
        PaisOrigem paisOrigem = null;

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "MATCH (f:Filme WHERE ID(f) = $id)--(pais:Pais) RETURN pais.nome AS nome, ID(pais) AS idPais";
            Result result = session.run(query, Values.parameters("id", idFilme));

            if (result.hasNext()) {
                Record record = result.next();
                paisOrigem = new PaisOrigem();
                paisOrigem.setIdPais(record.get("idPais").asLong());
                paisOrigem.setNomePais(record.get("nome").asString());
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao buscar o país especificado! " + e.getMessage());
        }

        return paisOrigem;
    }

    public final void save(PaisOrigem paisOrigem) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String query = "CREATE (p:Pais {nome: $nome})";
            session.run(query, Values.parameters("nome", paisOrigem.getNomePais()));

            mensagem.layoutMensagem("País cadastrado com sucesso!");
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao cadastrar o novo país! " + e.getMessage());
        }
    }

    public final void update(PaisOrigem paisOrigem) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            if (!paisOrigem.getNomePais().isBlank()) {
                String query = "MATCH (p:Pais) WHERE ID(p) = $id SET p.nome = $nome";
                session.run(query, Values.parameters("id", paisOrigem.getIdPais(), "nome", paisOrigem.getNomePais()));

                mensagem.layoutMensagem("País atualizado com sucesso!");
            }
        } catch (Exception e) {
            mensagem.layoutMensagem("Erro ao editar o país de origem! " + e.getMessage());
        }
    }
}