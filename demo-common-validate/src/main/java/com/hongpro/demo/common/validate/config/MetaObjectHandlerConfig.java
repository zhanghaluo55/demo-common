package com.hongpro.demo.common.validate.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangzihong
 * @description 通用对象处理器配置类
 * @date 2021/12/28 17:32
 */
@Component
@Slf4j
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入方法实体填充");
        setFieldValByName("testDate", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新方法实体填充");
    }
}
