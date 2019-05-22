package com.benma.graphql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data

public class Author extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -752887094969054204L;
    @Id
    private String id;

    private String firstName;

    private String lastName;
}
