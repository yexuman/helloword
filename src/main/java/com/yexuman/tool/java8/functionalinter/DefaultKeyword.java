package com.yexuman.tool.java8.functionalinter;

/**
 * @author yexuman
 * @date 2019/12/26 10:38
 */

/**
 * 在java里面，我们通常都是认为接口里面是只能有抽象方法，不能有任何方法的实现的，那么在jdk1.8里面打破了
 * 这个规定，引入了新的关键字default，通过使用default修饰方法，可以让我们在接口里面定义具体的方法实现
 */
public interface DefaultKeyword {
    public  void test1();

    /**
     * 定义这样一个方法的主要意义是定义一个默认方法，也就是说这个接口的实现类实现了这个接口之后，不用管这个
     * default修饰的方法，也可以直接调用
     */
    public  default void test2(){
        System.out.println("我是新特性1");
    }
}
