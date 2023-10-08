package wang.diff.scaffold.controller.crond;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.CrondApi;
import wang.diff.scaffold.service.IUserService;

@RestController
@Tag(name = "crond", description = "定时任务")
public class UserCrondController implements CrondApi {


    @Resource
    private IUserService userService;

    @Override
    public ResponseEntity<Void> syncUser() {
        userService.syncUserToEs();
        return ResponseEntity.ok().build();
    }
}
