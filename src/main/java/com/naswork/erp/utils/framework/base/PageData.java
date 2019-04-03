package com.naswork.erp.utils.framework.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Program: PageData
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-09 15:44:27
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PageData<T> {

    private List<T> rows;
    private Long totalCount;
    private Integer totalPage;
    private Integer pageNo = 1;
    private Integer pageSize = 10;

    public PageData(){
        this.init();
    }

    public PageData(Integer pageNo,Integer pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageData(PageData oldPageData,List<T> pageList){
        this.pageNo = oldPageData.getPageNo();
        this.pageSize = oldPageData.getPageSize();
        this.totalCount = oldPageData.getTotalCount();
        this.totalPage = oldPageData.getTotalPage();
        this.rows = pageList;
    }

    private void init(){
        if(this.pageNo == null || this.pageNo<1){
            this.pageNo = 1;
        }

        if(this.pageSize == null || this.pageSize<1){
            this.pageSize = 10;
        }

        if(this.totalCount == null){
            this.totalCount = 0L;
        }
        this.totalPage = (int) Math.ceil((double)this.totalCount / (double)this.pageSize);
        if(this.totalPage == 0){
            this.totalPage = 1;
        }
        this.pageNo = this.pageNo>this.totalPage?this.totalPage:this.pageNo;
    }

}
