package com.security.demo.bean.component;

import com.security.demo.enums.RespStatus;
import lombok.Data;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/24 - 9:35
 * @history 2020/4/24 - 9:35 chenglonghy  create.
 */
@Data
public class RespObject {
    private String code;
    private String message;
    private Object data;

    public RespObject() {
    }

    public RespObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespObject(RespStatus respStatus) {
        this.code = respStatus.getCode();
        this.message = respStatus.getMessage();
    }

    public RespObject(RespStatus respStatus, Object data) {
        this.code = respStatus.getCode();
        this.message = respStatus.getMessage();
        this.data = data;
    }
}
