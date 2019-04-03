package com.naswork.erp.utils.framework.base.impl;

import com.naswork.erp.utils.framework.base.BaseMapper;
import com.naswork.erp.utils.framework.base.BaseModel;
import com.naswork.erp.utils.framework.base.BaseRepoHandler;
import com.naswork.erp.utils.framework.base.PageData;
import com.naswork.erp.utils.framework.util.IntegerUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Program: BaseRepoImpl
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:28:21
 **/

public class BaseRepoImpl<Model extends BaseModel,Example> extends BaseRepoHandler {

    protected BaseMapper<Model, Example> mapper;
    protected Class modelClazz;

    public BaseRepoImpl() {
    }

    public Model get(Integer id) {
        if (id == null) {
            throw new RuntimeException("id is null");
        } else {
            Model model = (Model)this.mapper.selectByPrimaryKey(id);
            return model != null && model.getStatus() != 0 ? model : null;
        }
    }

    public Model get(Model model) {
        model.setIsPage(0);
        List<Model> list = this.list(model);
        if (list.isEmpty()) {
            return null;
        } else if (list.size() > 1) {
            throw new RuntimeException("查询结果不唯一，存在 " + list.size() + " 条，请确保数据完整性，或者换用 list 查询");
        } else {
            return (Model)list.get(0);
        }
    }

    public Integer update(Model model, HttpServletRequest req) {
        if (model.getId() == null) {
            throw new RuntimeException("id is null");
        } else if (model.getVersion() == null) {
            throw new RuntimeException("version is null");
        } else {
            model = setBaseInfo(model, req);
            Model target = (Model)this.mapper.selectByPrimaryKey(model.getId());
            if (target == null) {
                throw new RuntimeException("id is error");
            } else if (!model.getVersion().equals(target.getVersion())) {
                throw new RuntimeException("data is not lastest, can not update more than one time without get new one");
            } else {
                try {
                    Class clazz = model.getClass();
                    Method copyIfNotNull = clazz.getDeclaredMethod("copyIfNotNull", clazz, clazz);
                    copyIfNotNull.invoke((Object)null, model, target);
                } catch (IllegalAccessException var6) {
                    var6.printStackTrace();
                } catch (InvocationTargetException var7) {
                    var7.printStackTrace();
                } catch (NoSuchMethodException var8) {
                    var8.printStackTrace();
                }

                target.setVersion(target.getVersion() + 1);
                return this.mapper.updateByPrimaryKeySelective(target);
            }
        }
    }

    public Integer insert(Model model, HttpServletRequest req) {
        model = setBaseInfo(model, req);
        this.mapper.insertSelective(model);
        return model.getId();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public Integer insert(List<Model> list, String[] columns, HttpServletRequest req) {
        return this.insert(list, Arrays.asList(columns), req);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public Integer insert(List<Model> list, List<String> columns, HttpServletRequest req) {
        if (list != null && list.size() != 0 && columns != null && columns.size() != 0) {
            int size = list.size();
            int counter = 0;
            int seccuss = 0;
            List<Model> tmpList = new ArrayList();
            Iterator var8 = list.iterator();

            while(true) {
                do {
                    if (!var8.hasNext()) {
                        return seccuss;
                    }

                    Model model = (Model)var8.next();
                    ++counter;
                    model = setBaseInfo(model, req);
                    tmpList.add(model);
                } while(counter % 1200 != 0 && counter != size);

                seccuss += this.mapper.insertBatch(tmpList, getMapColumns(columns));
                tmpList = new ArrayList();
            }
        } else {
            return 0;
        }
    }

    public long count() {
        return this.countByExample((Model)null);
    }

    public long count(Model model) {
        return this.countByExample(model);
    }

    public Integer del(Integer id, HttpServletRequest req) {
        return this.del(Arrays.asList(id), req);
    }

    public Integer del(String ids, HttpServletRequest req) {
        return this.del(IntegerUtil.str2IntegerList(ids), req);
    }

    public Integer del(List<Integer> ids, HttpServletRequest req) {
        if (ids != null && ids.size() != 0) {
            Model model = (Model)getNewInstance(this.modelClazz);
            Object example = null;

            try {
                Method createDelExample = this.modelClazz.getDeclaredMethod("createDelExample", List.class);
                example = createDelExample.invoke((Object)null, ids);
            } catch (IllegalAccessException var6) {
                var6.printStackTrace();
            } catch (InvocationTargetException var7) {
                var7.printStackTrace();
            } catch (NoSuchMethodException var8) {
                var8.printStackTrace();
            }

            model = setBaseInfo(model, req);
            model.setStatus(0);
            return this.mapper.updateByExampleSelective(model, (Example) example);
        } else {
            return 0;
        }
    }

    public List<Model> list() {
        return this.list((Model)null);
    }

    public List<Model> list(Model model) {
        if (model == null) {
            model = (Model)getNewInstance(this.modelClazz);
        }

        model.setIsPage(0);
        return this.selectByExample(model).getRows();
    }

    public PageData<Model> page() {
        return this.selectByExample((Model)null);
    }

    public PageData<Model> page(Model model) {
        return this.selectByExample(model);
    }

    private PageData<Model> selectByExample(Model model) {
        pagePreHandle(model);
        Example example = this.getExample(model);
        List<Model> list = this.mapper.selectByExample(example);
        PageData pageData = ansyList2Page(model, list);
        pageData.setRows(list);
        return pageData;
    }

    private long countByExample(Model model) {
        Example example = this.getExample(model);
        return this.mapper.countByExample(example);
    }

    private Example getExample(Model model) {
        Object example = null;

        try {
            Class clazz = model.getClass();
            Method createExample = clazz.getDeclaredMethod("createExample", clazz);
            example = createExample.invoke((Object)null, model);
            return (Example) example;
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        } catch (InvocationTargetException var6) {
            var6.printStackTrace();
        } catch (NoSuchMethodException var7) {
            var7.printStackTrace();
        }

        return null;
    }

}
