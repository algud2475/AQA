package tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Test {
    private int id;
    private String name;
    private int status_id;
    private String method_name;
    private int project_id;
    private int session_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String env;
    private String browser;
    private int author_id;
}
