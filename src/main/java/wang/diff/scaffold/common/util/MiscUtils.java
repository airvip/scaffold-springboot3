package wang.diff.scaffold.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiscUtils {
    /**
     * 生成一个 1- 100的double岁数
     * @return 一个 1- 100的double岁数
     */
    public static double generateRandomDouble() {
        return Math.random() * 100;
    }

    /**
     * 生成6位随机数字符串
     * @return 6位随机数
     */
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    };

    /**
     * 生成订单
     * @param prefix 前缀
     * @return yyyyMMdd HH:mm:ss+ 随机数 + 纳秒
     */
    public static String genOrderNo(String prefix) {
        String yyMMdd = DateUtil.format(new Date(), "yyMMddHHmmss");
        int randomInt = (int) (Math.random() * 1000);
        String randomString = Integer.toString(randomInt);
        if(randomInt < 10) {
            randomString = "00" + randomString;
        }else if (randomInt < 100) {
            randomString = "0" + randomString;
        }
        String nanoTime = Long.toString(System.nanoTime());
        String nanoTimeString = nanoTime.substring(6, 12);
        return prefix + yyMMdd + randomString + nanoTimeString;
    }

    /**
     * 转时间 unix
     *
     * @param timeStr 12:00 | 12:00:00
     * @param schDate 2022-01-01
     * @return unix
     */
    public static Long timeStr2timeUnix(String timeStr, String schDate) {
        List<String> split = StrUtil.split(timeStr, ':');
        int size = split.size();
        String datetimeString = null;
        if (size == 3) {
            datetimeString = String.format("%s %s", schDate, timeStr);
        } else if (size == 2) {
            datetimeString = String.format("%s %s:00", schDate, timeStr);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assert datetimeString != null;
        LocalDateTime dateTime = LocalDateTime.parse(datetimeString, formatter);
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * Converts a string to a date object.
     *
     * @param dateString the string to convert
     * @param format     the format of the string (e.g. "yyyy-MM-dd")
     * @return the date object
     * @throws ParseException if the string cannot be parsed as a date
     */
    public static Date stringToDate(String dateString, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateString);
    }

    /**
     * 主要用于人民币元转分
     * @return 分
     */
    public static int bigDecimal2Int(BigDecimal bigDecimal) {
        // 四舍五入
        // return bigDecimal.multiply(new
        // BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
        // 直接省略厘
        return bigDecimal.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 价格格式化
     * @param fee 费用
     * @return 价格
     */
    public static String feeFormat(Integer fee) {
        return new BigDecimal(fee).divide(new BigDecimal(100), 2, RoundingMode.UP).toString();
    }

    /**
     * 价格格式化
     * @param fee 费用
     * @return 价格
     */
    public static String feeFormat(Long fee) {
        return new BigDecimal(fee).divide(new BigDecimal(100), 2, RoundingMode.UP).toString();
    }

    /**
     * 字符串格式话
     * @param format 需要格式化的字符串 "姓名:${name},年龄:${age}"
     * @param map 传入的参数 {"name":"阿尔维奇","age":"18","sex":"男"}
     * @return 格式化后的字符串
     */
    public static String StringFormat(String format, Map<String,String> map) {
        String reg = "\\$\\{(\\w+)}";
        Pattern pattern = Pattern.compile(reg);
        final Matcher matcher = pattern.matcher(format);
        final StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            final String value = map.get(matcher.group(1));
            matcher.appendReplacement(stringBuilder, value == null ? "" : value);
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }
}
