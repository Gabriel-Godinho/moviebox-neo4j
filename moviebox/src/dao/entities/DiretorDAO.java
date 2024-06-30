package dao.entities;

import connection.DataBaseConnection;
import model.Diretor;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;
import org.neo4j.driver.Values;
import view.MensagensView;
import java.util.*;

public class DiretorDAO {

    private final MensagensView mensagem = new MensagensView();
    private final PaisDAO PAIS_DAO = new PaisDAO();

    public final Set<Diretor> getAll() {
        Set<Diretor> diretores = new HashSet<>();

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String cypherQuery = "MATCH (d:Diretor) RETURN ID(d) AS idDiretor, d.nome AS nome";
            Result result = session.run(cypherQuery);

            while (result.hasNext()) {
                Record record = result.next();
                Diretor diretor = new Diretor();
                diretor.setIdDiretor(record.get("idDiretor").asLong());
                diretor.setNomeDiretor(record.get("nome").asString());
                diretores.add(diretor);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar os diretores cadastrados! " + e.getMessage());
        }

        return diretores;
    }

    public final Diretor getById(long idDiretor) {
        Diretor diretor = null;

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String cypherQuery = "MATCH (d:Diretor) WHERE ID(d) = $id RETURN ID(d) AS id, d.nome AS nome";
            Result result = session.run(cypherQuery, Values.parameters("id", idDiretor));

            if (result.hasNext()) {
                Record record = result.next();
                diretor = new Diretor();
                diretor.setIdDiretor(record.get("id").asLong());
                diretor.setNomeDiretor(record.get("nome").asString());
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o diretor com ID " + idDiretor + ": " + e.getMessage());
        }

        return diretor;
    }

    public final Diretor getByIdFilme(long idFilme) {
        Diretor diretor = null;

        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String cypherQuery = "MATCH (f:Filme WHERE ID(f) = $id)--(d:Diretor) RETURN ID(d) AS idDiretor, d.nome AS nome";
            Result result = session.run(cypherQuery, Values.parameters("id", idFilme));

            if (result.hasNext()) {
                Record record = result.next();
                diretor = new Diretor();
                diretor.setIdDiretor(record.get("idDiretor").asLong());
                diretor.setNomeDiretor(record.get("nome").asString());
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar o diretor com ID " + idFilme + ": " + e.getMessage());
        }

        return diretor;
    }

    public final void save(Diretor diretor) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String cypherQuery = "CREATE (d:Diretor {nome: $nome}) WITH d " +
                                "MATCH (p:Pais) WHERE id(p) = $idPais " +
                                "CREATE (d)-[:PAIS_NASCIMENTO]->(p)";

            session.run(cypherQuery, Values.parameters(
                    "nome", diretor.getNomeDiretor(),
                    "idPais", diretor.getIdPais()
            ));
            System.out.println("Diretor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o novo diretor! " + e.getMessage());
        }
    }

    public final void update(Diretor diretor) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            StringBuilder cypherQuery = new StringBuilder("MATCH (d:Diretor) WHERE ID(d) = $id\n");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", diretor.getIdDiretor());

            if (!diretor.getNomeDiretor().equals("0")) {
                cypherQuery.append("SET d.nome = $nome\n");
                parameters.put("nome", diretor.getNomeDiretor());
            }

            if (diretor.getIdPais() != 0) {
                // Deleta relacionamento antigo
                cypherQuery.append("WITH d\n");
                cypherQuery.append("OPTIONAL MATCH (d)-[r:PAIS_NASCIMENTO]->()\n");
                cypherQuery.append("DELETE r\n");

                // Cria novo relacionamento
                cypherQuery.append("WITH d\n");
                cypherQuery.append("MATCH (p:Pais) WHERE ID(p) = $idPais\n");
                cypherQuery.append("CREATE (d)-[:PAIS_NASCIMENTO]->(p)\n");
                parameters.put("idPais", diretor.getIdPais());
            }

            session.run(cypherQuery.toString(), parameters);
            System.out.println("Diretor atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o diretor! " + e.getMessage());
        }
    }
}
