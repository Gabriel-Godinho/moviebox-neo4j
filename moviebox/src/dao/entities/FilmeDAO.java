package dao.entities;

import connection.DataBaseConnection;
import model.Filme;
import view.MensagensView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmeDAO {

    private final MensagensView mensagem = new MensagensView();

    public final Set<Filme> getAll() {
        Set<Filme> filmes = new HashSet<>();

        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "SELECT * FROM filmes";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar os países cadastrados!");
        }

        return filmes;
    }

    public final Filme getById(long idFilme) {
        Filme filme = new Filme();

        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "SELECT * FROM filmes WHERE id_filme = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idFilme);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                filme.setIdDiretor(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar os diretores cadastrados!");
        }

        return filme;
    }

    public final void save(Filme filme) {
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "INSERT INTO filmes(nome_filme, duracao, ano, id_diretor, id_pais, sinopse) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, filme.getNomeFilme());
            preparedStatement.setInt(2, filme.getDuracao());
            preparedStatement.setInt(3, filme.getAno());
            preparedStatement.setLong(4, filme.getIdDiretor());
            preparedStatement.setLong(5, filme.getIdPais());
            preparedStatement.setString(6, filme.getSinopse());
            preparedStatement.executeUpdate();

            mensagem.layoutMensagem("Filme adicionado com sucesso!");
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao inserir o novo filme!");
        }
    }

    public final void update(Filme filme) {
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            StringBuilder sb = new StringBuilder("UPDATE filmes SET");

            List<Object> params = new ArrayList<>();

            if (!filme.getNomeFilme().equals("0")) {
                sb.append(" nome_filme = ?,");
                params.add(filme.getNomeFilme());
            }

            if (filme.getDuracao() != 0) {
                sb.append(" duracao = ?,");
                params.add(filme.getDuracao());
            }

            if (filme.getAno() != 0) {
                sb.append(" ano = ?,");
                params.add(filme.getAno());
            }

            if (filme.getIdDiretor() != 0) {
                sb.append(" id_diretor = ?,");
                params.add(filme.getIdDiretor());
            }

            if (filme.getIdPais() != 0) {
                sb.append(" id_pais = ?,");
                params.add(filme.getIdPais());
            }

            if (!filme.getSinopse().equals("0")) {
                sb.append(" sinopse = ?,");
                params.add(filme.getSinopse());
            }

            if (sb.toString().endsWith(",")) {
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(" WHERE id_filme = ?");
            params.add(filme.getIdFilme());

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

            mensagem.layoutMensagem("Filme alterado com sucesso!");
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao alterar filme!");
        }
    }

    public final void delete(long idFilme) {
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = "DELETE FROM filmes WHERE id_filme = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idFilme);
            preparedStatement.executeUpdate();

            mensagem.layoutMensagem("Filme excluído com sucesso!");
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao deletar filme!");
        }
    }

}
