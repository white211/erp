package com.naswork.erp.utils.framework.base;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface BaseMapper<Model,Example> {
    long countByExample(Example example);

    /** @deprecated */
    @Deprecated
    int insert(Model record);

    int insertSelective(Model record);

    /** @deprecated */
    @Deprecated
    List<Model> selectByExampleWithBLOBs(Example example);

    List<Model> selectByExample(Example example);

    Model selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Model record, @Param("example") Example example);

    int updateByExampleWithBLOBs(@Param("record") Model record, @Param("example") Example example);

    int updateByExample(@Param("record") Model record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Model record);

    /** @deprecated */
    @Deprecated
    int updateByPrimaryKey(Model record);

    /** @deprecated */
    @Deprecated
    int updateByPrimaryKeyWithBLOBs(Model record);

    int insertBatch(@Param("record") List<Model> record, @Param("columnsMap") Map<String, String> columnsMap);

}
