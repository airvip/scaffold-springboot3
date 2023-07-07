package wang.diff.scaffold.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wang.diff.scaffold.controller.PingApi;
import wang.diff.scaffold.dto.request.KafkaSyncHelloReqDTO;
import wang.diff.scaffold.dto.request.KafkaSyncSayReqDTO;
import wang.diff.scaffold.producer.kafka.SyncKafkaProducer;

import java.util.Map;

@Slf4j
@RestController
@Tag(name = "ping")
public class PingController implements PingApi {

    @Resource
    private SyncKafkaProducer syncKafkaProducer;

    @Resource
    private RestTemplate restTemplate;

    private String urlForHitokoto = "https://v1.hitokoto.cn/";

    @Override
    public ResponseEntity<String> getSentence(String encode) {
        String url = String.format("%s?c=f&encode=%s", urlForHitokoto, encode);
        Map forObject = restTemplate.getForObject(url, Map.class);

        log.info("forObject:{}",forObject);
        return ResponseEntity.ok("ok");
    }

    @Override
    public ResponseEntity<String> ping(String name) {

        val kafkaSyncHelloReqDTO = new KafkaSyncHelloReqDTO();
        kafkaSyncHelloReqDTO.setMsg("hello");
        kafkaSyncHelloReqDTO.setCode(1);
        val send = syncKafkaProducer.send(kafkaSyncHelloReqDTO);
        log.info("send:{}",send);

        val kafkaSyncSayReqDTO = new KafkaSyncSayReqDTO();
        kafkaSyncSayReqDTO.setMsg("say");
        kafkaSyncSayReqDTO.setCode(1);
        val send1 = syncKafkaProducer.send(kafkaSyncSayReqDTO);
        log.info("send1:{}",send1);

        return ResponseEntity.ok("ping pong");
    }
}
