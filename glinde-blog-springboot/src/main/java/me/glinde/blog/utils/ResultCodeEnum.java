package me.glinde.blog.utils;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    LOGIN_AUTH(203, "未登陆"),
    PERMISSION(204, "没有权限"),
    PARAM_ERROR( 205, "参数不正确"),
    SERVICE_ERROR(206, "服务异常"),
    DATA_ERROR(207, "数据异常"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}