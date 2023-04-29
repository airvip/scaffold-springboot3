package wang.diff.scaffold.controller.common;


import diff.wang.user.server.controller.SendMsgApi;
import diff.wang.user.server.controller.model.SendMsgOfMobileCodeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "send-msg")
public class SendMsgController implements SendMsgApi {
    @Override
    public ResponseEntity<Void> sendMsgCode(SendMsgOfMobileCodeDTO sendMsgOfMobileCodeDTO) {
        return null;
    }
}
