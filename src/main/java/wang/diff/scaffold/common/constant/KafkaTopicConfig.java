package wang.diff.scaffold.common.constant;

public class KafkaTopicConfig {
    private KafkaTopicConfig() {
    }

    public static final String SEND_VERIFICATION_CODE = "SendVerificationCode";
    public static final String SEND_SHORT_MSG = "SendShortMsg";


    public static class KafkaSyncMessageSay {
        private KafkaSyncMessageSay() {}
        public static final String REQUEST_TOPIC_NAME = "KafkaSyncMessageSayRequest";
        public static final String RESPONSE_TOPIC_NAME = "KafkaSyncMessageSayResponse";
    }


    public static class KafkaSyncMessage {
        private KafkaSyncMessage() {}
        public static final String REQUEST_TOPIC_NAME = "KafkaSyncMessageRequest";
        public static final String RESPONSE_TOPIC_NAME = "KafkaSyncMessageResponse";
    }


}