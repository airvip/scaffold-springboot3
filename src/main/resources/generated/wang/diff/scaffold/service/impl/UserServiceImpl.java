package wang.diff.scaffold.service.impl;

import wang.diff.scaffold.entity.User;
import wang.diff.scaffold.dao.UserMapper;
import wang.diff.scaffold.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author airvip
 * @since 2023-04-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
