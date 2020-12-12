package com.kuang.single;

/**
 * @author springdu
 * @create 2020/12/12 12:16
 */
public class Holder {

    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
