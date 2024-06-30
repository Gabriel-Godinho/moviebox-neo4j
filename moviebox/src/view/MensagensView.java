package view;

public class MensagensView {
    public void layoutMensagem(String mensagem) {
        System.out.println("------------------------------------------------");
        System.out.println(" " + mensagem);
        System.out.println("------------------------------------------------");
    }

    public void retornarMenu() {
        System.out.print(" Para voltar ao menu escreva [-1] || ");
    }

    public void edicao() {
        System.out.println("------------------------------------------------");
        System.out.println(" Preencha somente os campos que deseja editar e ");
        System.out.println(" marque o restante com [-1]: ");
    }

    public void limparTela(int espacos) {
        for(int i = 0; i < espacos; i++) {
            System.out.println("\n");
        }
    }
}
