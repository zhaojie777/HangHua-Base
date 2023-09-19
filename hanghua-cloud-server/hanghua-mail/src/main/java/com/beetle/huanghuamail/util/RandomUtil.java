package com.beetle.huanghuamail.util;

import java.util.Random;

/**
 * @author zhaojie
 * @date 2021-04-27
 * @Description 随机数生成器
 */
public class RandomUtil {

    /**
     * 根据指定长度,生成随机字符
     * @param length
     * @return
     */
    public static String creatRandomCharacters(int length) {
        //随机字符串生成的种子
        String seed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            //生成随机索引选取不同字符
            buffer.append(seed.charAt(number));
        }
        return buffer.toString();
    }
}
