package com.naswork.erp.service;

import com.naswork.erp.common.Result;
import com.naswork.erp.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
public interface UserService extends IService<User> {

    Result getUserById(int userId);

    Result getUserListPage(int current,int size);

    Result getUserListPageBySearch(HttpServletRequest request);

    Result updatePassword(HttpServletRequest request);

    Result insertUser(HttpServletRequest request);

    Result deleteUserById(HttpServletRequest request);

    Result insertByExcel(MultipartFile file,HttpServletRequest request);

    Result getUser(String username,String password);

    Result login(HttpServletRequest request);

    Result getInfo();

    Result logout();

}




