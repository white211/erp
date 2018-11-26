package com.naswork.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author White
 * @since 2018-11-24
 */
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("menu_code")
    private String menuCode;
    @TableField("menu_name")
    private String menuName;
    @TableField("permission_code")
    private String permissionCode;
    @TableField("permission_name")
    private String permissionName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", id=" + id +
        ", menuCode=" + menuCode +
        ", menuName=" + menuName +
        ", permissionCode=" + permissionCode +
        ", permissionName=" + permissionName +
        "}";
    }
}
