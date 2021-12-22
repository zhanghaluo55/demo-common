package com.hongpro.demo.common.validate.utils;

import com.hongpro.demo.common.validate.exception.ParamValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangzihong
 * @description 复杂检验工具
 * @date 2021/12/17 15:51
 */
public class ValidatorUtils {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validate(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> resultSet = VALIDATOR.validate(obj, groups);
        if (!resultSet.isEmpty()) {
            List<String> errorMessageList = resultSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            StringBuilder errorMessage = new StringBuilder();
            errorMessageList.forEach(o -> errorMessage.append(o).append(";"));
            throw new ParamValidException(errorMessage.toString());
        }
    }
}
