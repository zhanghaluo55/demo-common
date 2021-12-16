package com.hongpro.demo.common.validate.domain;

import com.hongpro.demo.common.validate.annotation.NotBlank;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 用户实体类
 * @date 2021/11/29 17:59
 */
public class User {
    private Long id;

    @NotBlank(fieldName="姓名", message = "姓名不能为空")
    private String name;

    //@Less(fieldName="年龄", value=100)
    private int age;

    private String phone;

    private String birthday;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
