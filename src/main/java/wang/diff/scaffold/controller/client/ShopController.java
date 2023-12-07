package wang.diff.scaffold.controller.client;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import wang.diff.scaffold.controller.ShopApi;
import wang.diff.scaffold.controller.model.ShopAddDTO;
import wang.diff.scaffold.controller.model.ShopDTO;
import wang.diff.scaffold.controller.model.ShopPageDTO;

@RestController
@Tag(name="shop")
public class ShopController implements ShopApi {
    @Override
    public ResponseEntity<ShopDTO> addOneShop(ShopAddDTO shopAddDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ShopPageDTO> getPageShop(Integer pageNum, Integer pageSize, String name) {
        return null;
    }
}
