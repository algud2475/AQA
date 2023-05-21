package tables;

import lombok.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Variant {
    private int id;
    @NonNull
    private boolean is_dynamic_version_in_footer = false;
    @NonNull
    private boolean use_ajax_for_tests_page;
    @NonNull
    private boolean use_frame_for_new_project;
    @NonNull
    private boolean use_new_tab_for_new_project;
    @NonNull
    private boolean use_geolocation_for_new_project;
    @NonNull
    private boolean use_alert_for_new_project;
    @NonNull
    private boolean grammar_mistake_on_save_project;
    @NonNull
    private boolean grammar_mistake_on_save_test;
}
