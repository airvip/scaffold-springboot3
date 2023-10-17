package wang.diff.scaffold.controller.common;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import wang.diff.scaffold.common.component.RedisMutexLock;

@SpringBootTest
public class ObjectStoreControllerTest {
    @Resource
    private ObjectStoreController objectStoreController;


    @Test
    void uploadMultiPartTest() {
        // objectStoreController.uploadMultipart(null);
        System.out.println("hello");
    }


    private Integer value = 0;
    @Resource
    private RedisMutexLock redisMutexLock;

    @Test
    public void test7() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final OpNumber opNumber = new OpNumber(i);
            opNumber.start();
//            opNumber.join();
        }

        Thread.sleep(20000);
        System.out.println(value);
    }


    class OpNumber extends Thread {

        private Integer idx = 0;
        private OpNumber(int i) {
            idx = i;
        }

        public void run() {
            final boolean lock = redisMutexLock.lock("test" + idx.toString());
            if(lock){
                value+=1;
                redisMutexLock.unlock("test"+idx.toString());
            }
        }
    }

}
