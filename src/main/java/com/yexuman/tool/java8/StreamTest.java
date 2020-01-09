package com.yexuman.tool.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yexuman
 * @date 2019/11/4 14:56
 */
public class StreamTest {

    public static void aboutMap() {
        HashMap<String, Integer> map
                = new HashMap<>();
        map.put("key1", 10000);
        map.put("key2", 20000);
        map.put("key3", 30000);
        map.put("key4", 40000);

        // print map details
        System.out.println("HashMap:\n " + map.toString());

        // provide value for new key which is absent
        // using computeIfAbsent
        Integer val3 = map.computeIfAbsent("key3", k -> 30000 + 1);
        System.out.println(val3);
        map.computeIfAbsent("key5", k -> 20000 + 33000);
        Integer val6 = map.computeIfAbsent("key6", k -> 20000 * 34);
        System.out.println(val6);
        // print new mapping
        System.out.println("New HashMap:\n " + map);
    }

    public static void main(String[] args) {

        aboutMap();
        System.out.println("*****************************************************");
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            list.add(i + "");
        }
        List<String> list2 = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list2.add(i + "");
        }

        res.add(list);
        res.add(list2);
        List<Stream<String>> collect1 = res.stream()
                .map((li -> li.stream().map(s -> s + "处理")))
                .collect(Collectors.toList());
        List<String> collect2 = res.stream().
                flatMap(li -> li.stream().map(s -> s + "处理")).
                collect(Collectors.toList());
        System.out.println(111);


        List<Integer> list3 = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list3.add(i);
        }

        int maxRes = list3.stream().max((o1, o2) -> o1.compareTo(o2)).get();

        //list = list.stream().filter(e -> e.endsWith("a")).collect(Collectors.toList());
        list = list.stream().map(e -> e + "ccc").collect(Collectors.toList());
        System.out.println("*****************************");
        list.forEach(System.out::println);


        System.out.println(maxRes);
        int sumList = list3.stream().reduce(0, (x, y) -> x + y);
        System.out.println("求和：" + sumList);
        int sumList2 = list3.stream().reduce(0, Integer::sum);
        System.out.println("求和2：" + sumList2);


        /**
         * T reduce(T identity, BinaryOperator<t>accumulator)
         * identity：它允许用户提供一个循环计算的初始值。
         *
         * accumulator：计算的累加器，其方法签名为apply(T t,U u)，在该reduce方法中第一个参数t为上次函数计算的返回值，
         *
         * 第二个参数u为Stream中的元素，这个函数把这两个值计算apply，得到的和会被赋值给下次执行这个方法的第一个参数。
         */

        System.out.println("给定个初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::sum));
        System.out.println("给定个初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (min, item) -> Math.min(min, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::min));
        System.out.println("给定个初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, (max, item) -> Math.max(max, item)));
        System.out.println(Stream.of(1, 2, 3, 4).reduce(100, Integer::max));

        //Optional<T> reduce(BinaryOperator<T> accumulator);
        // 注意返回值，上面的返回是T,泛型，传进去啥类型，返回就是啥类型。
        // 下面的返回的则是Optional类型
        System.out.println("无初始值，求和");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum).orElse(0));
        System.out.println("无初始值，求max");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::max).orElse(0));
        System.out.println("无初始值，求min");
        System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::min).orElse(0));

    }
}
