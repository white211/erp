package com.naswork.erp.service.impl;

import com.naswork.erp.common.Result;
import com.naswork.erp.entity.Menu;
import com.naswork.erp.entity.Vo.UserPermission;
import com.naswork.erp.mapper.MenuMapper;
import com.naswork.erp.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author White
 * @since 2018-11-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result getUserPermission(String username) {
        UserPermission userPermission = new UserPermission();

        return null;
    }

    @Override
    public Result getmenuListByUsername(String username) {
        List<String> menuList = menuMapper.getmenuListByUsername(username);
        return Result.requestBySuccess(menuList);
    }

    @Override
    public Result getpermissionListByUsername(String username) {
        List<String> permissionList = menuMapper.getpermissionListByUsername(username);
        return Result.requestBySuccess(permissionList);
    }
}
