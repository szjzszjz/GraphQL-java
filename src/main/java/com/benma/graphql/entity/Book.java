package com.benma.graphql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Book extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 5712330623612165679L;
    @Id
    private String id;

    private String name;

    private Integer pageCount;

    private String authorId;
}
