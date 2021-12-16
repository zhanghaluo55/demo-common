package com.hongpro.demo.common.validate.test;

import com.hongpro.demo.common.validate.domain.User;
import com.hongpro.demo.common.validate.domain.ValidateResult;
import com.hongpro.demo.common.validate.valid.AnnotationRule;
import com.hongpro.demo.common.validate.valid.AnnotationValidator;
import com.hongpro.demo.common.validate.valid.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 简单测试
 * @date 2021/11/29 18:03
 */
public class ValidateTest {
    private static final Logger logger = LoggerFactory.getLogger(ValidateTest.class);

    public static void main(String[] args) {
        //1
        User user = new User();
        user.setName("张嘻嘻");
        ValidateResult result = AnnotationValidator.validate(user);
        if (result.isValid()) {
            logger.info("校验通过");
        } else {
            logger.info(result.getMessage());
        }

        //2
        Validator v1 = new Validator().notNull(user.getName(), "姓名").notBlank("", "邮箱校验不通过").notNull(user.getAge(), "年龄");
        if (v1.isValid()) {
            logger.info("校验通过");
        } else {
            logger.info(v1.getMessage());
        }

        //3
        Validator v2 = new Validator().validate(new AnnotationRule(new User()));
        if (v2.isValid()) {
            logger.info("校验通过");
        } else {
            logger.info(v2.getMessage());
        }
    }


}
