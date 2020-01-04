package com.yexuman.tool.primary.generic;

/**
 * @author yexuman
 * @date 2020/1/2 13:55
 */
public class GenericClass<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        GenericClass<String> genericClass=new GenericClass<>();
        genericClass.setData("Generic Class");
        System.out.println(genericClass.getData());

        GenericClass<Integer> genericIntClass=new GenericClass<>();
        genericIntClass.setData(233);
        System.out.println(genericIntClass.getData());
    }
}
