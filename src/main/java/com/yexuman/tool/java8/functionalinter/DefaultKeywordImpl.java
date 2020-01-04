package com.yexuman.tool.java8.functionalinter;

import java.util.List;

/**
 * @author yexuman
 * @date 2019/12/26 10:42
 */
public class DefaultKeywordImpl implements DefaultKeyword {

    /**
     * 无需实现default方法2
     */
    @Override
    public void test1() {

    }

    /**
     * 这个default方法是所有的实现类都不需要去实现的就可以直接调用，那么比如说jdk的集合List里面增加了一个sort方法，
     * 那么如果定义为一个抽象方法，其所有的实现类如arrayList,LinkedList等都需要对其添加实现，那么现在用default定义
     * 一个默认的方法之后，其实现类可以直接使用这个方法了，这样不管是开发还是维护项目，都会大大简化代码量
     *
     * @param args
     */
    public static void main(String[] args) {
        DefaultKeyword dKeyword = new DefaultKeywordImpl();
        dKeyword.test2();
    }
}
