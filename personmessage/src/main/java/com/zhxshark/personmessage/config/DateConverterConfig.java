package com.zhxshark.personmessage.config;

/**
 * @author zhuxin
 * @date 2019/10/21 13:50
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 全局页面传入日期字符串，自动转换为日期格式
 */
@Component
public class DateConverterConfig implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateConverterConfig.class);

    private static final List<String> formats = new ArrayList<>();

    static {
        formats.add("yyyy-MM");
        formats.add("yyyy-MM-dd");
        formats.add("yyyy-MM-dd hh:mm");
        formats.add("yyyy-MM-dd hh:mm:ss");
    }


    @Override
    public Date convert(String s) {
        String value = s.trim();
        if (value == null || value.length()<1){
            return  null;
        }
        if (s.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(s,formats.get(0));
        }else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(s,formats.get(1));
        }else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(s,formats.get(2));
        }else if (s.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(s,formats.get(3));
        }else {
            throw  new IllegalArgumentException("invalid boolean value '"+s+"'");
        }

    }

    /**
     * 格式化日期
     * @param dateStr
     * @param format
     * @return
     */
    private Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            date  = dateFormat.parse(dateStr);
        }catch (Exception e){
            logger.warn("格式化日期出错,出错原因:"+e.getMessage());
        }
        return date;
    }

}
