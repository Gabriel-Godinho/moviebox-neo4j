package controllers;

import dao.entities.PaisDAO;
import model.PaisOrigem;
import view.MensagensView;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PaisController {

    private final PaisDAO PAIS_DAO = new PaisDAO();
    private final MensagensView MENSAGEM_VIEW = new MensagensView();

    public void cadastrarPais(String nomePais) {
        PaisOrigem pais = new PaisOrigem(nomePais);

        pais.setNomePais(nomePais);

        PAIS_DAO.save(pais);
    }

    public void listarPaises() {
        MENSAGEM_VIEW.layoutMensagem("                    PAÍSES                     ");

        Set<PaisOrigem> paisesOrdenados = PAIS_DAO.getAll().stream().sorted(Comparator.comparing(PaisOrigem::getIdPais)).collect(Collectors.toCollection(LinkedHashSet::new));
        for (PaisOrigem pais : paisesOrdenados) {
            System.out.println(" ID: " + pais.getIdPais());
            System.out.println(" Nome: " + pais.getNomePais());
            System.out.println("------------------------------------------------");
        }
    }

    public void editarPais(int idPais, String nomePais) {
        PaisOrigem pais = new PaisOrigem(idPais, nomePais);

        PAIS_DAO.update(pais);
    }
}
