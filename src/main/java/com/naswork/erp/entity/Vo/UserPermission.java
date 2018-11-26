package com.naswork.erp.entity.Vo;

import lombok.Data;

import java.util.List;

/**
 * @Program: UserPermission
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-24 16:44:12
 **/

@Data
public class UserPermission {

    private List<String> menuList;

    private Integer roleId;

    private String userName;

    private String roleName;

    private List<String> permissionList;

    private Integer userId;



}
