package com.example.weather;

/**
 * 项目名: weather
 * 文件名: SnCal
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午1:17
 * 描述: 计算百度API接口的SN码
 */

import com.example.weather.TestTool.LogUtil;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

//java版计算signature签名
public class SnCal {

    private static final String ak = "ccP5RL5I3NNtHySCDPZpGlA51GGjatpA";
    private static final String sk = "MkBIP5ntlBkGzu20owd1cyeIFEZduSId";

    private String address = "长安";  //需要搜索的地址
    private int digit = 2;      //需要搜索的行政区划显示子级级数

    //https://api.map.baidu.com/api_region_search/v1/?keyword=%E6%B9%98%E6%B0%B4%E9%95%87&sub_admin=2&ak=ccP5RL5I3NNtHySCDPZpGlA51GGjatpA&sn=c2b8d30febe1d10bc1b650a2f04e5d6f
    //https://api.map.baidu.com/api_region_search/v1/?keyword=%E6%B9%98%E6%B0%B4%E9%95%87&sub_admin=2&ak=ccP5RL5I3NNtHySCDPZpGlA51GGjatpAMkBIP5ntlBkGzu20owd1cyeIFEZduSId&sn=c2b8d30febe1d10bc1b650a2f04e5d6f
   @Test
    public  void Tests() throws UnsupportedEncodingException,
            NoSuchAlgorithmException {

       /**
        * 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；
        * post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，
        * 但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。
        * 以get请求为例：https://api.map.baidu.com/api_region_search/v1/?keyword=山东&sub_admin=2&ak=yourak，
        * paramsMap中先放入keyword，再放sub_admin，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
        */

       Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("keyword", address);
        paramsMap.put("sub_admin", String.valueOf(digit));
        paramsMap.put("ak", ak);

        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=keyword=山东&sub_admin=2&ak=yourak
        String paramsStr = SnCal.toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/api_region_search/v1/?keyword=山东&sub_admin=2&ak=yourak
        String Url = new String("https://api.map.baidu.com/api_region_search/v1/?" + paramsStr+"&sn=");

        String wholeStr = new String("/api_region_search/v1/?" + paramsStr + sk);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

        // 调用下面的MD5方法得到最后的sn签名
//        LogUtil.v("TAS",snCal.MD5(tempStr));
       System.out.println("SN码地址： "+Url+SnCal.MD5(tempStr));
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    private static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }


    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    private static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
