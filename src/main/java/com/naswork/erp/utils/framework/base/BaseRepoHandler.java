package com.naswork.erp.utils.framework.base;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.naswork.erp.utils.framework.pojo.dto.User;
import com.naswork.erp.utils.framework.util.*;
import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: BaseRepoHandler
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 10:58:38
 **/

public class BaseRepoHandler {

    protected static final int INSERT_SIZE = 1200;

    public BaseRepoHandler() {
    }

    private static Integer getUserId(HttpServletRequest req) {
        if (req == null) {
            return null;
        } else {
            User user = (User)req.getSession().getAttribute("user");
            return user != null ? user.getUserId() : null;
        }
    }

    protected static <T extends BaseModel> T setBaseInfo(T model, HttpServletRequest req) {
        Integer userId = getUserId(req);
        if (userId != null) {
            model.setLastUpdateBy(userId);
            if (model.getId() == null && model.getCreateBy() == null) {
                model.setCreateBy(userId);
            }
        }

        model.setLastUpdateTime(new Date());
        return model;
    }

    protected static <T extends BaseModel> boolean pagePreHandle(T model) {
        BeanUtil.removeBlank(model);
        boolean isPage = model.getIsPage() == null || model.getIsPage() == 1;
        if (isPage) {
            model.init();
            PageHelper.startPage(model.getPageNo(), model.getPageSize());
        }

        String orderBy = model.getOrderBy();
        if (orderBy != null && !orderBy.equals("sort, id desc") && sqlInj(orderBy)) {
            throw new RuntimeException("orderBy 有注入风险，请谨慎操作！");
        } else {
            model.setOrderBy(StringUtil.check2LowerCase(orderBy, "DESC"));
            model.setOrderBy(StringUtil.check2LowerCase(orderBy, "ASC"));
            model.setOrderBy(StringUtil.camelToUnderline(orderBy));
            if (model.getKeyword() != null && model.getKeyword() != "") {
                model.setKeyword("%" + model.getKeyword() + "%");
            }

            DateUtil.formatDateRange(model);
            return isPage;
        }
    }

    protected static <T extends BaseModel> PageData<T> pageSelect(T model, Object list) {
        boolean isPage = model.getIsPage() == null || model.getIsPage() == 1;
        PageData<T> pageData = new PageData(model.getPageNo(), model.getPageSize());
        if (isPage) {
            Page<T> listPage = (Page)list;
            pageData.setTotalCount(listPage.getTotal());
        } else {
            pageData.setPageNo((Integer)null);
            pageData.setPageSize((Integer)null);
            pageData.setTotalPage((Integer)null);
        }

        pageData.setRows((List)list);
        return pageData;
    }

    protected static <T> PageData<Map<String, T>> pageSelect4Map(BaseModel model, List<Map<String, T>> list) {
        boolean isPage = model.getIsPage() == null || model.getIsPage() == 1;
        PageData<Map<String, T>> pageData = new PageData(model.getPageNo(), model.getPageSize());
        if (isPage) {
            Page<Map<String, T>> listPage = (Page)list;
            pageData.setTotalCount(listPage.getTotal());
        } else {
            pageData.setPageNo((Integer)null);
            pageData.setPageSize((Integer)null);
            pageData.setTotalPage((Integer)null);
        }

        pageData.setRows(MapUtil.toReplaceKeyLow(list));
        return pageData;
    }

    protected static <M, T extends BaseModel> PageData<T> ansyList2Page(T model, List<M> list) {
        boolean isPage = model.getIsPage() == null || model.getIsPage() == 1;
        PageData<T> pageData = new PageData(model.getPageNo(), model.getPageSize());
        if (isPage) {
            Page<M> listPage = (Page)list;
            pageData.setTotalCount(listPage.getTotal());
        } else {
            pageData.setPageNo((Integer)null);
            pageData.setPageSize((Integer)null);
        }

        return pageData;
    }

    public static <Model> Model getNewInstance(Class clazz) {
        Object model = null;

        try {
            model = clazz.newInstance();
        } catch (InstantiationException var3) {
            var3.printStackTrace();
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        }

        return (Model) model;
    }

    protected static Map<String, String> getMapColumns(List<String> columns) {
        if (columns != null && columns.size() != 0) {
            Map<String, String> columnsMap = new HashMap();
            columns.forEach((column) -> {
                String columnUnderLine = StringUtil.camelToUnderline(column);
                String columnCamel = StringUtil.underlineToCamel(columnUnderLine);
                columnsMap.put(columnCamel, columnCamel);
            });
            return columnsMap;
        } else {
            return null;
        }
    }

    public static <T> List<T> jdbcExecutor(Connection conn, String sql, Class<T> clazz) {
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            List<Map> maps = ResultSetMapper.toMapList(results);
            List<T> list = MapUtil.map2ObjList(maps, clazz);
            return list;
        } catch (SQLException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public static List<Map> jdbcExecutor(Connection conn, String sql) {
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            List<Map> maps = ResultSetMapper.toMapList(results);
            return maps;
        } catch (SQLException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    private static boolean sqlInj(String str) {
        str = str.toLowerCase();
        String injStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
        String[] injStra = injStr.split("\\|");

        for(int i = 0; i < injStra.length; ++i) {
            String is = injStra[i];
            if (str.indexOf(is) >= 0) {
                return true;
            }
        }

        return false;
    }

}
