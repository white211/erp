package com.naswork.erp.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("user_name")
    private String userName;
    private String password;
    private String email;
    private String sex;
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}



