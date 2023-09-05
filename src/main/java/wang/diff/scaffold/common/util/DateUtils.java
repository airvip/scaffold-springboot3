package wang.diff.scaffold.common.util;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 将字符串格式时间解析成日期
     * @param dateStr 日期格式字符串
     * @param format 日期格式
     * @return 日期
     */
    @SneakyThrows
    public static Date parseDate(String dateStr, String format)  {
        if(format == null || "".equals(format) || format.length() == 0) {
            format = "yyyy-MM-dd";
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(dateStr);
    }
}
