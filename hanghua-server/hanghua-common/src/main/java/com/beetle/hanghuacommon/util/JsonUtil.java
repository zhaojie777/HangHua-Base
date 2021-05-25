package com.beetle.hanghuacommon.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zhaojie
 * @date 2021-04-26
 * @Description json工具类
 */
public class JsonUtil {
    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    //jackson核心类
    private static ObjectMapper mapper = new ObjectMapper();


    /**
     * 序列化，将对象转为json字符串
     * @param data
     * @return
     */
    public static String toJsonString(Object data) {
        if (data == null) {
            return null;
        }

        String json = null;
        try {
            json = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("[{}] toJsonString error: [{}]",
                    data.getClass().getSimpleName(),
                    e);
        }

        return json;
    }


    /**
     * 反序列化，将json字符串转化为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("parse json [{}] to class [{}] error: {{}}",
                    json,
                    clazz.getSimpleName(),
                    e);
        }
        return t;
    }




}
