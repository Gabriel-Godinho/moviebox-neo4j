package view;

import controllers.FilmeController;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmeView {

    private final FilmeController CONTROLLER = new FilmeController();
    private final MensagensView mensagem = new MensagensView();

    public void escolherAcao(Scanner input) {
        int escolha;

        mensagem.layoutMensagem("           Filmes - Cadastro/Edição            ");
        System.out.println(" Para CADASTRAR digite [1] ");
        System.out.println(" Para EDITAR digite [2] ");
        System.out.println("------------------------------------------------");
        System.out.print("Sua escolha: ");
        escolha = input.nextInt();

        if(escolha == 1) cadastro(input);
        else edicao(input);
    }

    private void cadastro(Scanner input) {

        mensagem.limparTela(5);
        mensagem.layoutMensagem("               Filmes - Cadastro               ");
        System.out.println(" Preencha os dados do filme que deseja inserir: ");

        ArrayList<Object> dados = formulario(input);

        CONTROLLER.cadastrarFilme((String)dados.getFirst(),
                                    (int)dados.get(1),
                                    (int)dados.get(2),
                                    (int)dados.get(3),
                                    (int)dados.get(4),
                                    (String)dados.getLast());
    }

    public void edicao(Scanner input) {

        mensagem.limparTela(10);
        mensagem.layoutMensagem("               Filmes - Edição                ");
        System.out.print(" ID do filme que deseja editar: ");
        int idFilme = input.nextInt();

        mensagem.limparTela(10);
        System.out.println("------------------------------------------------");
        System.out.println(" Preencha somente os campos que deseja editar e ");
        System.out.println(" marque o restante com [0]: ");

        ArrayList<Object> dados = formulario(input);

        CONTROLLER.editarFilme(idFilme,
                                (String)dados.getFirst(),
                                (int)dados.get(1),
                                (int)dados.get(2),
                                (int)dados.get(3),
                                (int)dados.get(4),
                                (String)dados.getLast());
    }

    private ArrayList<Object> formulario(Scanner input) {
        var dados = new ArrayList<>();

        System.out.println("------------------------------------------------");
        System.out.print(" Nome do filme: ");
        input.nextLine();
        String nomeFilme = input.nextLine();
        dados.add(nomeFilme);

        System.out.println("------------------------------------------------");
        System.out.print(" Duração em minutos: ");
        int duracao = input.nextInt();
        dados.add(duracao);

        System.out.println("------------------------------------------------");
        System.out.print(" Ano de lançamento: ");
        int ano = input.nextInt();
        dados.add(ano);

        System.out.println("------------------------------------------------");
        System.out.print(" ID do diretor: ");
        int idDiretor = input.nextInt();
        dados.add(idDiretor);

        System.out.println("------------------------------------------------");
        System.out.print(" ID do país de origem: ");
        int idPais = input.nextInt();
        dados.add(idPais);

        System.out.println("------------------------------------------------");
        System.out.print(" Sinopse: ");
        input.nextLine();
        String sinopse = input.nextLine();
        dados.add(sinopse);

        return dados;
    }

    public void excluir(Scanner input) {
        mensagem.layoutMensagem("               Filmes - Exclusão               ");
        System.out.print(" ID do filme que deseja excluir: ");
        int idFilme = input.nextInt();

        CONTROLLER.excluirFilme(idFilme);
    }

}
