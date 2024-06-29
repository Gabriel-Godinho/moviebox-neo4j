package controllers;

import dao.entities.DiretorDAO;
import dao.entities.PaisDAO;
import dao.entities.WatchListDAO;
import dao.relatorios.RelatoriosDAO;
import model.Filme;
import view.MensagensView;

public class RelatoriosController {

    private final RelatoriosDAO RELATORIOS_DAO = new RelatoriosDAO();
    private final DiretorDAO DIRETOR_DAO = new DiretorDAO();
    private final PaisDAO PAIS_DAO = new PaisDAO();
    private final WatchListDAO WATCHLIST_DAO = new WatchListDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();

    public void listarFilmesNaWatchlistPorDiretor(long idDiretor) {
        MENSAGEM_VIEW.limparTela(6);
        MENSAGEM_VIEW.layoutMensagem("Filmes por " + (DIRETOR_DAO.getById(idDiretor).getNomeDiretor()).toUpperCase() + " na watchlist");

        for (Filme filme : RELATORIOS_DAO.buscarFilmesNaWatchlistPorDiretor(idDiretor)) {
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" País de origem: " + PAIS_DAO.getById(filme.getIdPais()).getNomePais());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarFilmesPorDiretor(long idDiretor) {
        MENSAGEM_VIEW.limparTela(6);
        MENSAGEM_VIEW.layoutMensagem("Filmes por " + (DIRETOR_DAO.getById(idDiretor).getNomeDiretor()).toUpperCase() + " na MovieBox");

        for (Filme filme : RELATORIOS_DAO.buscarFilmesPorDiretor(idDiretor)) {
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" País de origem: " + PAIS_DAO.getById(filme.getIdPais()).getNomePais());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarFilmesNaWatchlistPorPais(long idPais) {
        MENSAGEM_VIEW.limparTela(6);
        MENSAGEM_VIEW.layoutMensagem((PAIS_DAO.getById(idPais).getNomePais()).toUpperCase() + " na sua watchlist");

        for (Filme filme : RELATORIOS_DAO.buscarFilmesNaWatchlistPorPais(idPais)) {
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Diretor: " + DIRETOR_DAO.getById(filme.getIdDiretor()).getNomeDiretor());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarFilmesPorPais(long idPais) {
        MENSAGEM_VIEW.limparTela(6);
        MENSAGEM_VIEW.layoutMensagem((PAIS_DAO.getById(idPais).getNomePais()).toUpperCase() + " na MovieBox");

        for (Filme filme : RELATORIOS_DAO.buscarFilmesPorPais(idPais)) {
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Diretor: " + DIRETOR_DAO.getById(filme.getIdDiretor()).getNomeDiretor());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println("------------------------------------------------");
        }
    }

    public void listarFilmesNaWatchlistPorData(int anoInsercao) {

        MENSAGEM_VIEW.limparTela(6);
        MENSAGEM_VIEW.layoutMensagem("Filmes adicionados na sua watchlist em " + anoInsercao);

        for (Filme filme : RELATORIOS_DAO.buscarFilmesNaWatchlistPorAnoInserido(anoInsercao)) {
            System.out.println(" Nome: " + filme.getNomeFilme());
            System.out.println(" Diretor: " + DIRETOR_DAO.getById(filme.getIdDiretor()).getNomeDiretor());
            System.out.println(" Duração: " + filme.getDuracao() + " minutos");
            System.out.println(" Ano de lançamento: " + filme.getAno());
            System.out.println(" País de origem: " + PAIS_DAO.getById(filme.getIdPais()).getNomePais());
            System.out.println(" Sinopse: " + filme.getSinopse());
            System.out.println(" Adicionado em: " + WATCHLIST_DAO.getDataInsercaoFilme(filme.getIdFilme()));
            System.out.println("------------------------------------------------");
        }
    }
}
