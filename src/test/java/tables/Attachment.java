package tables;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Attachment {
    private int id;
    private String content;
    private String content_type;
    private int test_id;
}
