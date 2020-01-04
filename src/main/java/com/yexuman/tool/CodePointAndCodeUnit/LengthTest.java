package com.yexuman.tool.CodePointAndCodeUnit;

/**
 * @author yexuman
 * @date 2019/12/17 18:59
 */
public class LengthTest {

    /**
     * 自 Java 1.5 java.lang.String就提供了Code Point方法， 用来获取完整的Unicode字符和Unicode字符数量:
     * String.length()返回Unicode code units的长度。
     *   public int codePointAt(int index)
     *   public int codePointBefore(int index)
     *   public int codePointCount(int beginIndex, int endIndex)
     *  注意这些方法中的index使用的是code unit值
     *
     * String.getBytes()如果不指定编码格式，Java会使用操作系统的编码格式得到字节数组，在我的MacOS中，默认使用UTF-8作为字符编码(locale命令可以查看操作系统的编码)，所以在我的机器运行，String.getBytes()会返回UTF-8编码的字节数组。
     *
     * String.length返回Unicode code units的长度。
     *
     * String.toCharArray返回字符数组。
     *
     * @param args
     */

    /**
     * Code Point：代码点，一个字符的数字表示。一个字符集一般可以用一张或多张由多个行和多个列所构成的二维表来表示。
     *              二维表中行与列交叉的点称之为代码点，每个码点分配一个唯一的编号数字，称之为码点值或码点编号，除开
     *              某些特殊区域(比如代理区、专用区)的非字符代码点和保留代码点，每个代码点唯一对应于一个字符。从U+0000 到
     *              U+10FFFF。
     * Code Unit：代码单元，是指一个已编码的文本中具有最短的比特组合的单元。对于 UTF-8 来说，代码单元是 8 比特长；
     *              对于 UTF-16 来说，代码单元是 16 比特长。换一种说法就是 UTF-8 的是以一个字节为最小单位的，UTF-16 是以
     *              两个字节为最小单位的。
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        // 中文常见字
        System.out.println("=============中文常见字==============");
        String s = "你好";
        System.out.println("1. string length =" + s.length());
        System.out.println("1. string codepoint =" + s.codePointAt(0));
        System.out.println("1. string codepointcount =" + s.codePointCount(0,s.length()));
        System.out.println("1. string bytes length =" + s.getBytes().length);
        System.out.println("1. string char length =" + s.toCharArray().length);
        System.out.println();
        System.out.println("=============表情==============");
        // emojis
        s = "👦👩";
        System.out.println("2. string length =" + s.length());
        System.out.println("2. string codepoint =" + s.codePointAt(0));
        System.out.println("2. string codepointcount =" + s.codePointCount(0,s.length()));
        System.out.println("2. string bytes length =" + s.getBytes().length);
        System.out.println("2. string char length =" + s.toCharArray().length);
        System.out.println();
        System.out.println("=============中文生僻字==============");
        // 中文生僻字
        s = "𡃁妹";
        System.out.println("3. string length =" + s.length());
        System.out.println("3. string codepoint =" + s.codePointAt(0));
        System.out.println("3. string codepointcount =" + s.codePointCount(0,s.length()));
        System.out.println("3. string bytes length =" + s.getBytes().length);
        System.out.println("3. string char length =" + s.toCharArray().length);
        System.out.println();
    }
}
