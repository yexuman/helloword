package com.yexuman.tool.primary.innerclass;

/**
 * @author yexuman
 * @date 2019/12/25 19:06
 */

import lombok.Data;

/**
 * 成员内部类
 *
 * 静态内部类适合于那种和外部类关系密切但是并不依赖外部类实例的情况。但是对于需要和外部类
 * 实例相关联的情况下，可以选择将内部类定义成成员内部类
 */
@Data
public class MemberInnerClassTest {
    private String name;

    public void showName(){
        System.out.println("my name is : "+name);
    }

    public class In{
        public void sayHello(){
            System.out.println(name);
            MemberInnerClassTest.this.showName();
        }
    }

    public static void main(String [] args){
        MemberInnerClassTest out = new MemberInnerClassTest();
        out.setName("成员内部类你好！");
        MemberInnerClassTest.In in = out.new In();
        in.sayHello();
    }

}
