package wang.diff.scaffold.service;

import wang.diff.scaffold.controller.model.UserDTO;
import wang.diff.scaffold.controller.model.UserPageDTO;
import wang.diff.scaffold.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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

    UserDTO getByMobile(String mobile);

    UserDTO getById(Long id);

}
