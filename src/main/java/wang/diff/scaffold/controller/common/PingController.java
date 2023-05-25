package wang.diff.scaffold.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.PingApi;
import wang.diff.scaffold.dto.request.KafkaSyncHelloReqDTO;
import wang.diff.scaffold.dto.request.KafkaSyncSayReqDTO;
import wang.diff.scaffold.producer.kafka.SyncKafkaProducer;

@Slf4j
@RestController
@Tag(name = "ping")
public class PingController implements PingApi {

    @Resource
    private SyncKafkaProducer syncKafkaProducer;

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
