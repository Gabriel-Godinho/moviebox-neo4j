package view;

import controllers.WatchlistController;
import java.util.Scanner;

public class WatchlistView {

    private final WatchlistController CONTROLLER = new WatchlistController();
    private final MensagensView mensagem = new MensagensView();

    public void escolherAcao(Scanner input) {
        int escolha;

        mensagem.layoutMensagem("               Editar Watchlist                ");
        System.out.println(" Para ADICIONAR um filme digite [1] ");
        System.out.println(" Para REMOVER um filme digite [2] ");
        System.out.println("------------------------------------------------");
        System.out.print(" Sua escolha: ");
        escolha = input.nextInt();

        if(escolha == 1) adicionar(input);
        else remover(input);
    }

    public void adicionar(Scanner input) {

        mensagem.limparTela(6);
        mensagem.layoutMensagem("            Watchlist - Adicionar              ");
        System.out.print(" ID do filme que deseja adicionar: ");
        int idFilme = input.nextInt();

        CONTROLLER.inserirFilme(idFilme);
    }

    public void remover(Scanner input) {

        mensagem.limparTela(6);
        mensagem.layoutMensagem("             Watchlist - Remover               ");
        System.out.print(" ID do filme que deseja remover: ");
        int idFilme = input.nextInt();

        CONTROLLER.removerFilme(idFilme);
    }

}
