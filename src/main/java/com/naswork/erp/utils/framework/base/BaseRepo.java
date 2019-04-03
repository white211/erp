package com.naswork.erp.utils.framework.base;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaseRepo<Model> {

    Model get(Integer id);

    Model get(Model model);

    Integer update(Model model, HttpServletRequest req);

    Integer insert(Model model, HttpServletRequest req);

    Integer insert(List<Model> models, String[] columns, HttpServletRequest req);

    Integer insert(List<Model> models, List<String> columns, HttpServletRequest req);

    long count();

    long count(Model model);

    Integer del(Integer id, HttpServletRequest req);

    Integer del(String ids, HttpServletRequest req);

    Integer del(List<Integer> ids, HttpServletRequest req);

    List<Model> list();

    List<Model> list(Model model);

    PageData<Model> page();

    PageData<Model> page(Model model);
}
