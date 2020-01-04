package com.yexuman.tool.java8.functionalinter;

/**
 * @author yexuman
 * @date 2019/12/18 14:35
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/**
 * @author yexuman
 * @date 2019/12/18 14:36
 */


/**
 * 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 * 在 Java 8中已经有很多接口已经声明为函数接口，如 Runnable、Callable、Comparator
 * 函数接口在使用时候可以隐式的转换成 Lambda 表达式
 */
public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");

        // 传递参数 n
        process(list,n->true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        process(list, n -> n % 2 == 0);

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        process(list, n -> n > 3);
    }

    /**
     * Predicate <T> 接口是一个函数式接口，它接受一个输入参数 T，返回一个布尔值结果。
     * <p>
     * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）。
     * <p>
     * 该接口用于测试对象是 true 或 false。
     *
     * @param list
     * @param predicate
     */
    public static void process(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i : list) {
            if (predicate.test(i)) {
                System.out.println(i + " ");
            }
        }
    }
}
