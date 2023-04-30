package wang.diff.scaffold.controller.common;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.SendMsgApi;
import wang.diff.scaffold.controller.model.SendMsgOfMobileCodeDTO;

@RestController
@Tag(name = "send-msg")
public class SendMsgController implements SendMsgApi {
    @Override
    public ResponseEntity<Void> sendMsgCode(SendMsgOfMobileCodeDTO sendMsgOfMobileCodeDTO) {
        return null;
    }
}
