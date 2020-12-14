package com.kuang.staticproxy;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/14 16:44
 *
 * 静态代理模式总结：
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 *
 * 好处：代理对象可以做很多真实对象做不了的事情
 *      真实对象专注做自己的事情
 *
 */


public class StaticProxy {

    public static void main(String[] args) {
        You you = new You(); // 你要结婚
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // to do something
//            }
//        }).start();

        new Thread(() -> System.out.println("我爱你")).start();

        new WeddingCompany(new You()).HappyMarry();


//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.HappyMarry();
    }

}

interface Marry {

    /**
     * 人间四大喜事
     * 久旱逢甘霖
     * 他乡遇故知
     * 洞房花烛夜
     * 金榜题名时
     */
    void HappyMarry();
}

// 真实角色，你去结婚
class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("我要结婚了，超开心");
    }
}

// 代理角色，帮助你结婚
class WeddingCompany implements Marry {

    // 代理谁 -> 真实目标角色
    private Marry target; // 目标对象接口

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry(); // 这就是真实对象
        after();
    }

    /**
     * 结婚之前
     */
    private void before() {
        System.out.println("结婚之前，布置现场！");
    }

    /**
     * 结婚之后
     */
    private void after() {
        System.out.println("结婚之后，收尾款");
    }


}
