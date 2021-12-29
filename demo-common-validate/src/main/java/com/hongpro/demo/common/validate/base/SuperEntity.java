package com.hongpro.demo.common.validate.base;

import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @author zhangzihong
 * @description
 * @date 2021/12/28 18:00
 */
public class SuperEntity<T extends Model<T>> extends Model<T> {
    private static final long serialVersionUID = -5520115271169225773L;
/*
    private Long id;
    private Long tenantId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public SuperEntity<T> setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }*/
}
