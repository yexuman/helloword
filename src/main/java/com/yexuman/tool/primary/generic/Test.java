package com.yexuman.tool.primary.generic;

/**
 * @author yexuman
 * @date 2020/1/2 12:55
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Java是怎么实现泛型的？不错，类型擦除。Java编译器将源码编译成字节码的时候会将你在源码中
 * 声明的类型进行擦除，比如：
 * List<String> list = new ArrayList<String>();
 * 在编译后，字节码中只有List，运行在JVM中也是一样的，那你可能会有疑问，既然将类型擦除了，
 * 那为什么我声明的泛型为String类型时，不能往里add一个整型的数据呢？这是因为编译器在编译前
 * 会进行类型检查，类型不一致会直接编译报错
 */
public class Test {
    /**
     * 难道我们一定不能往声明泛型为String的list中增加一个整型元素吗？
     * 既然是在编译前检查类型的，编译后又将类型擦除了，那我是不是可以
     * 在运行时通过反射将整型数字add进去？不错！确实是可以的。
     * @param args
     */
    public static void main(String[] args) throws Exception {
        List<Long> list = new ArrayList<Long>();
        list.add(100L);
        list.getClass().getMethod("add", Object.class).invoke(list, "abc");
        // 然后我们将list打印出来
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        /**
         * 对于上面这个list中第二个元素为"abc"，那我们分别以不同的类型来赋值
         * 无论你是用String还是Long类型来接收都会报错,其实这一切都是因为类型擦除，对于不能使用
         * String类型来接收是因为编译器会做检查，因为list声明的泛型是Long类型的，而使用String
         * 类型来赋值显然编译器会报错，第二种你使用Long类型来接收，编译器当然会认为是合法的，
         * 但是在运行的时候，list中的第二个实际值是String，强转成Long当然会报错了。这一切的根源是
         * 使用反射向list中放入了一个和声明不同类型的数据。
         */
//        String s = "";
//        Long a = 0L;
//        s = list.get(1);
//        a = list.get(1);

        /**
         * Java使用类型擦除来实现泛型有什么好处呢？
         * 1、第一点我们将如此多的泛型在编译时擦除了，那么在运行时显然可以省不少的内存空间嘛。
         * 2、第二点不得不说下兼容性，Java是在1.5版本推出的泛型，那1.5之前存在大量的线上代码没有泛型的，
         *    不能舍弃吧，所以编译擦除后和没有泛型不是一样吗，这就兼容了之前更老的Java版本。
         *
         * 类型擦除带来的问题？
         * 1、不能使用基本数据类型
         * 对于基本数据类型我们必须使用它的装箱类，那在我们使用过程中必然会平凡的涉及到拆箱和装箱的操作，
         * 这必定带来一定的资源开销，所以谷歌在针对key是int类型的情况下，使用SparseArray来代替HashMap。
         * 2、不能用来方法的重载
         *   在不同的泛型作为参数时，编译器编译时进行类型擦除，那参数就一样了
         * 3、泛型类型不能当作真实的类型使用
         * 4、静态方法无法引用类的泛型类型 Java中的泛型是类实例化的时候才能确定泛型的准确类型，而静态方法
         *   是不需要类实例化就能调用的，显然不能使用
         * 5、类型强转的开销
         *   在Java1.5之前的版本，必须要进行强转才能使用自己想要的类型。
         *   那Java1.5及以后的版本呢？有兴趣的可以看看ArrayList的源码，它的get方法还是会做强转的
         */
    }
}
