package model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class Nacionalidade implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long idNaciolidade;

    private long idPais;

    private String nomeNacionalidade;

}
