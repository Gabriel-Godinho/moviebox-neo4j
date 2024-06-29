package view;

import controllers.PaisController;
import java.util.Scanner;

public class PaisesView {

    private final PaisController CONTROLLER = new PaisController();
    private final MensagensView mensagem = new MensagensView();

    public void escolherAcao(Scanner input) {
        int escolha;

        mensagem.layoutMensagem("           Países - Cadastro/Edição            ");
        System.out.println(" Para CADASTRAR digite [1] ");
        System.out.println(" Para EDITAR digite [2] ");
        System.out.println("------------------------------------------------");
        System.out.print("Sua escolha: ");
        escolha = input.nextInt();

        if(escolha == 1) cadastro(input);
        else edicao(input);
    }

    public void cadastro(Scanner input) {

        mensagem.limparTela(7);
        mensagem.layoutMensagem("               Países - Cadastro               ");

        String nomePais = formulario(input);

        CONTROLLER.cadastrarPais(nomePais);
    }

    public void edicao(Scanner input) {

        mensagem.limparTela(6);
        mensagem.layoutMensagem("                Países - Edição                ");
        System.out.print(" ID do país que deseja editar: ");
        int idPais = input.nextInt();
        System.out.println("------------------------------------------------");

        String nomePais = formulario(input);

        CONTROLLER.editarPais(idPais, nomePais);
    }

    private String formulario(Scanner input) {
        System.out.print(" Nome do país: ");
        input.nextLine();

        return input.nextLine();
    }
}
