package controllers;

import dao.entities.DiretorDAO;
import dao.entities.NacionalidadeDAO;
import model.Diretor;
import view.MensagensView;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DiretorController {

    private final DiretorDAO DIRETOR_DAO = new DiretorDAO();
    private final NacionalidadeDAO NACIONALIDADE_DAO = new NacionalidadeDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();

    public void cadastrarDiretor(String nomeDiretor, long idNacionalidade) {
        Diretor diretor = new Diretor(nomeDiretor, idNacionalidade);

        diretor.setNomeDiretor(nomeDiretor);
        diretor.setIdNacionalidade(idNacionalidade);

        DIRETOR_DAO.save(diretor);
    }

    public void listarDiretores() {
        MENSAGEM_VIEW.layoutMensagem("                   DIRETORES                   ");

        Set<Diretor> diretoresOrdenados = DIRETOR_DAO.getAll().stream().sorted(Comparator.comparing(Diretor::getIdDiretor)).collect(Collectors.toCollection(LinkedHashSet::new));
        for (Diretor diretor : diretoresOrdenados) {
            System.out.println(" ID: " + diretor.getIdDiretor());
            System.out.println(" Nome: " + diretor.getNomeDiretor());
            System.out.println(" Nacionalidade: " + NACIONALIDADE_DAO.getById(diretor.getIdNacionalidade()).getNomeNacionalidade());
            System.out.println("------------------------------------------------");
        }
    }

    public void editarDiretor(int idDiretor, String nomeDiretor, long idNacionalidade) {
        Diretor diretor = new Diretor(idDiretor, nomeDiretor, idNacionalidade);

        DIRETOR_DAO.update(diretor);
    }
}
