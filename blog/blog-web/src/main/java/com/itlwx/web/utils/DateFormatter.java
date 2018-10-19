package com.itlwx.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static SimpleDateFormat sdf;

    public static String  format(Date date,String pattern){

        if(pattern != null && !pattern.equals("")){
            sdf = new SimpleDateFormat(pattern);
        }

        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        return  sdf.format(date);
    }
}
