package wang.diff.scaffold.controller.manage;


import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.UserManageApi;
import wang.diff.scaffold.controller.model.BaseRespWithEffectiveCount;
import wang.diff.scaffold.controller.model.UserExportDTO;
import wang.diff.scaffold.controller.model.UserUpdateSelectedDTO;
import wang.diff.scaffold.dao.UserMapper;
import wang.diff.scaffold.entity.User;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "user-manage")
public class UserManageController implements UserManageApi {

    @Resource
    private HttpServletResponse httpServletResponse;

    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseEntity<Void> exportExcel(UserExportDTO userExportDTO) {
        try {
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            httpServletResponse.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("test", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            httpServletResponse.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            final List<User> users = userMapper.selectList(null);
            EasyExcel.write(httpServletResponse.getOutputStream(), User.class).sheet("模板").doWrite(users);
        }catch (IOException e) {
            log.info("export excel error {}", e.getMessage());
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<BaseRespWithEffectiveCount> updateByIdsManage(UserUpdateSelectedDTO userUpdateSelectedDTO) {
        return null;
    }

}
