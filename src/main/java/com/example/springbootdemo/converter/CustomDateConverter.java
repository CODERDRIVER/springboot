package com.example.springbootdemo.converter;

import groovyjarjarantlr.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomDateConverter implements Converter<String,Date> {

    private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORDATEFORMAT = "yyyy-MM-dd";

    /**
     * 两种形式：
     *  1.时间戳
     *  2.1993-02-09
     * @param s
     * @return
     */
    @Override
    public Date convert(String s) {
        if (s==null)
        {
            return null;
        }else{
            s =s.trim();    //去除空格
            if (s.contains("-"))
            {
                SimpleDateFormat simpleDateFormat;
                if(s.contains(":")){
                    simpleDateFormat = new SimpleDateFormat(DATEFORMAT);

                }else{
                    simpleDateFormat = new SimpleDateFormat(SHORDATEFORMAT);
                }
                try {
                    Date date = simpleDateFormat.parse(s);
                    return date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                Long timestamp = new Long(s);
                return new Date(timestamp);
            }
        }
        return null;
    }
}
