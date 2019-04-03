package com.naswork.erp.controller;


import com.naswork.erp.common.Result;
import com.naswork.erp.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author White
 * @since 2018-11-23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("user:list")
    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable("id") int userId){
        return userService.getUserById(userId);
    }

    @RequiresPermissions("user:list")
    @GetMapping("/getUserListPage")
    public Result getUserListPage(@PathParam("current") int current,@PathParam("size") int size){
        return userService.getUserListPage(current,size);
    }

    @RequiresPermissions("user:list")
    @GetMapping("/getUserListPageBySearch")
    public Result getUserListPageBySearch(HttpServletRequest request){
        return userService.getUserListPageBySearch(request);
    }

    @RequiresPermissions("user:update")
    @PostMapping("/updatePassword")
    public Result updatePassword(HttpServletRequest request){
        return userService.updatePassword(request);
    }

    @PostMapping("/insertUser")
    @RequiresPermissions("user:add")
    public Result insertUser(HttpServletRequest request){
        return userService.insertUser(request);
    }

    @DeleteMapping("/deleteUserById")
    @RequiresPermissions("user:delete")
    public Result deleteUserById(HttpServletRequest request){
        return userService.deleteUserById(request);
    }

    @PostMapping("/insertByExcel")
    @RequiresPermissions(value = "user:add",logical = Logical.OR)
    public Result insertByExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return userService.insertByExcel(file,request);
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request){
        return userService.login(request);
    }

    @PostMapping("/getInfo")
    public Result getInfo(String username){
        return userService.getInfo(username);
    }

    @PostMapping("/logout")
    public Result logout(){
        return userService.logout();
    }


}







