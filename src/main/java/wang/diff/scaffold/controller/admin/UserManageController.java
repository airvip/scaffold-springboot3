package wang.diff.scaffold.controller.admin;

import diff.wang.user.server.controller.UserManageApi;
import diff.wang.user.server.controller.model.BaseRespWithEffectiveCount;
import diff.wang.user.server.controller.model.UserExportDTO;
import diff.wang.user.server.controller.model.UserUpdateSelectedDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
