package com.benma.graphql.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@DynamicUpdate
@MappedSuperclass  //该注解实现子类创建表时添加基类属性对应的列
public class BaseEntity implements Serializable{
    //创建时间以当前时间为准 不可插入 不可更新
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    private Date createTime;

    //跟新时间以当前时间为准 不可插入 默认可更新
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",insertable = false)
    private Date updateTime;
}
