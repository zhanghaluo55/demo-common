package com.hongpro.demo.common.validate.model.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hongpro.demo.common.validate.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zhangzihong
 * @description
 * @date 2021/12/27 15:06
 */
public class CommonResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 5098859229605604629L;

    /**
     * 操作成功
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 操作失败
     */
    public static final int FAILED_CODE = 500;

    /**
     * 参数校验失败
     */
    public static final int VALIDATE_FAILED_CODE = 701;

    /**
     * 未认证
     */
    public static final int UNAUTHORIZED_CODE = 401;

    /**
     * 未授权
     */
    public static final int  FORBIDDEN_CODE = 403;


    private int code;
    private String message;
    private Object data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS_CODE;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    /**
     * 返回分页成功数据
     * @return 分页响应消息
     */

    public <T> CommonResult pageSuccess(IPage<T> pageInfo) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("pageSize", pageInfo.getSize());
        commonResult.put("totalPage", pageInfo.getPages());
        commonResult.put("total", pageInfo.getTotal());
        commonResult.put("pageNum", pageInfo.getCurrent());
        commonResult.put("list", pageInfo.getRecords());
        this.code = SUCCESS_CODE;
        this.message = "操作成功";
        this.data = commonResult;
        return this;
    }
    /**
     * 普通失败提示信息
     * @return 普通失败消息
     */
    public CommonResult failed() {
        this.code = FAILED_CODE;
        this.message = "操作失败";
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult validateFailed(String message) {
        this.code = VALIDATE_FAILED_CODE;
        this.message = message;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public CommonResult unauthorized(String message) {
        this.code = UNAUTHORIZED_CODE;
        this.message = "暂未登录或token已经过期";
        this.data = message;
        return this;
    }

    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public CommonResult forbidden(String message) {
        this.code = FORBIDDEN_CODE;
        this.message = "没有相关权限";
        this.data = message;
        return this;
    }

    /**
     * 参数验证失败使用
     * @param result 错误信息
     */
    public CommonResult validateFailed(BindingResult result) {
        FieldError fieldError = result.getFieldError();
        if (fieldError != null) {
            String defaultMessage = fieldError.getDefaultMessage();
            if (StringUtils.isNotEmpty(defaultMessage)) {
                validateFailed(defaultMessage);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.objectToJson(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CommonResult that = (CommonResult) o;
        return code == that.code && Objects.equals(message, that.message) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code, message, data);
    }
}
