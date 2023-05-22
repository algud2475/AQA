package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Log {
    private int id;
    private String content;
    private boolean is_exception;
    private int test_id;
}
