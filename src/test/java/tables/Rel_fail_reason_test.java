package tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rel_fail_reason_test {
    private int id;
    private int fail_reason_id;
    private int test_id;
    private String comment;
}
