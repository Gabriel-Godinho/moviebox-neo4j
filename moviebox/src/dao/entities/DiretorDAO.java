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

    public final void save(Diretor diretor) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            String cypherQuery = "CREATE (d:Diretor {nome: $nome})";
            session.run(cypherQuery, Values.parameters("nome", diretor.getNomeDiretor()));
            System.out.println("Diretor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o novo diretor! " + e.getMessage());
        }
    }

    public final void update(Diretor diretor) {
        try (Session session = DataBaseConnection.getInstance().getSession()) {
            StringBuilder cypherQuery = new StringBuilder("MATCH (d:Diretor) WHERE id(d) = $id SET");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", diretor.getIdDiretor());

            if (!diretor.getNomeDiretor().equals("0")) {
                cypherQuery.append(" d.nome = $nome,");
                parameters.put("nome", diretor.getNomeDiretor());
            }

            if (cypherQuery.toString().endsWith(",")) {
                cypherQuery.deleteCharAt(cypherQuery.length() - 1); // Remove a vírgula final
            }

            session.run(cypherQuery.toString(), parameters);
            System.out.println("Diretor atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o diretor! " + e.getMessage());
        }
    }
}
