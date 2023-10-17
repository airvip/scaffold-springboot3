package wang.diff.scaffold.common.component;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Scope("prototype")
@Component
@Slf4j
public class RedisMutexLock {
    private static final String LOCK_PREFIX = "lock:";
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private String value = "";

    public RedisMutexLock() {
        this.value = UUID.randomUUID().toString();
        log.error("key-is:{}", value);
    }

    public boolean lock(String key) {
        return lock(key, 3);
    }

    public boolean lock(String key, Integer getLockTime) {
        return lock(key, getLockTime, 5);
    }


    public boolean lock(String key, Integer getLockTime, Integer expireTime) {
        return lock(key, getLockTime, expireTime, 100L);
    }

    /**
     * 获取锁
     *
     * @param key         获取锁的 key
     * @param getLockTime 获取锁的时间(秒)
     * @param expireTime  过期时间(秒)
     * @param sleepTime   睡眠时间(毫秒)  睡眠多久尝试获取锁
     * @return 成功 true  失败 false
     */
    public boolean lock(String key, Integer getLockTime, Integer expireTime, Long sleepTime) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不能为空");
        }
        if (getLockTime == null) {
            getLockTime = 3;
        }
        if (expireTime == null) {
            expireTime = 5;
        }
        // meiguo
        if (sleepTime == null) {
            sleepTime = 100L;
        }
        final long now = System.currentTimeMillis();

        boolean flag = false;
        do {
            flag = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(LOCK_PREFIX + key, value, expireTime, TimeUnit.SECONDS));
            if (flag) {
                break;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (System.currentTimeMillis() < now + getLockTime * 1000);

        return flag;
    }


    /**
     * 释放锁
     *
     * @param key 要解锁的 key
     */
    public void unlock(String key) {
        final String cacheValue = redisTemplate.opsForValue().get(LOCK_PREFIX + key);
        if (cacheValue != null && cacheValue.equals(value)) {
            redisTemplate.delete(LOCK_PREFIX + key);
        }
    }

}
