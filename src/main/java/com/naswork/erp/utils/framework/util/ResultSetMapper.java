package com.naswork.erp.utils.framework.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: ResultSetMapper
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:21:03
 **/

public class ResultSetMapper {

    public ResultSetMapper() {
    }

    public static List<Map> toMapList(ResultSet rs) {
        ArrayList list = new ArrayList();

        try {
            try {
                ResultSetMetaData meta = rs.getMetaData();

                while(rs.next()) {
                    Map<String, Object> map = new HashMap();

                    for(int i = 1; i <= meta.getColumnCount(); ++i) {
                        String colName = meta.getColumnLabel(i);
                        colName = StringUtil.underlineToCamel(colName);
                        Object value = rs.getObject(i);
                        if (value != null) {
                            map.put(colName, value);
                        }
                    }

                    list.add(map);
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            return list;
        } finally {
            ;
        }
    }

}
