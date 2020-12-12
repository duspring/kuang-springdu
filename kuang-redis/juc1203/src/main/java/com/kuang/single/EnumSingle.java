package com.kuang.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author springdu
 * @create 2020/12/12 12:36
 */
// enum 是一个什么？ 本身也是一个Class类
public enum  EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }

}

class Test {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        EnumSingle instance1 = EnumSingle.INSTANCE;
//        EnumSingle instance2 = EnumSingle.INSTANCE;
//        System.out.println(instance1);
//        System.out.println(instance2);
        EnumSingle instance1 = EnumSingle.INSTANCE;
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        // Exception in thread "main" java.lang.NoSuchMethodException: com.kuang.single.EnumSingle.<init>()

        //Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum object
        System.out.println(instance1);
        System.out.println(instance2);

    }
}
