package com.zhxshark.fileexplorer.commen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhuxin
 * @date 2019/10/21 18:53
 */
public class DateUtils {

    private static List<String> dateFormats =new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    static {
        dateFormats.add("yyyy-MM");
        dateFormats.add("yyyy-MM-dd");
        dateFormats.add("yyyy-MM-dd HH:mm");
        dateFormats.add("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前时间的字符串
     * @param order
     * @return
     */
    public static String getNowTimeString(int order){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormats.get(order));
        return sdf.format(new Date());
    }

    /**
     * 格式化日期字符串
     * @param dateStr
     * @param order
     * @return
     */
    public static String getTimeString(String dateStr, int order){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormats.get(order));
        return sdf.format(dateStr);
    }

}
