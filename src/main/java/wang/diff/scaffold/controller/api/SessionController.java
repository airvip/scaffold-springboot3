package wang.diff.scaffold.controller.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.SessionApi;
import wang.diff.scaffold.controller.model.BaseResp;
import wang.diff.scaffold.controller.model.SessionLoginDTO;

@RestController
@Tag(name = "session")
public class SessionController implements SessionApi {
    @Override
    public ResponseEntity<BaseResp> login(SessionLoginDTO sessionLoginDTO) {
        return null;
    }
}
