package com.cwca.constant;

/**
 * @Author: liforever
 * @Date: 2019/5/2 21:16
 *
 * 系统常量
 */
public class Constants {

    public static String CREATE_TIME = "createTime";
    public static String isPass = "isPass";
    public static String USERTYPE = "userType";
    //分页大小
    public static int SIZE = 15;
    //排除字段
    public static String[] excApplyCopyProperties = new String[]{"certificateType"};
    //session中排除这两个字段
    public static String[] excLoginCopyProperties = new String[]{"password", "salt"};

    //新立
    public static String SZZS_KEY_NEW = "0";
    //变更
    public static String SZZS_KEY_CHANGE = "1";
    //更新
    public static String SZZS_CHANGE = "2";
    //丢失补办
    public static String KEY_REISSUE = "3";
    //丢失补办加更新
    public static String KEY_REISSUE_SZZS_CHANGE = "4";

    //业务类型对应的价格
    public static String SZZS_KEY_NEW_PRICE = "350";
    public static String SZZS_KEY_CHANGE_PRICE = "500";
    public static String SZZS_CHANGE_PRICE = "350";
    public static String KEY_REISSUE_PRICE = "150";
    public static String KEY_REISSUE_SZZS_CHANGE_PRICE = "500";


    //相对中智  新户
    public static int newUser = 1;
    //旧户
    public static int oldUser = 0;

    //用户有效
    public static int valid = 1;

    //中智同步标志   已同步
    public static int SYNC = 1;
    //未同步
    public static int NO_SYNC = 0;


    //邮寄方式
    public static String YJ = "0";

    //自取
    public static String ZQ = "1";

    //邮寄费用
    public static Double EXPRESS_PAY = 25.00;


}
