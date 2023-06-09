package com.bjpowernode.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.commons.utils
 * @Description: TODO 对Data类型数据处理的工具类
 * @Date 2023-04-04 0:49
 */

public class DateUtils {

    /**
     * 对指定的date对象进行格式化
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }
}
