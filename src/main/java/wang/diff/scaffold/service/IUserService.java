package wang.diff.scaffold.service;

import wang.diff.scaffold.controller.model.UserAddDTO;
import wang.diff.scaffold.controller.model.UserDTO;
import wang.diff.scaffold.controller.model.UserPageDTO;
import wang.diff.scaffold.controller.model.UserUpdateDTO;
import wang.diff.scaffold.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author airvip
 * @since 2023-04-24
 */
public interface IUserService extends IService<User> {
    UserPageDTO getPage(Integer pageNum, Integer pageSize, String username);

    UserDTO getByMobile(String mobile, String password);

    UserDTO getById(Long id);

    Integer updateById(Long id, UserUpdateDTO payload);

    UserDTO addOne(UserAddDTO userAddDTO);

    List<UserDTO> searchUser(String username, String mobile);

    void syncUserToEs();

}
