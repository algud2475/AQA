package tests;

import tables.Variant;

public class DbTest {
    public static void main(String[] args) {
        Variant variant = new Variant();
        System.out.println(variant.is_dynamic_version_in_footer());
        System.out.println(variant.isUse_ajax_for_tests_page());
    }
}
