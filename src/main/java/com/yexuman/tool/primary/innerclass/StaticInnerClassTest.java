package com.yexuman.tool.primary.innerclass;

/**
 * @author yexuman
 * @date 2019/12/25 18:12
 */

/**
 * 静态内部类
 *
 * 静态内部类的定义和普通的静态变量或者静态方法的定义方法是一样的，使用static关键字，
 * 只不过这次static是修饰在class上的，一般而言，只有静态内部类才允许使用static关键字修饰，
 * 普通类的定义是不能用static关键字修饰的
 *
 * 使用场景，一般来说，对于和外部类联系紧密但是并不依赖于外部类实例的情况下，可以考虑定义成静态内部类。
 */
public class StaticInnerClassTest {

    private static String name;
    private int age;

    public static class In{
        private int age;
        public void sayHello(){

            System.out.println("my name is : "+name);
            //--编译报错---
            //System.out.println("my age is :"+ age);
        }
    }

    public static void main(String [] args){
        StaticInnerClassTest.In innerClass = new StaticInnerClassTest.In();
        innerClass.sayHello();
    }
}
