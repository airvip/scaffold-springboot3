package wang.diff.scaffold.controller.common;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import wang.diff.scaffold.common.util.MiscUtils;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void opRedisTest() {
        // 了解
        val valueOps = redisTemplate.boundValueOps("session.131087");
        String randomString = MiscUtils.generateRandomString();
        valueOps.set(randomString, Duration.ofSeconds(10L));

        // 推荐
        redisTemplate.opsForValue().set("now-mobile", randomString, 20L, TimeUnit.SECONDS);

        String idsKey = "order.ids";

        for (int i = 0; i<10; i++) {
            redisTemplate.opsForZSet().add(idsKey, Integer.toString(i), MiscUtils.generateRandomDouble());
        }

        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeWithScores(idsKey, 0, -1);
        assert typedTuples != null;
        typedTuples.forEach(x-> {
            log.info("........score:{},value:{}.....",x.getScore(),x.getValue());
            if(Objects.equals(x.getValue(), "3")){
                redisTemplate.opsForZSet().remove(idsKey, x.getValue());
            }
        });

        log.info(StrUtil.repeat("=====", 10));

        Set<ZSetOperations.TypedTuple<String>> typedTuplesNext = redisTemplate.opsForZSet().rangeWithScores(idsKey, 0, -1);
        assert typedTuplesNext != null;
        typedTuplesNext.forEach(x-> {
            log.info("........score:{},value:{}.....",x.getScore(),x.getValue());
        });
    }
}
