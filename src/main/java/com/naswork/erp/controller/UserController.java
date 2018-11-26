package com.naswork.erp.controller;


import com.naswork.erp.common.Result;
import com.naswork.erp.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @GetMapping("/getUserListPage")
    public Result getUserListPage(@PathParam("current") int current,@PathParam("size") int size){
        return userService.getUserListPage(current,size);
    }

    @GetMapping("/getUserListPageBySearch")
    public Result getUserListPageBySearch(HttpServletRequest request){
        return userService.getUserListPageBySearch(request);
    }

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
    public Result deleteUserById(HttpServletRequest request){
        return userService.deleteUserById(request);
    }

    @PostMapping("/insertByExcel")
    public Result insertByExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        return userService.insertByExcel(file,request);
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request){
        return userService.login(request);
    }

    @PostMapping("/getInfo")
    public Result getInfo(){
        return userService.getInfo();
    }


}







