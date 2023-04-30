package wang.diff.scaffold.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.PingApi;

@RestController
@Tag(name = "ping")
public class PingController implements PingApi {
    @Override
    public ResponseEntity<String> ping(String name) {
        return null;
    }
}
