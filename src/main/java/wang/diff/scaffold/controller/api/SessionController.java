package wang.diff.scaffold.controller.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.common.component.JwtTools;
import wang.diff.scaffold.controller.SessionApi;
import wang.diff.scaffold.controller.model.BaseResp;
import wang.diff.scaffold.controller.model.SessionLoginDTO;
import wang.diff.scaffold.controller.model.UserAddDTO;
import wang.diff.scaffold.controller.model.UserDTO;
import wang.diff.scaffold.service.IUserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "session")
public class SessionController implements SessionApi {


    @Resource
    private IUserService userService;

    @Override
    public ResponseEntity<BaseResp> login(SessionLoginDTO sessionLoginDTO) {

        log.info("this is a info information");
        UserDTO user = userService.getByMobile(sessionLoginDTO.getMobile(), sessionLoginDTO.getPassword());
        List<String> role = new ArrayList<>();
        role.add("ADMIN");
        String token = JwtTools.createToken(user.getId().toString(), role);
        return ResponseEntity.ok(new BaseResp().message(token));
    }

    @Override
    public ResponseEntity<UserDTO> register(UserAddDTO userAddDTO) {
        final UserDTO userDTO = userService.addOne(userAddDTO);
        return ResponseEntity.ok(userDTO);
    }
}
