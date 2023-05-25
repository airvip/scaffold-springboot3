package wang.diff.scaffold.common.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Slf4j
public class JacksonUtils {

    private static ObjectMapper om = new ObjectMapper();

    static {

        // 对象的所有字段全部列入，还是其他的选项，可以忽略null等
        om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 设置Date类型的序列化及反序列化格式
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略空Bean转json的错误
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略未知属性，防止json字符串中存在，java对象中不存在对应属性的情况出现错误
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
        om.registerModule(new JavaTimeModule());
    }


    /**
     * 对象转json
     * @param <T>
     * @param obj
     * @return
     */
    public static <T> String toJson(T obj) {
        String json = null;
        if (obj != null) {
            try {
                json = om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return json;
    }


    /**
     * json字符串转对象
     * @param <T> 泛型
     * @param json json字符串
     * @param clazz 对象类
     * @return 对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        return parse(json, clazz, null);
    }


    /**
     * json字符串转对象
     * @param <T> 泛型
     * @param json json字符串
     * @param type 对象类型
     * @return 对象
     */
    public static <T> T parse(String json, TypeReference<T> type) {
        return parse(json, null, type);
    }


    /**
     * json 转对象解析器,
     * 参数clazz和type必须一个为null，另一个不为null
     * @param <T>   泛型
     * @param json  原始json字符串
     * @param clazz 对象
     * @param type  对象类型
     * @return 对象
     */
    private static <T> T parse(String json, Class<T> clazz, TypeReference<T> type) {
        T obj = null;
        if (StringUtils.hasLength(json)) {
            try {
                if (clazz != null) {
                    obj = om.readValue(json, clazz);
                } else {
                    obj = (T) om.readValue(json, type);
                }
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return obj;
    }

}

