package com.naswork.erp.mapper;

import com.naswork.erp.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author White
 * @since 2018-11-24
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getmenuListByUsername(String username);

    List<String> getpermissionListByUsername(String username);
}
