package com.hongpro.demo.common.validate.handler;

import com.hongpro.demo.common.validate.exception.BusinessException;
import com.hongpro.demo.common.validate.exception.DataNotExistsException;
import com.hongpro.demo.common.validate.exception.DuplicateDataException;
import com.hongpro.demo.common.validate.exception.ParamValidException;
import com.hongpro.demo.common.validate.model.constant.GlobalReturnStatus;
import com.hongpro.demo.common.validate.model.result.BaseReturn;
import com.hongpro.demo.common.validate.model.result.ResultStatus;
import com.hongpro.demo.common.validate.model.result.ReturnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangzihong
 * @description 自定义异常捕获
 * @date 2021/12/16 14:18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String RESPONSE_ERROR_RESULT = "RESPONSE_RESULT";
    private static final String ERROR_HEADER = "ERROR";

    /**
     * 默认异常
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = Exception.class)
    public BaseReturn exceptionHandler(HttpServletResponse response, Exception e) {
        LOGGER.error(e.getMessage(), e);
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        return new ReturnError();
    }

    /**
     * 业务异常
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object exceptionHandler(HttpServletResponse response, BusinessException e) {
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        if (null != e.getResultStatus()) {
            return new ReturnError(e.getResultStatus());
        }
        LOGGER.error(e.getMessage(), e);
        return new ReturnError(e.getMessage());
    }

    /**
     * 数据不存在
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = DataNotExistsException.class)
    public Object exceptionHandler(HttpServletResponse response, DataNotExistsException e) {
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        if (null != e.getResultStatus()) {
            return new ReturnError(e.getResultStatus());
        }
        return new ReturnError(new ResultStatus(GlobalReturnStatus.NOT_FOUND_DATA.getCode(), e.getMessage()));
    }

    /**
     * 重复数据
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = DuplicateDataException.class)
    public Object exceptionHandler(HttpServletResponse response, DuplicateDataException e) {
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        if (null != e.getResultStatus()) {
            return new ReturnError(e.getResultStatus());
        }
        return new ReturnError(new ResultStatus(GlobalReturnStatus.DUPLICATE_NAME.getCode(), e.getMessage()));
    }

    /**
     * 参数校验错误
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = ParamValidException.class)
    public Object exceptionHandler(HttpServletResponse response, ParamValidException e) {
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        if (null != e.getResultStatus()) {
            return new ReturnError(e.getResultStatus());
        }
        return new ReturnError(new ResultStatus(GlobalReturnStatus.PARAM_ERROR.getCode(), e.getMessage()));
    }

    /**
     * 参数校验错误
     * @param e 异常对象
     * @return 统一返回参数格式数据
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object exceptionHandler(HttpServletResponse response, MethodArgumentNotValidException e) {
        response.addHeader(RESPONSE_ERROR_RESULT, ERROR_HEADER);
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            LOGGER.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
            return new ReturnError(new ResultStatus(GlobalReturnStatus.PARAM_ERROR.getCode(), fieldError.getDefaultMessage()));
        }
        return new ReturnError(new ResultStatus(GlobalReturnStatus.PARAM_ERROR.getCode(), e.getMessage()));
    }

}
