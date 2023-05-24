package tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Api_key {
    private int id;
    private String key;
    private String key_info;

    public static List<String> getFields() {
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("key");
        list.add("key_info");
        return list;
    }
}
