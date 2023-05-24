package tests;

import tables.Variant;
import utils.DbUtil;

import javax.sql.DataSource;

public class DbTest {
    public static void main(String[] args) {
        DataSource dataSource = DbUtil.buildDataSource();
    }
}
