package com.yanggy.cloud.common.enums;

/**
 * @author derrick.yang
 * @Date 8/23/18 16:08
 */
public enum ErrorCode {

    //RPC层调用错误码
    UNKONWN_ERROR("0001","未知错误");


    private String msg;
    private String code;
    private static String baseCode = "00000";

    ErrorCode(String code,String msg)
    {
        this.code=code;
        this.msg=msg;
    }

    public String getMsg()
    {
        return this.msg;
    }
    public String getCode() {
        return this.code;
    }
}
