import controllers.*;
import view.*;
import java.util.Scanner;

public class Main {

    private static final MensagensView mensagem = new MensagensView();

    public static void main(String[] args)  {

        int escolha;
        Scanner input = new Scanner(System.in);

        do {
            escolha = menu(input);

            switch (escolha) {
                case 1:
                    mensagem.limparTela(6);
                    new FilmeController().listarFilmes();
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 2:
                    mensagem.limparTela(6);
                    new FilmeView().escolherAcao(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 3:
                    mensagem.limparTela(10);
                    new FilmeView().excluir(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 4:
                    mensagem.limparTela(6);
                    new DiretorController().listarDiretores();
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 5:
                    mensagem.limparTela(6);
                    new DiretoresView().escolherAcao(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 6:
                    mensagem.limparTela(6);
                    new PaisController().listarPaises();
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 7:
                    mensagem.limparTela(6);
                    new PaisesView().escolherAcao(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 8:
                    mensagem.limparTela(6);
                    new WatchlistController().mostrar();
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 9:
                    mensagem.limparTela(6);
                    new WatchlistView().escolherAcao(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 10:
                    mensagem.limparTela(6);
                    new RelatoriosView().mostrarOpcoes(input);
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
                    break;
                case 11:
                    System.exit(0);
                    break;
                default:
                    mensagem.retornarMenu();
                    escolha = input.nextInt();
            }
        } while (escolha >= 0);
        input.close();
    }

    private static int menu(Scanner input) {
        mensagem.layoutMensagem("                  Filmoteca                  ");
        System.out.println(" 1. Listar filmes");
        System.out.println(" 2. Cadastrar/Editar filme");
        System.out.println(" 3. Excluir filme");
        System.out.println(" 4. Listar diretores");
        System.out.println(" 5. Cadastrar/Editar diretor");
        System.out.println(" 6. Listar países");
        System.out.println(" 7. Cadastrar/Editar país");
        System.out.println(" 8. Ver watchlist");
        System.out.println(" 9. Editar watchlist");
        System.out.println(" 10. Relatórios");
        System.out.println(" 11. Sair");
        System.out.println("------------------------------------------------");
        System.out.print(" Escolha uma opção: ");

        return input.nextInt();
    }
}
