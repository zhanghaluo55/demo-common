package com.hongpro.demo.common.validate.model.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 通用返回实体
 * @date 2021/12/16 10:45
 */
@Data
@ToString
public class BaseResult<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 918723049060943696L;

    @ApiModelProperty(value = "返回码(0：成功 -1：失败)", dataType = "String")
    private int code;
    @ApiModelProperty(value = "提示信息", dataType = "String")
    private String message;
    @ApiModelProperty(value = "返回值", dataType = "String")
    private T data;

    public BaseResult() {
    }

    public BaseResult(BaseResult.Type type, String message) {
        this.code = type.value;
        this.message = message;
    }

    public BaseResult(BaseResult.Type type, String message, T data) {
        this.code = type.value;
        this.message = message;
        if (data != null) {
            this.data = data;
        }
    }

    public static <T extends Serializable> BaseResult<T> success() {
        return success("操作成功");
    }

    public static <T extends Serializable> BaseResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T extends Serializable> BaseResult<T> success(String message) {
        return success(message, (T) null);
    }

    public static <T extends Serializable> BaseResult<T> success(String message, T data) {
        return new BaseResult<>(BaseResult.Type.SUCCESS, message, data);
    }

    public static <T extends Serializable> BaseResult<T> warn(String message) {
        return warn(message, (T) null);
    }

    public static <T extends Serializable> BaseResult<T> warn(String message, T data) {
        return new BaseResult<>(BaseResult.Type.WARN, message, data);
    }

    public static <T extends Serializable> BaseResult<T> unAuth() {
        return new BaseResult<>(Type.NO_AUTH, "未登陆", (T) null);
    }

    public static <T extends Serializable> BaseResult<T> error() {
        return error("操作失败");
    }

    public static <T extends Serializable> BaseResult<T> error(String message) {
        return error(message, (T) null);
    }

    public static <T extends Serializable> BaseResult<T> error(String message, T data) {
        return new BaseResult<>(Type.ERROR, message, data);
    }

    public enum Type {
        /**
         * 状态码
         */
        SUCCESS(0),
        WARN(402),
        NO_AUTH(401),
        ERROR(-1);
        private final int value;

        private Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
