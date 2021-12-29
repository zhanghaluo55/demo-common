package com.hongpro.demo.common.validate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 操作日志实体
 * @date 2021/12/29 15:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperateLogDomain implements Serializable {
    private static final long serialVersionUID = -8076855368385991637L;

    /**
     * 日志类型
     */
    private String logType;
    /**
     * 业务分类
     */
    private String bizType;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作结果
     */
    private String operationResult;
}
