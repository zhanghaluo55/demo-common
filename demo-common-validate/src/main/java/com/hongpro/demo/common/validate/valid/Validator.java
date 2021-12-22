package com.hongpro.demo.common.validate.valid;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 通用校验器
 * @date 2021/11/29 16:47
 */
public class Validator {
    /**
     * 是否通过检验
     */
    private boolean isValid = true;
    /**
     * 提示信息
     */
    private String message;

    public Validator validate(Rule rule) {
        if (this.isValid) {
            this.isValid = rule.isValid();
            this.message = rule.getMessage();
        }
        return this;
    }

    public Validator validatorAnnotation(Object o) {
        return validate(new AnnotationRule(o));
    }

    public Validator notNull(Object o, String message) {
        Validator result = new Validator();
        boolean b = this.isValid && o == null;
        if (b) {
            result.setMessage(message);
            return result;
        }
        return this;
    }

    public Validator notBlank(Object o, String message) {
        Validator result = new Validator();
        boolean b = this.isValid && (o == null || o.toString().length() == 0);
        if (b) {
            result.setMessage(message);
            return result;
        }
        return this;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.isValid = false;
        this.message = message;
    }
}
