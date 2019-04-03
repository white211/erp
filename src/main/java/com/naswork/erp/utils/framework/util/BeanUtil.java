package com.naswork.erp.utils.framework.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Program: BeanUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:13:57
 **/

public class BeanUtil {

    public BeanUtil() {
    }

    public static <T> T removeBlank(T obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                PropertyDescriptor[] var3 = propertyDescriptors;
                int var4 = propertyDescriptors.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    PropertyDescriptor property = var3[var5];
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if (value != null && value.toString().trim().length() == 0) {
                        Method setter = property.getWriteMethod();
                        setter.invoke(obj, null);
                    }
                }
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            return obj;
        }
    }

    public static <S, T> void cp(S source, T target) {
        if (source != null && target != null) {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        }
    }

    public static <S, T> List<T> cp(List<S> source, Class<T> clazz) {
        if (source != null && source.size() != 0) {
            ArrayList list = new ArrayList();

            try {
                Iterator var3 = source.iterator();

                while(var3.hasNext()) {
                    S s = (S)var3.next();
                    T t = clazz.newInstance();
                    cp(s, t);
                    list.add(t);
                }
            } catch (InstantiationException var6) {
                var6.printStackTrace();
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            }

            return list;
        } else {
            return null;
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet();
        PropertyDescriptor[] var4 = pds;
        int var5 = pds.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            PropertyDescriptor pd = var4[var6];
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return (String[])emptyNames.toArray(result);
    }
}
