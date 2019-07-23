package com.naswork.erp.entity.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Program: GoodsVo
 * @Description:
 * @Author: White
 * @DateTime: 2019-04-26 16:04:27
 **/
@Data
public class GoodsVo extends BaseRowModel {

    @ExcelProperty(value = "id" ,index = 0)
    private Integer id;

    @ExcelProperty(value = "商店id" ,index = 1)
    private Integer shopId;

    @ExcelProperty(value = "创建时间" ,index = 2)
    private Date createTime;

    @ExcelProperty(value = "商品价格" ,index = 3)
    private BigDecimal price;

    @ExcelProperty(value = "商品描述" ,index = 4)
    private String description;

    @ExcelProperty(value = "商品名称" ,index = 5)
    private String goodsName;


}
