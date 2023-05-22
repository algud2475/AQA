package tables;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Variant {
    private int id;
    private boolean is_dynamic_version_in_footer = false;
    private boolean use_ajax_for_tests_page = false;
    private boolean use_frame_for_new_project = false;
    private boolean use_new_tab_for_new_project = false;
    private boolean use_geolocation_for_new_project = false;
    private boolean use_alert_for_new_project = false;
    private boolean grammar_mistake_on_save_project = false;
    private boolean grammar_mistake_on_save_test = false;
}
