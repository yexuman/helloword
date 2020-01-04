package com.yexuman.tool.SPI;


import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @author yexuman
 * @date 2019/12/10 10:42
 */
public class DubboSPITest {
    public void sayHello()  {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }

    public static void main(String[] args) {
        new DubboSPITest().sayHello();
    }
}
