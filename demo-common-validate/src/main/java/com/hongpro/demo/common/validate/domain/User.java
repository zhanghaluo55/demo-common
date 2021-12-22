package com.hongpro.demo.common.validate.domain;

import com.hongpro.demo.common.validate.annotation.NotBlank;
import lombok.Data;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description 用户实体类
 * @date 2021/11/29 17:59
 */
@Data
public class User {
    private Long id;

    @NotBlank(fieldName="姓名", message = "姓名不能为空")
    private String name;

    //@Less(fieldName="年龄", value=100)
    private int age;

    private String phone;

    private String birthday;
}
