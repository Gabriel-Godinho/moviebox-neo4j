package view;

import controllers.RelatoriosController;
import java.util.Scanner;

public class RelatoriosView {

    private final RelatoriosController CONTROLLER = new RelatoriosController();
    private final MensagensView mensagem = new MensagensView();

    public void mostrarOpcoes(Scanner input) {
        int escolha;

        mensagem.layoutMensagem("             Consultar Relatório               ");
        System.out.println(" Buscar por DIRETORES na Watchlist digite [1] ");
        System.out.println(" Buscar por DIRETORES na Moviebox digite [2] ");
        System.out.println(" Buscar por PAÍSES na Watchlist digite [3] ");
        System.out.println(" Buscar por PAÍSES na MovieBox digite [4] ");
        System.out.println(" Buscar por ANO ADICIONADO na watchlist [5] ");
        System.out.println("------------------------------------------------");
        System.out.print("Sua escolha: ");
        escolha = input.nextInt();

        if(escolha == 1) {
            mensagem.limparTela(6);
            relatorioFilmesNaWatchlistPorDiretor(input);
        } else if(escolha == 2) {
            mensagem.limparTela(6);
            relatorioFilmesPorDiretor(input);
        } else if(escolha == 3) {
            mensagem.limparTela(6);
            relatorioFilmesNaWatchlistPorPais(input);
        } else if (escolha == 4) {
            mensagem.limparTela(6);
            relatorioFilmesPorPais(input);
        } else {
            mensagem.limparTela(6);
            relatorioFilmesNaWatchlistPorAno(input);
        }
    }

    public void relatorioFilmesNaWatchlistPorDiretor(Scanner input) {
        mensagem.layoutMensagem("   Buscar filmes da watchlist por DIRETOR    ");
        System.out.print(" ID Diretor: ");
        long idDiretor = input.nextLong();

        CONTROLLER.listarFilmesNaWatchlistPorDiretor(idDiretor);
    }

    public void relatorioFilmesPorDiretor(Scanner input) {
        mensagem.layoutMensagem("            Buscar filmes DIRETOR            ");
        System.out.print(" ID Diretor: ");
        long idDiretor = input.nextLong();

        CONTROLLER.listarFilmesPorDiretor(idDiretor);
    }

    public void relatorioFilmesNaWatchlistPorPais(Scanner input) {
        mensagem.layoutMensagem("      Buscar filmes da watchlist por PAÍS      ");
        System.out.print(" ID País: ");
        long idPais = input.nextLong();

        CONTROLLER.listarFilmesNaWatchlistPorPais(idPais);
    }

    public void relatorioFilmesPorPais(Scanner input) {
        mensagem.layoutMensagem("            Buscar filmes por PAÍS             ");
        System.out.print(" ID País: ");
        long idPais = input.nextLong();

        CONTROLLER.listarFilmesPorPais(idPais);
    }

    public void relatorioFilmesNaWatchlistPorAno(Scanner input) {
        mensagem.layoutMensagem("      Buscar filmes da watchlist por ANO       ");
        System.out.print(" Ano em que foi adicionado: ");
        int anoInsercao = input.nextInt();

        CONTROLLER.listarFilmesNaWatchlistPorData(anoInsercao);
    }





}
