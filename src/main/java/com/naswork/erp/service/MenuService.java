package com.naswork.erp.service;

import com.naswork.erp.common.Result;
import com.naswork.erp.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author White
 * @since 2018-11-24
 */
public interface MenuService extends IService<Menu> {

    Result getUserPermission(String username);

    Result  getmenuListByUsername(String username);

    Result getpermissionListByUsername(String username);


}
