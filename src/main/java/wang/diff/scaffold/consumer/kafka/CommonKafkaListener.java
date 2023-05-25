package wang.diff.scaffold.consumer.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import wang.diff.scaffold.common.constant.KafkaTopicConfig;
import wang.diff.scaffold.common.util.JacksonUtils;
import wang.diff.scaffold.dto.SendVerificationCodeDTO;

@Slf4j
@Component
public class CommonKafkaListener {
    private static final String GROUP_ID = "spring-kafka";

    @KafkaListener(topics = KafkaTopicConfig.SEND_VERIFICATION_CODE, groupId = GROUP_ID)
//    @KafkaListener(topics = KafkaTopicConfig.SEND_VERIFICATION_CODE)
    public void processMessageSms(String message) {
        log.info("Kafka 消费者：接收到发送消息请求,消息内容:{}", message);
        SendVerificationCodeDTO parse = JacksonUtils.parse(message, SendVerificationCodeDTO.class);
        log.info("Kafka 消费者：发送短信验证码的数据,mobile:{},code:{}",parse.getMobile(),parse.getCode());
    }


    /**
     * 哈哈，感觉过度封装了，直接用也是一句话
     * @param message
     * @param clazz
     * @return
     * @param <T>
     */
    private <T> T parse(String message, Class<T> clazz) {
        return JacksonUtils.parse(message,clazz);
    }

}
