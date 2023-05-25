package wang.diff.scaffold.consumer.kafka;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import wang.diff.scaffold.common.constant.KafkaTopicConfig;
import wang.diff.scaffold.common.util.JacksonUtils;
import wang.diff.scaffold.dto.response.KafkaSyncHelloRespDTO;
import wang.diff.scaffold.dto.response.KafkaSyncSayRespDTO;

@Slf4j
@Component
public class SyncKafkaListener {
    @KafkaListener(topics = KafkaTopicConfig.KafkaSyncMessage.REQUEST_TOPIC_NAME)
    @SendTo
    public String consumerSync(String message){
        log.warn("Kafka 消费者：Sync 接收到发送消息请求,消息内容:{}", message);

        val kafkaSyncHelloRespDTO = new KafkaSyncHelloRespDTO();
        kafkaSyncHelloRespDTO.setMsg("this hello");
        return JacksonUtils.toJson(kafkaSyncHelloRespDTO);
    }


    @KafkaListener(topics = KafkaTopicConfig.KafkaSyncMessageSay.REQUEST_TOPIC_NAME)
    @SendTo
    public String consumerSyncSay(String message){
        log.warn("Kafka 消费者：Sync 接收到发送消息请求,消息内容:{}", message);
        val kafkaSyncSayRespDTO = new KafkaSyncSayRespDTO();
        kafkaSyncSayRespDTO.setMsg("this say");
        return JacksonUtils.toJson(kafkaSyncSayRespDTO);
    }


}

