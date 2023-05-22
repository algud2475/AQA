package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Token {
    private int id;
    private String value;
    private int variant_id;
    private String creation_time;
}
