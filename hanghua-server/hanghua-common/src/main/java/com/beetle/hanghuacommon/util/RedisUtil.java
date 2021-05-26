package com.beetle.hanghuacommon.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Redis工具类，封装自定义redisTemplate
 * @author zhaojie
 * @date 2021-05-06
 */
@Component
public class RedisUtil {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

/**
 * =========================Common=================================
 */

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     *指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public static boolean expire(String key, long time) {
       if (time > 0) {
           return redisTemplate.expire(key, time, TimeUnit.SECONDS);
       }
        return false;
    }


    /**
     * 根据key获取过期时间
     * @param key
     * @return
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }


    /**
     * 删除缓存(可单个,可批量)
     * @param key
     */
    public static void delKey(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                //单个销毁
                redisTemplate.delete(key[0]);
            } else {
                //批量销毁
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


/**
 * =========================String=================================
 */

    /**
     * 获取string类型
     * @param key
     * @return
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * 缓存string类型,可存储数字和字符串
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
         redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存string类型，并设置失效时间
     * @param key
     * @param value
     * @param time
     */
    public static void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value);
        if (expire(key, time)) {
            throw new IllegalArgumentException("过期时间不可为小于零的值！");
        }

    }




    /**
     * 递增并返回值
     * @param key
     * @param delta 步长
     * @return
     */
    public long increase(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0!");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 递减并返回值
     * @param key
     * @param delta
     * @return
     */
    public long decrease(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0!");
        }
        return redisTemplate.opsForValue().decrement(key, delta);

    }




/**
 * =========================List=================================
 */

    /**
     * 获取list的缓存长度
     * @param key
     * @return
     */
    public static long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 获取list指定范围的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<Object> getLists(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 根据索引获取list的值
     * @param key
     * @param index
     * @return
     */
    public static Object getListByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }


    /**
     * 缓存list的值
     * @param key
     * @param value
     */
    public static void setList(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 缓存list的值，并设置过期时间
     * @param key
     * @param value
     * @param time
     */
    public static void setList(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
    }


    /**
     * 批量缓存list的值
     * @param key
     * @param value
     */
    public static void setLists(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }


    /**
     * 根据索引更新list的值
     * @param key
     * @param value
     * @param index
     */
    public static void updateListByIndex(String key, Object value, long index) {
        redisTemplate.opsForList().set(key, index, value);
    }



/**
 * =========================hash=================================
 */


    /**
     * 获取hash类型的值
     * @param key
     * @param field
     * @return
     */
    public static Object getHash(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }


    /**
     * 获取hash指定key下的所有值
     * @param key
     * @return
     */
    public static Map<Object, Object> getHashAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存单个hash值
     * @param key
     * @param field
     * @param value
     */
    public static void setHash(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 缓存单个hash值,并设置过期时间
     * @param key
     * @param field
     * @param value
     */
    public static void setHash(String key, String field, Object value, long time) {
        redisTemplate.opsForHash().put(key, field, value);
        expire(key, time);
    }


    /**
     * 批量缓存hash值
     * @param key
     * @param map
     */
    public static void setHashs(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     *批量缓存hash值，并设置过期时间
     * @param key
     * @param map
     * @param time
     */
    public static void setHashs(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        expire(key, time);
    }


    /**
     * 批量删除hash值
     * @param key
     * @param field
     */
    public static void deleteHash(String key, Object... field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 判断hash表中是否存在该值
     * @param key
     * @param field
     * @return
     */
    public static boolean hasHashKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }


    /**
     * hash递增，且返回该值，若不存在，则创建。
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public static double incrHash(String key, String field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }


    /**
     * hash递减，且返回该值，若不存在，则创建。
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public static double encrHash(String key, String field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, -delta);
    }


/**
 * =========================set=================================
 */

    /**
     * 批量获取set的值
     * @param key
     * @return
     */
    public static Set<Object> getSetAll(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 批量缓存set
     * @param key
     * @param values
     * @return  成功个数
     */
    public static long setSets(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }


    /**
     * 获取set缓存的长度
     * @param key
     * @return
     */
    public static long getSetSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }


    /**
     * 判断set中是否存在该值
     * @param key
     * @param value
     * @return
     */
    public static boolean hasHashKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


}
