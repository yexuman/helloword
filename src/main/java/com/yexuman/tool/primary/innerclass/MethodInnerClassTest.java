package com.yexuman.tool.primary.innerclass;

/**
 * @author yexuman
 * @date 2019/12/25 19:11
 */

/**
 * 方法内部类
 *
 * 定义在一个方法内部的类。方法内部类相对而言要复杂一些
 *
 * 类In就是一个方法内部类。我们的方法内部类的生命周期不超过包含它的方法的生命周期，
 * 也就是说，方法内部类只能在方法中使用。所以在声明的时候，任何的访问修饰符都是没有意义的，
 * 于是Java干脆不允许使用任何的访问修饰符修饰方法内部类,定义和使用时两回事，别看那一大串
 * 定义类的代码，你实际想要使用该类，就必须new对象，而对于方法内部类而言，只能在方法内部new对象
 */
public class MethodInnerClassTest {
    private String name;

    public void sayHello(){
        class In{
            public void showName(){
                System.out.println("my name is : "+name);
            }
        }

        In in = new In();
        in.showName();
    }

    public static void main(String[] args) {
        MethodInnerClassTest out=new MethodInnerClassTest();
        out.sayHello();
    }

    /**
     * 方法内部类的实现原理其实是和成员内部类差不太多的，也是在内部类初始化的时候为其传入一个
     * 外部类实例，区别在哪呢？就在于方法内部类是定义在具体方法的内部的，所以该类除了可以通过传
     * 入的外部实例访问外部类中的字段和方法，对于包含它的方法中被传入的参数也会随着外部类实例一起
     * 初始化给内部类。毋庸置疑的是，方法内部类的封装性比之前介绍的两种(静态内部类和成员内部类)都要完善。
     * 所以一般只有在需要高度封装的时候才会将类定义成方法内部类
     *
     */
}
