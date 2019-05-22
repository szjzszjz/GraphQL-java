package com.benma.graphql.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;

@Data
@DynamicUpdate
public class BaseEntity implements Serializable {


    private static final long serialVersionUID = 5317559077564199349L;
    private Date createTime;

    private Date updateTime;
}
