package com.naswork.erp.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
@Data
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("user_id")
    private Integer userId;
    private String address;
    @TableField("create_time")
    private Date createTime;
    private String description;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
