package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Author {
    private int id;
    private String name;
    private String login;
    private String email;
}
