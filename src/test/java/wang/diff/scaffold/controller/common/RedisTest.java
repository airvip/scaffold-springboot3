package wang.diff.scaffold.controller.common;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
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
    public void computeDistanceByRedis() {
        final GeoOperations<String, String> stringStringGeoOperations = redisTemplate.opsForGeo();
        stringStringGeoOperations.add("geo", new Point(116.343328,39.947246) , "北京动物园");
        stringStringGeoOperations.add("geo", new Point(116.404269, 39.914492), "北京天安门");
        final Distance distance = stringStringGeoOperations.distance("geo", "北京动物园", "北京天安门");
        log.info("distance:{}", distance);

        final Distance dis = stringStringGeoOperations.distance("geo", "北京动物园", "北京天安门", Metrics.KILOMETERS);
        assert dis != null;
        log.info("distance:{} {}", dis.getValue(), dis.getMetric());

    }

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
