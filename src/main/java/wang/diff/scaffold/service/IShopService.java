package wang.diff.scaffold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.diff.scaffold.controller.model.ShopAddDTO;
import wang.diff.scaffold.controller.model.ShopPageDTO;
import wang.diff.scaffold.entity.Shop;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author airvip
 * @since 2023-09-05
 */
public interface IShopService extends IService<Shop> {

    ShopPageDTO getPage(Integer pageNum, Integer pageSize, String name);

    Integer addOne(ShopAddDTO shopAddDTO);

}
