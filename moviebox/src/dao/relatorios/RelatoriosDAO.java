package dao.relatorios;

import connection.DataBaseConnection;
import model.Filme;
import view.MensagensView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * */
    public final Set<Filme> buscarFilmesNaWatchlistPorDiretor(long idDiretor) {
        Set<Filme> filmes = new HashSet<>();
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = """
                    SELECT * FROM filmes AS f INNER JOIN watchlist AS w
                    ON f.id_filme = w.id_filme
                    WHERE f.id_diretor = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idDiretor);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar pelo diretor na watchlist!");
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
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = """
                    SELECT * FROM filmes AS f INNER JOIN diretores AS d
                    ON f.id_diretor = d.id_diretor
                    WHERE f.id_diretor = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idDiretor);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar pelo diretor na watchlist!");
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
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = """
                    SELECT * FROM filmes AS f INNER JOIN watchlist AS w
                    ON f.id_filme = w.id_filme
                    WHERE f.id_pais = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idPais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar pelo país na watchlist!");
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
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = """
                    SELECT * FROM filmes AS f INNER JOIN paises AS p
                    ON f.id_pais = p.id_pais
                    WHERE f.id_pais = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, idPais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar os filmes pelo país inserido!");
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
    public final Set<Filme> buscarFilmesNaWatchlistPorAnoInserido(int anoInsercao) {
        Set<Filme> filmes = new HashSet<>();
        try {
            Connection conn = DataBaseConnection.getInstance().getConn();
            String sql = """
                    SELECT * FROM filmes AS f INNER JOIN watchlist AS w
                    ON f.id_filme = w.id_filme
                    WHERE DATE_PART('year', w.data_insercao_filme) = ?
                    """;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, anoInsercao);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setIdFilme(resultSet.getLong("id_filme"));
                filme.setNomeFilme(resultSet.getString("nome_filme"));
                filme.setDuracao(resultSet.getInt("duracao"));
                filme.setAno(resultSet.getInt("ano"));
                filme.setIdDiretor(resultSet.getLong("id_diretor"));
                filme.setIdPais(resultSet.getLong("id_pais"));
                filme.setSinopse(resultSet.getString("sinopse"));
                filmes.add(filme);
            }
        } catch (SQLException e) {
            mensagem.layoutMensagem("Erro ao buscar os filmes pelo ano inserido!");
        }

        return filmes;
    }
}
