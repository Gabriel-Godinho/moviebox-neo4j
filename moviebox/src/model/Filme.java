package model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class Filme implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ToString.Exclude
    private long idFilme;

    private String nomeFilme;

    private int duracao;

    private int ano;

    private long idDiretor;

    private long idPais;

    private String sinopse;

    // Construtor com todos os atributos menos o id do filme
    public Filme(String nomeFilme, int duracao, int ano, long idDiretor, long idPais, String sinopse) {
        this.nomeFilme = nomeFilme;
        this.duracao = duracao;
        this.ano = ano;
        this.idDiretor = idDiretor;
        this.idPais = idPais;
        this.sinopse = sinopse;
    }

}
