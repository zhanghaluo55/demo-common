package com.hongpro.demo.common.validate.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author zhangzihong
 * @description 通用业务逻辑接口
 * @date 2021/12/28 17:08
 */
@Service
public interface IService<T> {
    /**
     * 根据主键查询
     * @param key 主键键值
     * @return 查询结果
     */
    T selectByKey(Serializable key);

    /**
     * 保存
     * @param entity 实体
     * @return 保存结果
     */
    int save(T entity);

    /**
     * 删除
     * @param key 主键
     * @return 结果
     */
    int delete(Serializable key);

    /**
     * 更新
     * @param entity 更新实体
     * @return 结果
     */
    int updateAll(T entity);

    /**
     * 更新
     * @param entity 更新实体
     * @param updateWrapper 更新条件实体
     * @return 结果
     */
    int updateByCondition(T entity, Wrapper<T> updateWrapper) ;

    /**
     * 条件查询-分页
     * @param page 分页实体
     * @param selectWrapper 查询条件实体
     * @return 查询结果
     */
    public IPage<T> selectPageByCondition(IPage<T> page , QueryWrapper<T> selectWrapper);
}
