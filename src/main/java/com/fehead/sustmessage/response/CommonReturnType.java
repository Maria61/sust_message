package com.fehead.sustmessage.response;

/**
 * @author Maria
 * @program sustmessage
 * @date 2019/10/20 14:22
 */
public class CommonReturnType {
    private String status;
    /**
     * status:success
     * data:返回所需数据
     *
     * status:fail
     * data:返回通用错误码格式
     */
    private Object data;
    //定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
