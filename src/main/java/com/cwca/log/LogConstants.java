package com.cwca.log;

/**
 * @Author liforever
 * @Date 2019/3/26 23:21
 **/
public class LogConstants {
    /**
     * 用户动作名称
     */
    public class ActionName {

        /**
         * 用户登陆
         */
        public static final String USER_LOGIN_IN = "UserLoginIn";
        /**
         * 用户注册
         */
        public static final String USER_REGISTER = "UserRegister";
        /**
         * 用户文件上传
         */
        public static final String USER_FILE_UPLOAD = "UserFileUpload";
        /* 用户历史记录*/
        public static final String HISTORY_APPLY_INFO = "HistoryApplyInfo";
        /* 审核信息查询*/
        public static final String EXAM_INFO_GET = "GetExamInfo";

        /* 审核流程开始*/
        public static final String TO_APPLY_PROCESS = "ToApplyProcess";

        /* 审核单个资料*/
        public static final String EXAM_ONE = "ExamOne";

        /* 审核该条记录*/
        public static final String Exam_Apply = "ExamApply";

        /* 移交终审*/
        public static final String TRANSFOR = "TRANSFOR";

        /* 检查申请状态*/
        public static final String CHECK_APPLY = "Check_Apply";

        public static final String PRINT = "print";

        public static final String PAY = "PAY";


        public static final String ONEEXPRESS = "oneexpress";

    }
}
