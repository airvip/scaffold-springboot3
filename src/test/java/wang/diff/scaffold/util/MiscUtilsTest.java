package wang.diff.scaffold.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import wang.diff.scaffold.common.enums.RedisKeyInfo;
import wang.diff.scaffold.common.util.MiscUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
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
        String s1 = "11:10";
        final String s2 = new SimpleDateFormat("HH:mm").format(System.currentTimeMillis());
        String s3 = "11:20:10";
        final int i = s1.compareTo(s3);
//        int i1 = s1.compareTo(s3);
        System.out.println(i);

        /*String s = null;
        final Integer integer = Integer.valueOf(s);
        System.out.println(integer);*/

    }


    @Test
    public void test3() {
        Long l = 1693576800000L;
        final String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(l);
        System.out.println(format);

        String s = "1,2,3";
        final String[] split = s.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.stream(split).toList());

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


    @Test
    public void test6() {
        String s = "订单:${orderNo},姓名:${name}";
        final HashMap<String, String> map = new HashMap<>();
        map.put("orderNo", "123456");
        map.put("name", "张三");
        map.put("test", "test");

        final String s1 = MiscUtils.StringFormat(s, map);
        System.out.println(s1);
    }


    @Test
    public void test7() {
        DateTime startDate = DateUtil.parse("2023-10-17", "yyyy-MM-dd");
        final String beginDate = DateUtil.format(startDate, "yyyy-MM-dd");
        DateTime realEndDate = startDate.offset(DateField.MONTH, 2);
        final String endDate = DateUtil.format(realEndDate, "yyyy-MM-dd");
        System.out.println(beginDate);
        System.out.println(endDate);
    }


    @Test
    public void test8() {
        final String key = RedisKeyInfo.generateKey(RedisKeyInfo.USER_LIST, "1");
        System.out.println(key);
    }

    @Test
    public void test9() {
        int split = 15;
        for (int i = 0; i < split; i = i+7) {
            System.out.println(i/7);
        }

    }

}
