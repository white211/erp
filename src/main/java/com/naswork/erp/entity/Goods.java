package com.naswork.erp.entity;

import java.math.BigDecimal;
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
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("shop_id")
    private Integer shopId;
    @TableField("create_time")
    private Date createTime;
    private BigDecimal price;
    private String description;
    @TableField("goods_name")
    private String goodsName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
