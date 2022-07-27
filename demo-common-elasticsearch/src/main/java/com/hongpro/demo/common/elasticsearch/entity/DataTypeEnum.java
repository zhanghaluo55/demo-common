package com.hongpro.demo.common.elasticsearch.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @description:
 * @author: tracy
 * @createTime: 2022/7/22
 */
public enum DataTypeEnum {
    /**
     * 类型枚举
     */
    KEYWORD_TYPE("keyword"),
    TEXT_TYPE("text"),
    BYTE_TYPE("byte"),
    SHORT_TYPE("short"),
    INTEGER_TYPE("integer"),
    LONG_TYPE("long"),
    FLOAT_TYPE("float"),
    DOUBLE_TYPE("double"),
    BOOLEAN_TYPE("boolean"),
    DATE_TYPE("date"),
    NESTED_TYPE("nested"),
    GEO_POINT_TYPE("geo_point"),
    ;

    private final String dataType;

    DataTypeEnum(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public static DataTypeEnum getDataTypeByStr(String str) {
       if (StringUtils.isNoneBlank(str)) {
           for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
               if (dataTypeEnum.name().equals(str)) {
                   return dataTypeEnum;
               }
           }
        }
        return TEXT_TYPE;
    }
}
