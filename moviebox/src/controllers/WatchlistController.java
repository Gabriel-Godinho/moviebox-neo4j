package controllers;

import dao.entities.FilmeDAO;
import dao.entities.WatchListDAO;
import model.WatchListItem;
import view.MensagensView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class WatchlistController {

    private final WatchListDAO WATCHLIST_DAO = new WatchListDAO();
    private final FilmeDAO FILME_DAO = new FilmeDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();

    public void inserirFilme(int idFilme) {
        LocalDate localDate = LocalDate.now();
        String dataFormatada = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

        WatchListItem watchlistItem = new WatchListItem(idFilme, dataFormatada);

        WATCHLIST_DAO.saveWatchListItem(watchlistItem);
    }

    public void removerFilme(int idFilme) {
        WATCHLIST_DAO.deleteWatchListItem(idFilme);
    }

    public void mostrar() {
        MENSAGEM_VIEW.layoutMensagem("                   WATCHLIST                   ");

        List<WatchListItem> watchlistOrdenada = WATCHLIST_DAO.getWatchList().getItensWatchList().stream().sorted(Comparator.comparing(WatchListItem::getDataInsercaoFilme)).toList();
        for (WatchListItem watchlistItem : watchlistOrdenada) {
            SimpleDateFormat sdfBanco = new SimpleDateFormat("yyyy-MM-dd");
            Date dataBanco;

            try {
                dataBanco = sdfBanco.parse(watchlistItem.getDataInsercaoFilme());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String data = sdf.format(dataBanco);

            System.out.println( "ID do filme: " + watchlistItem.getIdFilme());
            System.out.println( "Filme: " + FILME_DAO.getById(watchlistItem.getIdFilme()).getNomeFilme() );
            System.out.println( "Adicionado em: " + data);
            System.out.println("------------------------------------------------");
        }
    }
}
