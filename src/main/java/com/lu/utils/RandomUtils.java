package com.lu.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机数
 */
public class RandomUtils {  
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    public static final String NUMBERCHAR = "0123456789";

    public static final int RANDOM_TYPE = 0; // 类型：随机不限数字字母字符

    public static final int NUM_TYPE = 1;  // 类型：数字

    public static final int CHAR_TYPE = 2;  // 类型：字母

    public static final int[] inCode = { 0,1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public static int generateInt(){
        Random random = new Random();
        return random.nextInt(10);
    }

    /** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString();  
    }  
    
    /** 
     * 
     *  1 纯数字  2 纯字母 3 随机
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length, int type) {
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
        	if(NUM_TYPE == type){
        		sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));  
        	}else if(CHAR_TYPE == type){
        		sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));  
        	}else if (RANDOM_TYPE == type){
        		sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        	}
            
        }  
        return sb.toString();  
    } 
  
    /** 
     * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateMixString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateLowerString(int length) {  
        return generateMixString(length).toLowerCase();  
    }  
  
    /** 
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateUpperString(int length) {  
        return generateMixString(length).toUpperCase();  
    }  
  
    /** 
     * 生成一个定长的纯0字符串 
     *  
     * @param length 
     *            字符串长度 
     * @return 纯0字符串 
     */  
    public static String generateZeroString(int length) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            sb.append('0');  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 根据数字生成一个定长的字符串，长度不够前面补0 
     *  
     * @param num 
     *            数字 
     * @param fixdlenth 
     *            字符串长度 
     * @return 定长的字符串 
     */  
    public static String toFixdLengthString(long num, int fixdlenth) {  
        StringBuffer sb = new StringBuffer();  
        String strNum = String.valueOf(num);  
        if (fixdlenth - strNum.length() >= 0) {  
            sb.append(generateZeroString(fixdlenth - strNum.length()));  
        } else {  
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth  
                    + "的字符串发生异常！");  
        }  
        sb.append(strNum);  
        return sb.toString();  
    }  
  
    /** 
     * 每次生成的len位数都不相同 
     *  
     * @param param 
     * @return 定长的数字 
     */  
    public static int getNotSimple(int[] param, int len) {  
        Random rand = new Random();  
        if(param.length==0){
        	 param = inCode;
        }
        for (int i = param.length; i > 1; i--) {  
            int index = rand.nextInt(i);  
            int tmp = param[index];  
            param[index] = param[i - 1];  
            param[i - 1] = tmp;  
        }  
        int result = 0;  
        for (int i = 0; i < len; i++) {  
            result = result * 10 + param[i];  
        }  
        return result;  
    }  
    
    /* 
     * 返回长度为【strLength】的随机数字，在前面补0
     */  
    public static String getFixLenthNo(int strLength) {
          
        Random rm = new Random();  
          
        // 获得随机数  
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
      
        // 将获得的获得随机数转化为字符串  
        String fixLenthString = String.valueOf(pross);  
      
        // 返回固定的长度的随机数  
        return fixLenthString.substring(1, strLength + 1);  
    }
    /**
     * 获取1000 - 2000 范围内的随机数
     * @param min
     * @param max
     * @return
     */
    public static String get3Random(int min,int max){
    	Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        String value = String.valueOf(s);
        StringBuffer sb = new StringBuffer(); 
        if(value.length() < 3){
        	sb.append(generateZeroString(3 - value.length()));  
        	value = sb.append(value).toString();
        }
        
    	return value;
    }
    /**
     * 获取uuid 无连接符
     * @return
     */
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    }

    /**
     * 获取uuid  有连接符号
     * @return
     */
    public static String getSalt(){
       return UUID.randomUUID().toString();
    }
    
    /**
     * 字符串转换为Ascii 数组类型字符串 （有逗号）
     * @param value
     * @return
     */
    public static String stringToAsciiArray(String value)  
    {  
        StringBuffer sbu = new StringBuffer();  
        char[] chars = value.toCharArray();   
        for (int i = 0; i < chars.length; i++) {  
            if(i != chars.length - 1)  
            {  
                sbu.append((int)chars[i]).append(",");  
            }  
            else {  
                sbu.append((int)chars[i]);  
            }  
        }  
        return sbu.toString();  

    }
    
    /**
     * 字符串转换为Ascii （无逗号）
     * @param value
     * @return
     */
    public static String stringToAscii(String value)  
    {  
        StringBuffer sbu = new StringBuffer();  
        char[] chars = value.toCharArray();   
        for (int i = 0; i < chars.length; i++) {  
               sbu.append((int)chars[i]);  
        }  
        return sbu.toString();  

    }
    /**
     * Ascii转换为字符串
     * @param value
     * @return
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

} 