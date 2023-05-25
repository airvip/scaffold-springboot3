package wang.diff.scaffold.controller.common;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.common.util.MiscUtils;
import wang.diff.scaffold.controller.SendMsgApi;
import wang.diff.scaffold.controller.model.SendMsgOfMobileCodeDTO;
import wang.diff.scaffold.dto.SendVerificationCodeDTO;
import wang.diff.scaffold.producer.kafka.CommonKafkaProducer;

@Slf4j
@RestController
@Tag(name = "send-msg")
public class SendMsgController implements SendMsgApi {

    @Resource
    private CommonKafkaProducer commonKafkaProducer;

    @Override
    public ResponseEntity<Void> sendMsgCode(SendMsgOfMobileCodeDTO sendMsgOfMobileCodeDTO) {
        log.info("to send msg >>> {}", sendMsgOfMobileCodeDTO.getMobile());
        SendVerificationCodeDTO sendVerificationCodeDTO = new SendVerificationCodeDTO();
        sendVerificationCodeDTO.setMobile(sendMsgOfMobileCodeDTO.getMobile());
        sendVerificationCodeDTO.setCode(MiscUtils.generateRandomString());
        commonKafkaProducer.send(sendVerificationCodeDTO);
        return ResponseEntity.ok().build();
    }
}
