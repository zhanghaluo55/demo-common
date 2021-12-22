package com.hongpro.demo.common.validate.model.constant;

import com.hongpro.demo.common.validate.model.result.ResultStatus;

/**
 * @author zhangzihong
 * @description 全局状态码
 * 错误码为字符串类型，4位   分成两个部分：错误产生来源+三位数字编号
 * 错误来源分类：
 *      A  -  表示错误来源于用户           eg: 参数错误
 *      B  -  表示错误来源于当前系统内部    eg: 业务逻辑出错，或程序健壮性差等问题
 *      C  -  表示错误来源于第三方服务      eg: 消息投递超时
 * @date 2021/12/16 14:25
 */
public class GlobalReturnStatus {
    /** 成功状态码  */
    public static final ResultStatus SUCCESS = new ResultStatus("0000", "操作成功");

    /**  错误来源于用户 */
    public static final ResultStatus PARAM_VALID_ERROR = new ResultStatus("A100", "参数校验错误");
    public static final ResultStatus PARAM_ERROR = new ResultStatus("A101", "参数错误");
    public static final ResultStatus PARAM_BLANK = new ResultStatus("A102", "参数为空");
    public static final ResultStatus LEN_OUT_LIMIT = new ResultStatus("A103", "参数【{0}】长度超过限制");
    public static final ResultStatus VALID_ERROR = new ResultStatus("A104", "校验失败：{0}");
    public static final ResultStatus DUPLICATE_NAME = new ResultStatus("A105", "名称重复：{0}");
    public static final ResultStatus DUPLICATE_DATA = new ResultStatus("A106", "{0} 已存在");

    public static final ResultStatus NOT_FOUND_DATA = new ResultStatus("A201", "数据不存在");
    public static final ResultStatus DENIED_OPERATE = new ResultStatus("A202", "您没有权限操作");
    public static final ResultStatus BUSINESS_VALID_ERROR = new ResultStatus("A203", "业务参数校验失败");

    /**  错误来源于系统内部 */
    public static final ResultStatus ERROR = new ResultStatus("B001", "系统内部错误");
    public static final ResultStatus SYS_TIMEOUT = new ResultStatus("B002", "系统执行超时");
    public static final ResultStatus RESOURCES_ERROR = new ResultStatus("B003", "系统资源异常");
    public static final ResultStatus RESOURCES_EXHAUSTED = new ResultStatus("B004", "系统资源耗尽");
    public static final ResultStatus MEMORY_EXHAUSTED = new ResultStatus("B005", "系统内存耗尽");
    public static final ResultStatus ACCESS_RESOURCES_ERROR = new ResultStatus("B006", "系统资源访问异常");
    public static final ResultStatus PROXY_OFFLINE = new ResultStatus("B007", "服务已离线，请稍候重试");

    /**  错误来源于第三方服务 */
    public static final ResultStatus OTT_ERROR = new ResultStatus("C001", "调用第三方服务失败");
    public static final ResultStatus MIDDLEWARE_ERROR = new ResultStatus("C002", "中间件服务出错");
    public static final ResultStatus DB_ERROR = new ResultStatus("C003", "数据库服务出错");
    public static final ResultStatus DB_NOT_FOUND_TABLE = new ResultStatus("C004", "主键冲突");
}
