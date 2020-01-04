package com.yexuman.tool.java8.functionalinter;

/**
 * @author yexuman
 * @date 2019/12/18 15:09
 */
public class Test {
    public static void main(String[] args) {
        FunctionalInterDemo demo = (name, age) -> System.out.println("我叫" + name + "，我今年" + age + "岁了");
        demo.say("金庸", 99);


        //Lambda的写法 ABC
        System.out.println(toUpperString(str -> str.toUpperCase(), "abc"));
        //匿名内部类的写法
        System.out.println(toUpperString(new MyNumber<String>() {
            @Override
            public String getValue(String s) {
                return s;
            }
        },"abc"));

    }

    public static String toUpperString(MyNumber<String> mn, String str) {
        return mn.getValue(str);
    }
}
