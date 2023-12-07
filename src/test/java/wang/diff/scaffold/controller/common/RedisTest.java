package wang.diff.scaffold.controller.common;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
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
    public void computeRadiusInfo() {
        redisTemplate.opsForGeo().add("geo", new Point(102.680084,25.069415) , "金泰国际广场");
        redisTemplate.opsForGeo().add("geo", new Point(102.68082,25.073065) , "秘境M60创意园");
        redisTemplate.opsForGeo().add("geo", new Point(102.687647,25.065308) , "昆明面粉厂");
        redisTemplate.opsForGeo().add("geo", new Point(102.709817,25.054572) , "翠湖公园");
        redisTemplate.opsForGeo().add("geo", new Point(102.709817,25.054572) , "郊野公园");
        redisTemplate.opsForGeo().add("geo", new Point(102.680712,25.027859) , "大观公园");
        redisTemplate.opsForGeo().add("geo", new Point(102.578952,25.060464) , "棋盘山国家公园");
        redisTemplate.opsForGeo().add("geo", new Point(102.626383,25.020524) , "翠峰生态公园");
        redisTemplate.opsForGeo().add("geo", new Point(102.70874,25.119235) , "长虫山生态公园");

        // 坐标点
        Point nowPoint = new Point(102.683856,25.069694);
        // 10 千米
        final Distance distance = new Distance(5, Metrics.KILOMETERS);
        // 10 千米的圆圈
        final Circle circle = new Circle(nowPoint, distance);

        final RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance() // 包含距离
                .sortAscending()  // 正序
                .includeDistance() // 包含距离
                .includeCoordinates() // 包含坐标
                .limit(10);// 10条


        final GeoResults<RedisGeoCommands.GeoLocation<String>> geo = redisTemplate.opsForGeo().radius("geo", circle, args);
        assert geo != null;
        geo.forEach(x-> {
//            log.info("........距离:{},名字:{}.....",x.getDistance(),x.getContent().getName());
            System.out.printf("距离 %s,名字 %s \n",x.getDistance(),x.getContent().getName());
        });

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
