package com.naswork.erp.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.naswork.erp.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.naswork.erp.entity.Vo.UserPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserListByPage(Page page);

    UserPermission getUserRoleByUsername(String username);

}


