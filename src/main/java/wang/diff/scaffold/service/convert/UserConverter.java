package wang.diff.scaffold.service.convert;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import wang.diff.scaffold.controller.model.OnePageDataPagination;
import wang.diff.scaffold.controller.model.UserDTO;
import wang.diff.scaffold.controller.model.UserPageDTO;
import wang.diff.scaffold.entity.User;

import java.text.SimpleDateFormat;

@Component
public class UserConverter {

    private static <T> void fillPageInfo(UserPageDTO rst, PageInfo<T> pd) {
        final OnePageDataPagination onePageDataPagination = new OnePageDataPagination();
        onePageDataPagination.setTotalSize((int)pd.getTotal());
        onePageDataPagination.setPageNum(pd.getPageNum());
        onePageDataPagination.setPageSize(pd.getPageSize());
        rst.setPagination(onePageDataPagination);
    }


    public UserPageDTO covert2PageDto(PageInfo<User> pageData) {
        final UserPageDTO rst = new UserPageDTO();
        fillPageInfo(rst, pageData);
        rst.setList(pageData.getList().stream().map(this::convert2Dto).toList());
        return rst;
    }

    public UserDTO convert2Dto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setBalance(user.getBalance());
        userDTO.setSex(user.getSex());
        userDTO.setStatus(user.getSex());
//        userDTO.setBirthday(DateUtil.format(user.getBirthday(), "yyyy-MM-dd"));
        String birthdayString = "";
        if(user.getBirthday() != null){
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthdayString = simpleDateFormat.format(user.getBirthday());
        }
        userDTO.setBirthday(birthdayString);
        userDTO.setUserName(user.getUserName());
        userDTO.setMobile(user.getMobile());
        userDTO.setCreateTime(user.getCreateTime());

        return userDTO;

    }

}