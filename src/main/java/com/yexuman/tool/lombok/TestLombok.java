package com.yexuman.tool.lombok;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

/**
 * @author yexuman
 * @date 2019/12/12 11:05
 */
public class TestLombok {
    public static void main(String[] args) {
        //@Builder 不用写构造函数  相当于只用两个属性的构造函数
        Student student = Student.builder()
                .name("张三")
                .age(18)
                .build();
        System.out.println(student);
        System.out.println("=====================================");
        //修改属性
        String name = "李四";
        student = student.toBuilder()
                .name(name)
                .age(22)
                .sex("男")
                .build();
        System.out.println(student);
        System.out.println("================验证链式set @Accessors(chain = true)注解===================");
        Student student2 =new Student()
                .setAge(25)
                .setName("张飞")
                .setSex("男");
        System.out.println(student2);
        System.out.println("=============静态构造方法========================");
        List<String> list= Lists.newArrayList();
        HashMap<String, String> objectObjectHashMap = Maps.newHashMap();
        Student student3=Student.of("李白")
                .setAge(74)
                .setSex("男");
        System.out.println(student3);


    }
}
