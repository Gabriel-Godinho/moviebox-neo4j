package controllers;

import dao.entities.DiretorDAO;
import dao.entities.FilmeDAO;
import dao.entities.PaisDAO;
import dao.entities.WatchListDAO;
import model.Filme;
import view.MensagensView;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilmeController {

    private final MensagensView MENSAGEM_VIEW = new MensagensView();
    private final FilmeDAO FILME_DAO = new FilmeDAO();
    private final WatchListDAO WATCHLIST_DAO = new WatchListDAO();
    private final PaisDAO PAIS_DAO = new PaisDAO();
    private final DiretorDAO DIRETOR_DAO = new DiretorDAO();

    public void cadastrarFilme(String nomeFilme, int duracao, int ano, long idDiretor, long idPais, String sinopse) {
        Filme filme = new Filme(nomeFilme, duracao, ano, idDiretor, idPais, sinopse);

        filme.setNomeFilme(nomeFilme);
        filme.setDuracao(duracao);
        filme.setAno(ano);
        filme.setIdDiretor(idDiretor);
        filme.setIdPais(idPais);
        filme.setSinopse(sinopse);

        FILME_DAO.save(filme);
    }

    public void excluirFilme(int idFilme) {
        if (WATCHLIST_DAO.existsFilmeInWatchList(idFilme)) {
            WATCHLIST_DAO.deleteWatchListItem(idFilme);
        }

        FILME_DAO.delete(idFilme);
    }

    public void listarFilmes() {
        MENSAGEM_VIEW.layoutMensagem("                    FILMES                     ");

        Set<Filme> filmesOrdenados = FILME_DAO.getAll().stream().sorted(Comparator.comparing(Filme::getIdFilme)).collect(Collectors.toCollection(LinkedHashSet::new));
        for (Filme filme : filmesOrdenados) {
            System.out.println(" ID: " + filme.getIdFilme());
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Diretor: " + DIRETOR_DAO.getById(filme.getIdDiretor()).getNomeDiretor());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" País de origem: " + PAIS_DAO.getById(filme.getIdPais()).getNomePais());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println("------------------------------------------------");
        }
    }

    public void editarFilme(int idFilme, String nomeFilme, int duracao, int ano, int idDiretor, int idPais, String sinopse) {
        Filme filme = new Filme(idFilme, nomeFilme, duracao, ano, idDiretor, idPais, sinopse);

        FILME_DAO.update(filme);
    }
}
