package com.yexuman.tool.SPI;


import java.util.ServiceLoader;

/**
 * @author yexuman
 * @date 2019/12/9 20:31
 */

/**
 * SPI 机制的约定
 * 1 在 META-INF/services/ 目录中创建以接口全限定名命名的文件，该文件内容为API具体实现类的全限定名
 *
 * 2 使用 ServiceLoader 类动态加载 META-INF-INF 中的实现类
 *
 * 3 如 SPI 的实现类为 Jar 则需要放在主程序 ClassPath 中
 *
 * 4 API 具体实现类必须有一个不带参数的构造方法
 */
public class JavaSPITest {

    /**
     * 优缺点
     * 优点：使用Java SPI机制的优势是实现了解耦，使第三方模块的装配逻辑与业务代码分离。应用程序可以根据实际业务情况使用新的框架拓展
     * 或者替换原有组件。
     *
     * 缺点：ServiceLoader在加载实现类的时候会全部加载并实例化，假如不想使用某些实现类，它也会被加载示例化的，这就造成了浪费。另外
     * 获取某个实现类只能通过迭代器迭代获取，不能根据某个参数来获取，使用方式上不够灵活。
     *
     * Dubbo框架中大量使用了SPI来进行框架扩展，但它是重新对SPI进行了实现，完美的解决上面提到的问题。

     */
    public void sayHello()  {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }

    public static void main(String[] args) {
        try {
            new JavaSPITest().sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
