package com.yexuman.tool.primary.innerclass;

/**
 * @author yexuman
 * @date 2019/12/25 19:20
 */

/**
 * 匿名内部类必定是要依托一个父类的，因为它是没有名字的，无法用一个具体的类型来表示。
 * 所以匿名内部类往往都是通过继承一个父类，重写或者重新声明一些成员来实现一个匿名内部类
 * 的定义。实际上还是利用了里式转换原理。
 */
public class AnonymousInnerClassTest {
    private String name;

    public void sayHello(){
        System.out.println("my name is :" + name);
    }

    /**
     *   定义和使用一个匿名内部类
     */

    public static void main(String [] args){
        AnonymousInnerClassTest out = new AnonymousInnerClassTest(){
            @Override
            public void sayHello(){
                System.out.println("my name is cxk");
            }
            public void showName(){
                System.out.println("hello single");
            }
        };
        out.sayHello();
    }

}
