package com.yanggy.cloud.common.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author derrick.yang
 * @Date 8/23/18 15:57
 */

@Data
public class  ResponseEntityDto<T> implements Serializable {
    private String status = "1"; //默认1 成功，0失败
    private String msg = "success";

    private T data;

    public ResponseEntityDto() {}

    public ResponseEntityDto(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseEntityDto(T data) {
        this.data = data;
    }
}
