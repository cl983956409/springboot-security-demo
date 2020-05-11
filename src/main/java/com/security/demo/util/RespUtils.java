package com.security.demo.util;

import com.security.demo.bean.component.RespObject;
import com.security.demo.enums.RespStatus;
import org.springframework.stereotype.Service;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/24 - 9:51
 * @history 2020/4/24 - 9:51 chenglonghy  create.
 */
@Service
public class RespUtils {

    public static RespObject success() {
        return success(RespStatus.SUCCESS, null);
    }

    public static RespObject success(Object data) {
        return success(RespStatus.SUCCESS, data);
    }

    public static RespObject success(RespStatus respStatus, Object data) {
        return new RespObject(respStatus, data);
    }

    public static RespObject error() {
        return error(RespStatus.ERROR, null);
    }

    public static RespObject error(Object data) {
        return error(RespStatus.ERROR, data);
    }

    public static RespObject resp(RespStatus respStatus) {
        return resp(respStatus, null);
    }

    public static RespObject resp(RespStatus respStatus, Object data) {
        return new RespObject(respStatus, data);
    }

    public static RespObject error(RespStatus respStatus, Object data) {
        return new RespObject(respStatus, data);
    }
}
