package dao.entities;

import connection.DataBaseConnection;
import model.Diretor;
import view.MensagensView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiretorDAO {

    private final MensagensView mensagem = new MensagensView();

    public final Set<Diretor> getAll() {
        Set<Diretor> diretores = new HashSet<>();

        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "SELECT * FROM diretores";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Diretor diretor = new Diretor();
                diretor.setIdDiretor(resultSet.getLong("id_diretor"));
                diretor.setNomeDiretor(resultSet.getString("nome_diretor"));
                diretor.setIdNacionalidade(resultSet.getLong("id_nacionalidade"));
                diretores.add(diretor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os países cadastrados!");
        }

        return diretores;
    }

    public final Diretor getById(long idDiretor) {
        Diretor diretor = new Diretor();

        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "SELECT * FROM diretores WHERE id_diretor = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idDiretor);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                diretor.setIdDiretor(rs.getLong("id_diretor"));
                diretor.setNomeDiretor(rs.getString("nome_diretor"));
                diretor.setIdNacionalidade(rs.getLong("id_nacionalidade"));
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar os diretores cadastrados!");
        }

        return diretor;
    }

    public final void save(Diretor diretor) {
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "INSERT INTO diretores(nome_diretor, id_nacionalidade) VALUES(?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, diretor.getNomeDiretor());
            preparedStatement.setLong(2, diretor.getIdNacionalidade());
            preparedStatement.executeUpdate();

            mensagem.layoutMensagem("Diretor cadastrado com sucesso!");
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao cadastrar o novo diretor!");
        }
    }

    public final void update(Diretor diretor) {
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            StringBuilder sb = new StringBuilder("UPDATE diretores SET");

            List<Object> params = new ArrayList<>();

            if (!diretor.getNomeDiretor().equals("0")) {
                sb.append(" nome_diretor = ?,");
                params.add(diretor.getNomeDiretor());
            }

            if (diretor.getIdNacionalidade() != 0) {
                sb.append(" id_nacionalidade = ?,");
                params.add(diretor.getIdNacionalidade());
            }

            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(" WHERE id_diretor = ?");
            params.add(diretor.getIdDiretor());

            PreparedStatement preparedStatement = conn.prepareStatement(sb.toString());

            for (int i = 0; i < params.size(); i++) {
                Object value = params.get(i);
                switch (value) {
                    case String str -> preparedStatement.setString(i + 1, str);
                    case Integer integer -> preparedStatement.setInt(i + 1, integer);
                    case Long longInt -> preparedStatement.setLong(i + 1, longInt);
                    default -> {}
                }
            }

            preparedStatement.executeUpdate();

            mensagem.layoutMensagem("Diretor atualizado com sucesso!");
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao atualizar o diretor!");
        }
    }

}
