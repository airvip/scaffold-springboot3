package wang.diff.scaffold.service.impl;

import wang.diff.scaffold.controller.model.ShopAddDTO;
import wang.diff.scaffold.controller.model.ShopPageDTO;
import wang.diff.scaffold.entity.Shop;
import wang.diff.scaffold.dao.ShopMapper;
import wang.diff.scaffold.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author airvip
 * @since 2023-09-05
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public ShopPageDTO getPage(Integer pageNum, Integer pageSize, String name) {
        return null;
    }

    @Override
    public Integer addOne(ShopAddDTO shopAddDTO) {
        return null;
    }
}
