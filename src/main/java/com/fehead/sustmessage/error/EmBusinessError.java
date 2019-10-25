package com.fehead.sustmessage.error;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 17:20
 */
public enum EmBusinessError implements CommonError{

    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),

    UNKNOWN_ERROR(10002, "未知错误"),
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_EXIST(20002,"用户已存在"),
    USER_LOGIN_FAIL(20003,"用户学号或者密码错误"),

    NO_MESSAGES(30001,"您没有发布过留言"),
    NO_MESSAGE(30002,"该留言不存在");

        private int errCode;
        private String errMsg;
        //构造函数
        private EmBusinessError(int errCode, String errMsg){
            this.errCode = errCode;
            this.errMsg = errMsg;
        }

        @Override
        public int getErrCode() {
            return this.errCode;
        }

        @Override
        public String getErrMsg() {
            return this.errMsg;
        }

        @Override
        public CommonError setErrMsg(String errMsg) {
            this.errMsg = errMsg;
            return this;
        }

}
