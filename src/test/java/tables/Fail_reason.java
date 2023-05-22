package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Fail_reason {
    private int id;
    private String name;
    private boolean is_global;
    private boolean is_unremovable;
    private boolean is_unchangeable;
    private boolean is_stats_ignored;
    private boolean is_session;
    private boolean is_test;
}
