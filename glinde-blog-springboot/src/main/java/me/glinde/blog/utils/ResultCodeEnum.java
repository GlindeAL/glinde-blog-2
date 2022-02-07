package me.glinde.blog.utils;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"操作成功"),
    FAIL(400, "操作失败"),
    LOGIN_AUTH(401, "未登陆"),
    PERMISSION(403, "权限不足"),
    NOT_FOUND(404,"未找到"),
    TIMEOUT(504,"超时")
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}