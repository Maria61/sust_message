package com.fehead.sustmessage.error;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 17:35
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }
    //接受自定义ErrMsg的方法构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }
    @Override
    public int getErrCode() {
        return 0;
    }

    @Override
    public String getErrMsg() {
        return null;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return null;
    }
}
