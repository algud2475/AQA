package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Session {
    private int id;
    private String session_key;
    private String created_time;
    private int build_number;
}
