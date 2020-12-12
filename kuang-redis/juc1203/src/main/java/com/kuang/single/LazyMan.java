package com.kuang.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author springdu
 * @create 2020/12/12 11:59
 */

// 懒汉式单例
// 道高一尺，魔高一丈！
public class LazyMan {

    private static boolean qingjiang = false;

    private LazyMan() {

        synchronized (LazyMan.class) {
//            if (lazyMan != null) {
//                throw new RuntimeException("不要试图使用反射破坏异常");
//            }
            if (qingjiang == false) {
                qingjiang = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }


//        System.out.println(Thread.currentThread().getName() + " ok");
    }

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // 不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }

    // 反射！
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
//        LazyMan instance = LazyMan.getInstance();

        Field qingjiang = LazyMan.class.getDeclaredField("qingjiang");
        qingjiang.setAccessible(true);


        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance = declaredConstructor.newInstance();
//        LazyMan instance2 = declaredConstructor.newInstance();

        qingjiang.set(instance, false);

        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);

    }




    // 单线程下确实单例OK

    // 多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazyMan.getInstance();
//            }).start();
//        }
//    }



    /**
     * 1. 分配内存空间
     * 2. 执行构造方法，初始化对象
     * 3. 把这个对象指向这个空间
     *
     * 123
     * 132 A
     *     B // 此时lazyMan还没有完成构造
     *
     */
}
