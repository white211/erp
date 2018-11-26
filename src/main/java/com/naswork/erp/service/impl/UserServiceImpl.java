package com.naswork.erp.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.naswork.erp.common.Constants;
import com.naswork.erp.common.Result;
import com.naswork.erp.entity.User;
import com.naswork.erp.entity.Vo.UserPermission;
import com.naswork.erp.mapper.UserMapper;
import com.naswork.erp.service.MenuService;
import com.naswork.erp.service.RoleService;
import com.naswork.erp.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.naswork.erp.utils.ExcelListener;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getUserById(int userId) {
        User user = this.baseMapper.selectById(userId);
        return Result.requestBySuccess(user);
    }

    @Override
    public Result<Page> getUserListPage(int current, int size) {
        Page<User> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        page.setRecords(this.baseMapper.selectUserListByPage(page));
        return Result.requestBySuccess(page);
    }

    @Override
    public Result<Page> getUserListPageBySearch(HttpServletRequest request) {
        Page<User> page = new Page();
        String userName = request.getParameter("userName");
        int current = Integer.parseInt(request.getParameter("current"));
        int size = Integer.parseInt(request.getParameter("size"));
        Wrapper<User> entity = new EntityWrapper<User>();
        entity.like("user_name", "%" + userName + "%");
        entity.between("id", 1, 3);
        entity.orderBy("create_time", false);
        page.setCurrent(current);
        page.setSize(size);
        List<User> userList = this.baseMapper.selectPage(page, entity);
        page.setRecords(userList);
        return Result.requestBySuccess(page);
    }

    @Override
    public Result updatePassword(HttpServletRequest request) {
        String id = request.getParameter("userId");
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
        User user = this.baseMapper.selectById(Integer.parseInt(id));
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            this.baseMapper.updateById(user);
            return Result.requestBySuccess("update success", user);
        } else {
            return Result.requestBySuccess("the old password error");
        }
    }

    @Override
    public Result insertUser(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        User user = new User();
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        user.setUserName(userName);
        this.baseMapper.insert(user);
        return Result.requestBySuccess("insert user success", user);
    }

    @Override
    public Result deleteUserById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("password"));
        this.baseMapper.deleteById(id);
        return Result.requestBySuccess("delete success");
    }

    @Override
    public Result insertByExcel(MultipartFile file, HttpServletRequest request) {
        try {
            InputStream inputStream = file.getInputStream();
            ExcelListener excelListener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX,null,excelListener);
            excelReader.read();
        } catch (Exception e) {
            return Result.requestByError("server throw exception");
        }
        return Result.requestBySuccess("upload success");
    }

    @Override
    public Result getUser(String username,String password){
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        User selectOne =  this.baseMapper.selectOne(user);
        return Result.requestBySuccess(selectOne);
    }

    @Override
    public Result login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            currentUser.login(token);
            return Result.requestBySuccess("success");
        }catch (Exception e){
            return Result.requestByError("fail");
        }
    }

    @Override
    public Result getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = user.getUserName();
        String password = user.getPassword();
        UserPermission userPermission = getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION,userPermission);
        return Result.requestBySuccess(userPermission);
    }

    public  UserPermission getUserPermission(String username){
        UserPermission userPermission = new UserPermission();
        userPermission = userMapper.getUserRoleByUsername(username);
        userPermission.setUserName(username);
        List<String> menuList = (List<String>) menuService.getmenuListByUsername(username).getData();
        userPermission.setMenuList(menuList);
        List<String> permissionList = (List<String>) menuService.getpermissionListByUsername(username).getData();
        userPermission.setPermissionList(permissionList);
        return userPermission;
    }

}



