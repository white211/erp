#### 系统设计-后台
**1.引入mybatis-plus使用说明**  
1. 需要引入com.naswork.erp.config.MybatisPlusConfig
1. 执行com.naswork.erp.common.CodeGernerator(注意需要修改具体的生成路径)   
2. 根据提示在控制台输入要生成的表名  
3. 自动生成entity,mapper,controller,service,配置文件，官网生成的文件都是空的，如果需要生成特定内容自行查找资料创建模板  
4. 生成完之后需要在Mapper类中添加 ```@Mapper```注释，项目才能启动    
5. mybatis-plus提供很好的增删查改，并且封装了分页查询等等，使用更加方便  
**2.引入shiro使用说明**       
1. RBAC控制权限模式，即用户-角色-权限    
1. 控制到按钮级别，即在接口上面添加```@RequiresPermissions(value="user:list")``` 而不是```@RequiresRoles("admin")```
1. 用户登陆成功之后获取用户权限信息，如下：     
```
{
    "status": 0,
    "msg": null,
    "data": {
        "menuList": [
            "shop",
            "user"
        ],
        "roleId": 1,
        "userName": "white",
        "roleName": "超级管理员",
        "permissionList": [
            "user:list",
            "user:add",
            "user:update",
            "user:delete",
            "shop:list",
            "shop:add"
        ],
        "userId": 1
    }
}
```

1. ##### 未登录访问需要检验的接口返回结果       
```

```
2. ##### 未登录访问接口        
```

```
3. ##### error报错 返回结果       
```

```
4. ##### 请求方法错误返回结果     
```

```
5. ##### 

#### 技术栈
##### 1. 后台主体框架springboot
##### 2. 数据持久层mybatis-plus
##### 3. 权限认证shiro
##### 4. 会话验证JWT
##### 5. 前端框架Vue.js
##### 6. 工作流activity
##### 7. 关系型数据库mysql
##### 8. 缓存数据库redis
##### 9. 集群ES
##### 10.缓存ehcache

#### 开发环境
##### 1. 服务器tomcat8
##### 2. java开发语言版本jdk1.8

#### 开发工具
##### 1. Navicat
##### 2. IntelliJ IDEA
##### 3. VSCode
##### 4. WebStorm
##### 5. postman
##### 6. Chrome



