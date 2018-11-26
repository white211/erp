package com.naswork.erp.utils;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.naswork.erp.entity.User;
import com.naswork.erp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: ExcelListener
 * @Description:
 * @Author: White
 * @DateTime: 2018-11-24 11:36:52
 **/
@Data
public class ExcelListener extends AnalysisEventListener {

    @Autowired
    private UserService userService;

    private List<Object> datas = new ArrayList<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
        this.writeData(o);
    }

    private void writeData(Object o){
        System.out.println(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }





}
