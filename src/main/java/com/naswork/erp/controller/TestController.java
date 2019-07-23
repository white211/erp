package com.naswork.erp.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.naswork.erp.common.Exception.CommonException;
import com.naswork.erp.common.Result;
import com.naswork.erp.entity.Goods;
import com.naswork.erp.entity.Vo.GoodsVo;
import com.naswork.erp.utils.redis.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Program: TestController
 * @Description:
 * @Author: White
 * @DateTime: 2019-04-18 10:35:59
 **/
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private JedisUtil jedisUtil;

    @GetMapping("/test1/{type}")
    @ResponseBody
    public Result test1(@PathVariable String type){
        jedisUtil.STRINGS.set(type,type);
        return Result.requestBySuccess("tets1成功");
    }

    @GetMapping("/file/download")
    public void fileDownLoad(HttpServletResponse response){
        List<GoodsVo> list = new ArrayList<>();
        GoodsVo goods = new GoodsVo();
        goods.setId(1);
        goods.setGoodsName("egg");
        goods.setPrice(new BigDecimal(5.00));
        goods.setShopId(1);
        goods.setCreateTime(new Date());
        goods.setDescription("is a delicious food");
        list.add(goods);

        GoodsVo goods2 = new GoodsVo();
        goods2.setId(2);
        goods2.setGoodsName("ice");
        goods2.setPrice(new BigDecimal(5.00));
        goods2.setShopId(1);
        goods2.setCreateTime(new Date());
        goods2.setDescription("is a delicious food");
        list.add(goods2);

        String fileName = "excel";
        String sheetName = "第一个 sheet1";

        BaseRowModel object = new BaseRowModel();
        ExcelWriter writer = new ExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(0, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();

    }

    @GetMapping("/excel")
    public String toPage(){
        return "/excel";
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        //创建本地文件
        String filePath = fileName+".xlsx";
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(new Result("创建文件失败"));
        }
    }

}

