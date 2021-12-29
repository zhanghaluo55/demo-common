package com.hongpro.demo.common.validate.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongpro.demo.common.validate.model.dto.QueryParam;
import com.hongpro.demo.common.validate.model.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangzihong
 * @description 基础控制层类
 * @date 2021/12/24 16:40
 */
@RestController
public abstract class BaseController<T> {
    @Autowired
    private BaseService<T> baseService;

    /**
     * 获取service抽象方法
     * @return service bean
     */
    public abstract BaseService<T> getBaseService();

    @ResponseBody
    @RequestMapping(value = "/listPages")
    public Object listByPage(T t, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "queryParams", required = false) List<QueryParam> queryParams) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        IPage<T> p = baseService.selectPageByCondition(new Page<>(pageNum, pageSize), queryWrapper);
        return new CommonResult().pageSuccess(p);
    }
}
