package wang.diff.scaffold.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.UserApi;
import wang.diff.scaffold.controller.model.*;
import wang.diff.scaffold.service.IUserService;

@RestController
@Tag(name="user")
public class UserController implements UserApi {

    @Resource
    private IUserService userService;

    @Override
    public ResponseEntity<UserDTO> addOne(UserAddDTO userAddDTO) {
        return null;
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> deleteById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        final UserDTO userDTO = userService.getById(id);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserPageDTO> getPage(Integer pageNum, Integer pageSize, String userName) {
        UserPageDTO page = userService.getPage(pageNum, pageSize, userName);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateById(Long id, UserUpdateDTO userUpdateDTO) {
        return null;
    }
}
