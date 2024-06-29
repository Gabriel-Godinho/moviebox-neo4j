package model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class WatchListItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long idFilme;

    private String dataInsercaoFilme;

}
