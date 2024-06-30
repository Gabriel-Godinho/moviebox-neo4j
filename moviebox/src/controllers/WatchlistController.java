package controllers;

import dao.entities.FilmeDAO;
import dao.entities.WatchListDAO;
import model.WatchListItem;
import view.MensagensView;

import java.util.Comparator;
import java.util.List;

public class WatchlistController {

    private final WatchListDAO WATCHLIST_DAO = new WatchListDAO();
    private final FilmeDAO FILME_DAO = new FilmeDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();

    public void inserirFilme(long idFilme) {
        WatchListItem watchlistItem = new WatchListItem();
        watchlistItem.setIdFilme(idFilme);

        WATCHLIST_DAO.saveWatchListItem(watchlistItem);
    }

    public void removerFilme(long idFilme) {
        WATCHLIST_DAO.deleteWatchListItem(idFilme);
    }

    public void mostrar() {
        MENSAGEM_VIEW.layoutMensagem("                   WATCHLIST                   ");

        List<WatchListItem> watchlistOrdenada = WATCHLIST_DAO.getWatchList().getItensWatchList().stream().sorted(Comparator.comparing(WatchListItem::getDataInsercaoFilme)).toList();
        for (WatchListItem watchlistItem : watchlistOrdenada) {
            System.out.println( "ID do filme: " + watchlistItem.getIdFilme());
            System.out.println( "Filme: " + FILME_DAO.getById(watchlistItem.getIdFilme()).getNomeFilme());
            System.out.println( "Adicionado em: " + watchlistItem.getDataInsercaoFilme());
            System.out.println("------------------------------------------------");
        }
    }
}
