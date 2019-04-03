package com.naswork.erp.utils.framework.util;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Program: MapUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:21:13
 **/

public class MapUtil {

    public MapUtil() {
    }

    public static <T> Map<String, T> obj2Map(T... objs) {
        Map<String, T> map = new HashMap();
        Object[] var2 = objs;
        int var3 = objs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            T obj = (T)var2[var4];
            Map m = new BeanMap(obj);
            map.putAll(m);
        }

        return map;
    }

    public static <T> List<Map<String, T>> obj2MapList(T... objs) {
        List<Map<String, T>> list = new ArrayList();
        Object[] var2 = objs;
        int var3 = objs.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            T obj = (T)var2[var4];
            Map<String, T> map = obj2Map(obj);
            list.add(map);
        }

        return list;
    }

    public static <T> List<T> map2ObjList(List<Map> maps, Class<T> clazz) {
        if (maps != null && maps.size() != 0) {
            List<T> list = new ArrayList();
            maps.forEach((map) -> {
                list.add(map2Obj(map, clazz));
            });
            return list;
        } else {
            return null;
        }
    }

    public static <T> T map2Obj(Map map, Class<T> clazz) {
        if (map == null) {
            return null;
        } else {
            Object obj = null;

            try {
                obj = clazz.newInstance();
                BeanUtils.populate(obj, map);
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            } catch (InvocationTargetException var6) {
                var6.printStackTrace();
            }

            return (T)obj;
        }
    }

    public static <T> List<Map<String, T>> toReplaceKeyLow(List<Map<String, T>> maps) {
        List<Map<String, T>> rts = new ArrayList();
        Iterator var2 = maps.iterator();

        while(var2.hasNext()) {
            Map<String, T> map = (Map)var2.next();
            rts.add(toReplaceKeyLow(map));
        }

        return rts;
    }

    public static <T> Map<String, T> toReplaceKeyLow(Map<String, T> map) {
        Map reRap = new HashMap();
        if (reRap != null) {
            Iterator var2 = map.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, T> entry = (Map.Entry)var2.next();
                reRap.put(StringUtil.underlineToCamel((String)entry.getKey()), map.get(entry.getKey()));
            }

            map.clear();
        }

        return reRap;
    }

    public static <T> Map<String, T> removeBlank(Map<String, T> map) {
        Set<String> keySet = map.keySet();
        Iterator var2 = keySet.iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            if (map.get(key) != null && map.get(key).toString().trim().length() == 0) {
                map.put(key, (T)null);
            }
        }

        return map;
    }

    public static <T> String map2UrlString(Map<String, T> map) {
        StringBuffer str = new StringBuffer();
        Set<String> keySet = map.keySet();
        Iterator var3 = keySet.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            str.append(key).append("=").append(map.get(key).toString()).append("&");
        }

        return str.substring(0, str.length() - 1);
    }

    public static Map<String, String> prop2Map(Properties prop) {
        Map<String, String> map = new HashMap();
        prop.forEach((propKey, propValue) -> {
            map.put(propKey.toString(), propValue.toString());
        });
        return map;
    }

    public static Properties map2Prop(Map<String, String> map) {
        Properties prop = new Properties();
        Set<String> sets = map.keySet();
        sets.forEach((set) -> {
            if (map.get(set) != null) {
                prop.setProperty(set, (String)map.get(set));
            }

        });
        return prop;
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            Map<String, String> sortMap = new TreeMap(new MapUtil.MapKeyComparator());
            sortMap.putAll(map);
            return sortMap;
        } else {
            return null;
        }
    }

    static class MapKeyComparator implements Comparator<String> {
        MapKeyComparator() {
        }

        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

}
