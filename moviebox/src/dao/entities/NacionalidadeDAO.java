package dao.entities;

import connection.DataBaseConnection;
import model.Nacionalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NacionalidadeDAO {

    public final Nacionalidade getById(long idNacionalidade) {
        Nacionalidade nacionalidade = new Nacionalidade();

        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "SELECT * FROM nacionalidades WHERE id_pais = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idNacionalidade);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nacionalidade.setIdNaciolidade(resultSet.getLong("id_nacionalidade"));
                nacionalidade.setIdPais(resultSet.getLong("id_pais"));
                nacionalidade.setNomeNacionalidade(resultSet.getString("nome_nacionalidade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar a nacionalidade!");
        }

        return nacionalidade;
    }

}
