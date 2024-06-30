package view;

import controllers.DiretorController;
import java.util.ArrayList;
import java.util.Scanner;

public class DiretoresView {

    private final DiretorController CONTROLLER = new DiretorController();
    private final MensagensView mensagem = new MensagensView();

    public void escolherAcao(Scanner input) {
        int escolha;

        mensagem.layoutMensagem("          Diretores - Cadastro/Edição          ");
        System.out.println(" Para CADASTRAR digite [1] ");
        System.out.println(" Para EDITAR digite [2] ");
        System.out.println("------------------------------------------------");
        System.out.print("Sua escolha: ");
        escolha = input.nextInt();

        if(escolha == 1) cadastro(input);
        else edicao(input);
    }

    public void cadastro(Scanner input) {

        mensagem.limparTela(5);
        mensagem.layoutMensagem("             Diretores - Cadastro              ");
        System.out.println(" Preencha os dados do diretor: ");

        ArrayList<Object> dados = formulario(input);

        CONTROLLER.cadastrarDiretor((String)dados.getFirst(), (Long) dados.get(1));
    }

    public void edicao(Scanner input) {

        mensagem.limparTela(6);
        mensagem.layoutMensagem("              Diretores - Edição               ");
        System.out.print(" ID do diretor que deseja editar: ");
        int idDiretor = input.nextInt();

        mensagem.limparTela(10);
        mensagem.edicao();

        ArrayList<Object> dados = formulario(input);

        CONTROLLER.editarDiretor(idDiretor, (String)dados.getFirst(), (Long)dados.get(1));
    }

    private ArrayList<Object> formulario(Scanner input) {
        var dados = new ArrayList<>();

        System.out.println("----------------------------------------------");
        System.out.print(" Nome do diretor: ");
        input.nextLine();
        String nomeDiretor = input.nextLine();
        dados.add(nomeDiretor);

        System.out.println("----------------------------------------------");
        System.out.print(" ID do país de origem: ");
        long idPais = input.nextLong();
        dados.add(idPais);

        return dados;
    }
}
