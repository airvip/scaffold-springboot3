package wang.diff.scaffold.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wang.diff.scaffold.common.enums.CommonStatus;
import wang.diff.scaffold.common.exception.BizException;
import wang.diff.scaffold.controller.model.UserAddDTO;
import wang.diff.scaffold.controller.model.UserDTO;
import wang.diff.scaffold.controller.model.UserPageDTO;
import wang.diff.scaffold.controller.model.UserUpdateDTO;
import wang.diff.scaffold.dao.UserMapper;
import wang.diff.scaffold.entity.User;
import wang.diff.scaffold.service.IUserService;
import wang.diff.scaffold.service.convert.UserConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserConverter userConverter;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public UserPageDTO getPage(Integer pageNum, Integer pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(username != null){
            queryWrapper.like(User.USER_NAME, username);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        PageInfo<User> page = new PageInfo<>(list);
        return userConverter.covert2PageDto(page);
    }

    @Override
    public UserDTO getByMobile(String mobile, String password) {
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq(User.MOBILE, mobile);
        User user = userMapper.selectOne(objectQueryWrapper);
        if(user == null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户不存在");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户密码不一致");
        }

        return userConverter.convert2Dto(user);
    }

    @Override
    public UserDTO getById(Long id) {
        final User user = userMapper.selectById(id);
        if(user == null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户不存在");
        }
        return userConverter.convert2Dto(user);
    }

    @Override
    public Integer updateById(Long id, UserUpdateDTO payload) {
        User user = userMapper.selectById(id);
        if(user == null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户不存在");
        }
//        BeanUtil.copyProperties(payload, user);
        user.setUserName(payload.getUserName());
        user.setBalance(payload.getBalance());
        return userMapper.updateById(user);
    }

    @Override
    public UserDTO addOne(UserAddDTO userAddDTO) {
        LocalDate birthday = LocalDate.parse(userAddDTO.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE);
        final Date birthdayDate = Date.from(birthday.atStartOfDay(ZoneId.systemDefault()).toInstant());

        final QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq(User.MOBILE, userAddDTO.getMobile());
        objectQueryWrapper.eq(User.STATUS, CommonStatus.NORMAL.getCode());
        User userDb = userMapper.selectOne(objectQueryWrapper);
        if(userDb != null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户已存在");
        }

        final User user = new User();
        user.setUserName(userAddDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userAddDTO.getPassword()));
        user.setMobile(userAddDTO.getMobile());
        user.setBirthday(birthdayDate);
        user.setSex(userAddDTO.getSex());
        userMapper.insert(user);


        elasticsearchOperations.save(user);
        return userConverter.convert2Dto(user);
    }

    @Override
    public List<UserDTO> searchUser(String username, String mobile) {

        final Criteria criteria = new Criteria(User.USER_NAME).contains(username);
        criteria.and(new Criteria(User.MOBILE).contains(mobile));

        final Query query = new CriteriaQuery(criteria);

        final Sort sort = Sort.by(User.ID).descending()
                .and(Sort.by(User.CREATE_TIME).descending());

        query.addSort(sort);

        final SearchHits<User> search = elasticsearchOperations.search(query, User.class);
        final List<SearchHit<User>> searchHits = search.getSearchHits();

        if(searchHits.isEmpty())
            throw new BizException(HttpStatus.BAD_REQUEST, "user.400000", "用户不存在");

        return searchHits.stream().map(searchHit -> {
            User user = searchHit.getContent();
            final UserDTO userDTO = userConverter.convert2Dto(user);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void syncUserToEs() {
        final List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            elasticsearchOperations.save(user);
        }
    }


}
