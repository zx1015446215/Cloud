package com.zhxshark.fileexplorer.commen.util;

import java.util.UUID;

/**
 * @author zhuxin
 * @date 2019/9/29 15:20
 */
public class GenerateUtile {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
}
