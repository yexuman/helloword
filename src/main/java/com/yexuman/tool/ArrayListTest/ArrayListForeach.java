package com.yexuman.tool.ArrayListTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yexuman
 * @date 2019/11/16 9:41
 */
public class ArrayListForeach {
    public static void main(String[] args) {
        removeListElement1();  // 没有问题
      //  removeListElement2();  // ConcurrentModificationException异常
       // removeListElement3();
    }

    public static void removeListElement1() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("2");
        arrayList.add("1");
//        for (String s : arrayList) {
//            if ("1".equals(s)) {
//                arrayList.remove(s);
//            }
//        }
        for (int i =0;i<arrayList.size();i++){
            if ("1".equals(arrayList.get(i))) {
                arrayList.remove(arrayList.get(i));
            }
        }
        arrayList.stream().forEach(System.out::println);
    }

    public static void removeListElement2() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("2");
        arrayList.add("1");
        for (String s : arrayList) {
            if ("1".equals(s)) {
                arrayList.remove(s);
            }
        }
        arrayList.stream().forEach(System.out::println);
    }

    public static void removeListElement3(){
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("2");
        arrayList.add("1");
        Iterator<String> iterator = arrayList.iterator();
        while(iterator.hasNext()){
            String item = iterator.next();
            if("1".equals(item)){
                iterator.remove();
            }
        }
        arrayList.stream().forEach(System.out::println);
    }
}
