package view;

public class MensagensView {
    public void layoutMensagem(String mensagem) {
        System.out.println("------------------------------------------------");
        System.out.println(" " + mensagem);
        System.out.println("------------------------------------------------");
    }

    public void retornarMenu() {
        System.out.print(" Para voltar ao menu tecle [0] || ");
    }

    public void limparTela(int espacos) {
        for(int i = 0; i < espacos; i++) {
            System.out.println("\n");
        }
    }
}
