package com.hongpro.demo.common.validate.valid;

import com.hongpro.demo.common.validate.domain.ValidateResult;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 使用AnnotationValidator的校验规则
 * @date 2021/11/29 18:55
 */
public class AnnotationRule implements Rule {
    private String message;

    private Object o;

    public AnnotationRule(Object o) {
        this.o = o;
    }

    @Override
    public boolean isValid() {
        ValidateResult result = AnnotationValidator.validate(this.o);
        this.message = result.getMessage();
        return result.isValid();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
