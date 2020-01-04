package com.yexuman.tool.primary;

import java.util.ArrayList;

/**
 * @author yexuman
 * @date 2019/12/17 20:30
 */
public class VariableParamsTest {
    public static void main(String[] args) {
        print("我");
        print("我","好");
        print("我","好","帅");
        print("我","好","帅","啊");
        System.out.println("====================");
        testArrayArgs();
        System.out.println("====================");
        //%d 表示将整数格式化为 10 进制整数，%s 表示输出字符串
        System.out.println(String.format("年纪是: %d", 18));
        System.out.println(String.format("年纪是: %d 名字是: %s", 18, "胡歌"));
    }

    private static void testArrayArgs() {
        print(new String[]{"我"});
        print(new String[]{"我","好"});
        print(new String[]{"我","好","帅"});
        print(new String[]{"我","好","帅","啊"});
    }

    /**
     * 当使用可变参数的时候，实际上是先创建了一个数组，该数组的大小就是可变参数的个数，
     * 然后将参数放入数组当中，再将数组传递给被调用的方法
     * 
     * 所以数组作为参数来调用带有可变参数的方法
     * @param strs
     */
    public static void print(String...strs) {
        for (String s : strs) {
            System.out.print(s);
        }
        System.out.println();
    }

    public void test(ArrayList<String> list,String...strs){

    }
}
