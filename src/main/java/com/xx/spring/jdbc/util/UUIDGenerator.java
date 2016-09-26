package com.xx.spring.jdbc.util;

import java.util.UUID;

/**
 * Created by Administrator on 2016/9/26.
 */
public class UUIDGenerator {


    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
