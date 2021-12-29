package com.hongpro.demo.common.validate.base;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hongpro.demo.common.validate.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 业务逻辑实体 基础类
 * @date 2021/12/24 16:49
 */
public class BaseService<T> implements IService<T> {
    @Autowired
    private SuperMapper<T> mapper;

    public SuperMapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Serializable key) {
        return mapper.selectById(key);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Serializable key) {
        return mapper.deleteById(key);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateById(entity);
    }

    @Override
    public int updateByCondition(T entity, Wrapper<T> updateWrapper) {
        return mapper.update(entity, updateWrapper);
    }

    @Override
    public IPage<T> selectPageByCondition(IPage<T> page, QueryWrapper<T> selectWrapper) {
        return mapper.selectPage(page, selectWrapper);
    }
}
