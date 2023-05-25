package wang.diff.scaffold.controller.common;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObjectStoreControllerTest {
    @Resource
    private ObjectStoreController objectStoreController;


    @Test
    void uploadMultiPartTest() {
        // objectStoreController.uploadMultipart(null);
        System.out.println("hello");
    }
}
