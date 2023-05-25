package wang.diff.scaffold.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import wang.diff.scaffold.common.constant.KafkaTopicConfig;

import java.time.Duration;

@Configuration
public class KafkaSyncProducerConfig {

    /**
     * 同步kafka需要 ReplyingKafkaTemplate 并制定 repliesContainer
     * @param producerFactory 生产者工厂
     * @param repliesContainer 响应容器
     * @return ReplyingKafkaTemplate
     */
    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyingTemplate(
            ProducerFactory<String, String> producerFactory,
            KafkaMessageListenerContainer<String, String> repliesContainer) {
        ReplyingKafkaTemplate<String,String,String> template = new ReplyingKafkaTemplate<>(producerFactory, repliesContainer);
        //同步相应超时时间：30s
        template.setDefaultReplyTimeout(Duration.ofSeconds(30));
        return template;
    }


    /**
     * 指定 consumer 返回数据到指定的 topic
     * @return 消息容器
     */
    @Bean
    public KafkaMessageListenerContainer<String, String> repliesContainer(ConsumerFactory<String, String> cf) {
        val containerProperties = new ContainerProperties(KafkaTopicConfig.KafkaSyncMessage.RESPONSE_TOPIC_NAME,
                KafkaTopicConfig.KafkaSyncMessageSay.RESPONSE_TOPIC_NAME);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }


}