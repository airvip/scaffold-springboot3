package wang.diff.scaffold.util;

import org.junit.jupiter.api.Test;

public class MiscUtilsTest {

    @Test
    public void test() {
        String upperCase = "37142520000101873x".toUpperCase();
        System.out.println(upperCase);

        upperCase = "37142520000101873X".toUpperCase();
        System.out.println(upperCase);
    }
}
