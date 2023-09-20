package wang.diff.scaffold.util;

import org.junit.jupiter.api.Test;
import wang.diff.scaffold.common.util.MiscUtils;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class MiscUtilsTest {

    @Test
    public void test() {
        String upperCase = "37142520000101873x".toUpperCase();
        System.out.println(upperCase);

        upperCase = "37142520000101873X".toUpperCase();
        System.out.println(upperCase);
    }


    @Test
    public void test2() {
        String s = "12:00:00";
        final String[] split = s.split(":", 1000);
        if(split.length > 2){
            s = split[0]+":"+split[1];
        }
        final int length = split.length;
        System.out.println(length);

    }


    @Test
    public void test4() {
        String s1 = "11:30";
        final String s2 = new SimpleDateFormat("HH:mm").format(System.currentTimeMillis());
        String s3 = "11:20:10";
        final int i = s1.compareTo(s2);
        int i1 = s1.compareTo(s3);
        System.out.println(i);

    }


    @Test
    public void test3() {
        Long l = 1693576800000L;
        final String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(l);
        System.out.println(format);
    }

    @Test
    public void test5() {
        final Set<String> orderSet = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            final String order = MiscUtils.genOrderNo("Air");
            System.out.println(order);
            orderSet.add(order);
        }
        System.out.println(orderSet.size());
    }

}
