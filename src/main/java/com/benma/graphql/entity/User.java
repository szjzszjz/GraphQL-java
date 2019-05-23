package com.benma.graphql.entity;

import com.benma.graphql.enums.GenderEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author:szjz
 * date:2019/5/23
 */

@Data
@DynamicUpdate
@Entity(name = "user")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7382473842690949537L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /** 平台用户姓名 */
    @Column(nullable = false)
    private String username;

    /** 平台用户密码 */
    @Column(nullable = false)
    private String password;

    /** 性别 默认未知 0 男 1 女 2*/
    private Integer gender = GenderEnum.UNKNOWN.getCode();
    
    /** 手机号 */
    @Column(nullable = false)
    private String phone;


}
