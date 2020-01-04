package com.yexuman.tool;

/**
 * @author yexuman
 * @date 2019/10/25 17:03
 */
public class JvmArguement {
    public static void main(String[] args) {
        Runtime.getRuntime();             //单例模式
        //byte
        //整体电脑的1/4
        System.out.println("maxMemory："+byteToM(Runtime.getRuntime().maxMemory()));
        //整体电脑的1/16
        System.out.println("totalMemory："+byteToM(Runtime.getRuntime().totalMemory()));
        String a = "123"; String b = "123"; System.out.println(a==b);

        //伸缩空间 ：maxMemory   -  totalMemory
    }
    public static double byteToM(long num){
        return  num/1024/1024;
    }
}
