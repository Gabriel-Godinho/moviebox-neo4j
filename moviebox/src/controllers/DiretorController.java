package controllers;

import dao.entities.DiretorDAO;
import dao.entities.PaisDAO;
import model.Diretor;
import view.MensagensView;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DiretorController {

    private final DiretorDAO DIRETOR_DAO = new DiretorDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();
    private final PaisDAO PAIS_DAO = new PaisDAO();

    public void cadastrarDiretor(String nomeDiretor) {
        Diretor diretor = new Diretor(nomeDiretor);

        diretor.setNomeDiretor(nomeDiretor);

        DIRETOR_DAO.save(diretor);
    }

    public void listarDiretores() {
        MENSAGEM_VIEW.layoutMensagem("                   DIRETORES                   ");

        Set<Diretor> diretoresOrdenados = DIRETOR_DAO.getAll().stream().sorted(Comparator.comparing(Diretor::getIdDiretor)).collect(Collectors.toCollection(LinkedHashSet::new));
        for (Diretor diretor : diretoresOrdenados) {
            System.out.println(" ID: " + diretor.getIdDiretor());
            System.out.println(" Nome: " + diretor.getNomeDiretor());
            System.out.println(" País de origem: " + PAIS_DAO.getByIdDiretor(diretor.getIdDiretor()).getNomePais());
            System.out.println("------------------------------------------------");
        }
    }

    public void editarDiretor(int idDiretor, String nomeDiretor) {
        Diretor diretor = new Diretor(idDiretor, nomeDiretor);

        DIRETOR_DAO.update(diretor);
    }
}
