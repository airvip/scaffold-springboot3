package wang.diff.scaffold.controller.api;

import diff.wang.user.server.controller.SessionApi;
import diff.wang.user.server.controller.model.BaseResp;
import diff.wang.user.server.controller.model.SessionLoginDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "session")
public class SessionController implements SessionApi {
    @Override
    public ResponseEntity<BaseResp> login(SessionLoginDTO sessionLoginDTO) {
        return null;
    }
}
