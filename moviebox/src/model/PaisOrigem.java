package model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class PaisOrigem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ToString.Exclude
    private long idPais;

    private String nomePais;

    public PaisOrigem(String nomePais) {
        this.nomePais = nomePais;
    }

}
