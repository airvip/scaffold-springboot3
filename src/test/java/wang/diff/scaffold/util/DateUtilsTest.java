package wang.diff.scaffold.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void test() {
        final Date date = DateUtils.parseDate("2023-08-06", "yyyy-MM-dd");
        System.out.println(date);
    }
}
