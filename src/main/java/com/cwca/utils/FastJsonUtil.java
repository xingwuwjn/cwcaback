package com.cwca.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liforever
 * @Date: 2019/5/11 20:11
 */
public class FastJsonUtil {

    /**
     * json字符串转map集合
     *
     * @param jsonStr
     * @return
     */
    public static HashMap<String, String> json2Map(String jsonStr) {
        return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
    }

    /**
     * map转json字符串
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, String> map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T json2Bean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转换成List集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> List<T> json2List(String jsonString, Class cls) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转换成ArrayList集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> ArrayList<T> json2ArrayList(String jsonString, Class cls) {
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List集合转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String list2Json(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * json转List
     * (不需要实体类)
     *
     * @param jsonStr
     * @return
     */
    public static JSONArray json2List(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }
//{"data":["{\"nonceStr\":\"fdsfsf\",\"openId\":\"2343243467\",\"orderNo\":\"333dddd32dddafsd\",\"origin\":\"shop\",\"payDate\":1557727677000,\"payMoney\":0.01,\"payStatus\":\"1\",\"payTradeNo\":\"4200000327201905135460206500\",\"payType\":\"2\",\"platform\":\"2\",\"platformAlias\":\"2\"}"],"appid":["2018071987800249"],"sign":["SiHEBA9VkSp1GmSVMdO1hbdViGG3Q5ecZ0-5Ic-T1SCvr9GgaSA2xL9IYf21HJf2ug1eJPdPT5Qd2aDeyfDE1FLfZSka65IpOGKjMW_eav0vZMsVGAxcJyK_ho74UxDGSq9VisUVy9oYi2rvbUobqmEz-w87NIV241wQbOdbzrE"],"signType":["RSA"],"companyId":["4A7CF8058EC94427BF7DD01BA1C62CBD"]}

    public static void main(String[] args) {
        //  NotifyDataDto notifyDataDto = FastJsonUtil.json2Bean("{\"nonceStr\":\"fdsfsf\",\"openId\":\"2343243467\",\"orderNo\":\"333dddd32dddafsd\",\"origin\":\"shop\",\"payDate\":1557727677000,\"payMoney\":0.01,\"payStatus\":\"1\",\"payTradeNo\":\"4200000327201905135460206500\",\"payType\":\"2\",\"platform\":\"2\",\"platformAlias\":\"2\"}", NotifyDataDto.class);


        // JSON.parseObject()
        //System.out.println(JSON.toJSONString(notifyDataDto));
    }

}
