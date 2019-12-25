package com.example.utils;

import java.util.Random;

public class DataUtils {
    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    //截取返回内容  10  1 "userId" ","
    public static String substring(String result,String beginString,Integer beginindex,String endString,Integer endindex){
        int begin = result.indexOf(beginString) + beginindex;
        int end = result.indexOf(endString) - endindex;
        String element = result.substring(begin, end);
        return  element;
    }

}
