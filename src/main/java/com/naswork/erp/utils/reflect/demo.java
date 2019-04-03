package com.naswork.erp.utils.reflect;

import java.util.Arrays;

/**
 * @Program: demo
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-08 14:30:48
 **/

public class demo {

    public static void main(String[] args) throws Exception {

        Class concreteClass = ConcreteClass.class;

        concreteClass = new ConcreteClass(7).getClass();

        concreteClass = Class.forName("com.naswork.erp.utils.reflect.ConcreteClass");

        System.out.println(concreteClass.getCanonicalName());
        System.out.println(concreteClass.getName());

        Class primative = boolean.class;
        System.out.println(primative.getCanonicalName());

        Class doubleClass = Double.TYPE;
        System.out.println(doubleClass.getName());

        Class<?> arrayClass = Class.forName("[D");
        System.out.println(arrayClass.getCanonicalName());
        arrayClass = Class.forName("[B");
        System.out.println(arrayClass.getCanonicalName());
        arrayClass = Class.forName("[S");
        System.out.println(arrayClass.getCanonicalName());
        arrayClass = Class.forName("[C");
        System.out.println(arrayClass.getCanonicalName());
        arrayClass = Class.forName("[F");
        System.out.println(arrayClass.getCanonicalName());

        Class<?> superClass = Class.forName("com.naswork.erp.utils.reflect.ConcreteClass").getSuperclass();
        System.out.println(superClass);
        System.out.println(Object.class.getSuperclass());
        System.out.println(String[][].class.getSuperclass());

        Class[] classARR = concreteClass.getClasses();
        System.out.println(Arrays.toString(classARR));


    }


}
