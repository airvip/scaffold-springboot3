package wang.diff.scaffold.controller.admin;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.UserManageApi;
import wang.diff.scaffold.controller.model.BaseRespWithEffectiveCount;
import wang.diff.scaffold.controller.model.UserExportDTO;
import wang.diff.scaffold.controller.model.UserUpdateSelectedDTO;

@RestController
@Tag(name = "manage")
public class UserManageController implements UserManageApi {
    @Override
    public ResponseEntity<Void> exportExcel(UserExportDTO userExportDTO) {
        return null;
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateByIdsManage(UserUpdateSelectedDTO userUpdateSelectedDTO) {
        return null;
    }

}
