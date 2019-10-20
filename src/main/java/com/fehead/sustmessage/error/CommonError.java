package com.fehead.sustmessage.error;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 17:12
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);

}
