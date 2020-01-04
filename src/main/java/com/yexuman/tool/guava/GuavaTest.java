package com.yexuman.tool.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.yexuman.tool.lombok.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yexuman
 * @date 2019/12/23 15:33
 */
public class GuavaTest {
    /**
     * 切割器
     */
    private static final Splitter splitter = Splitter.on(",")
            .trimResults()
            .omitEmptyStrings();
    /**
     * 连接器
     * 跳过NULL元素：skipNulls() / 对于NULL元素使用其他替代：useForNull(String)
     */
    private static final Joiner joiner = Joiner.on(",")
            .skipNulls();
    /**
     * 定义缓存的实现
     */
    private static final CacheLoader<Long, Student> useCacheLoader = new CacheLoader<Long, Student>() {
        @Override
        public Student load(Long aLong) throws Exception {
            //模拟从数据库/REDIS/缓存中加载数据
            Student student = Student.builder()
                    .id(aLong)
                    .build();

            student.setName(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + "-" + aLong);
            System.out.println("load: " + student);
            return student;
        }
    };

    /**
     * 定义缓存的策略
     */
    private static final LoadingCache<Long, Student> useCacheData = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .refreshAfterWrite(3,TimeUnit.SECONDS)
            .maximumSize(10000L)
            .build(useCacheLoader);

    public static void main(String[] args) {

        System.out.println("=======================1.连接器和切割器=====================================");

        /**
         *  1.连接器和切割器
         */
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println("join = " + join);

        for (String tmp : splitter.split("a, , ,b,,c")) {
            System.out.println("tmp = " + tmp);
        }

        System.out.println("=======================2.CharMatcher=====================================");

        /**
         *  2.CharMatcher，将字符的匹配和处理解耦
         */

        System.out.println("=======================3.原生类型扩展=====================================");

        /**
         *  3. guava对JDK提供的原生类型操作进行了扩展
         */
        List<Integer> list = Ints.asList(1, 3, 5, 7, 9);
        System.out.println(Ints.join(",", 1, 3, 1, 4));

        System.out.println("=======================4.Multiset=====================================");

        /**
         *  4. Multiset是什么，我想上面的图，你应该了解它的概念了。
         *  Multiset就是无序的，但是可以重复的集合，它就是游离在List/Set之间的“灰色地带”！
         */
        Multiset multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        System.out.println(multiset.size());
        System.out.println(multiset.count("a"));


        System.out.println("=======================5.Immutable=====================================");

        /**
         *  5.Immutable 、
         *  JDK提供的unmodifiable有缺陷，Collections.unmodifiableXxx所返回的集合和源集合是同一个对象，
         *  只不过可以对集合做出改变的API都被override，会抛出UnsupportedOperationException。也即是说我
         *  们改变源集合，导致不可变视图（unmodifiable View）也会发生变化，oh my god!
         *
         */
        List<String> immutable = ImmutableList.of("a", "b", "c");
        //immutable.add("f");   //java.lang.UnsupportedOperationException


        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        List<String> immutable2 = ImmutableList.copyOf(list2);

        //list2  改变了  immutable2不变
        list2.add("mmm");
        System.out.println("immutable2.size: " + immutable2.size() + "    *******    list2.size:" + list2.size());

        System.out.println("=======================6.Multimap 一对多=====================================");

        //guava所有的集合都有create方法
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("李白", "男");
        multimap.put("李白", "18");
        multimap.put("王昭君", "女");
        //multimap.size:  3
        System.out.println("multimap.size: " + multimap.size());
        System.out.println(multimap.get("李白"));

        System.out.println("=======================7.BiMap 双向=====================================");

        //比如通过用户ID找到mail，也需要通过mail找回用户名
        BiMap<String, String> biMap = HashBiMap.create();
        //KEY和VALUE都是唯一
        biMap.put("name", "libai");
        //biMap.put("nick","libai");  value重复会报错
        //强制覆盖
        biMap.forcePut("nick", "libai");

        biMap.put("007", "789456@qq.com");
        //biMap.inverse != biMap ；biMap.inverse.inverse == biMap
        System.out.println(biMap.inverse().get("789456@qq.com"));

        System.out.println("=======================8.Table 多个key=====================================");

        //多级关系查找也是比较多的，当然我们可以利用嵌套的Map来实现：Map<k1,Map<k2,v2>>。为了让我们的代码
        // 看起来不那么丑陋，guava为我们提供了Table
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("张飞", "数学", 80);
        table.put("张飞", "语文", 75);
        table.put("张飞", "英语", 75);
        table.put("关羽", "数学", 78);
        table.put("关羽", "语文", 88);
        table.put("关羽", "英语", 83);
        //最小单位cell Table涉及到3个概念：rowKey,columnKey,value
        Set<Table.Cell<String, String, Integer>> set = table.cellSet();
        for (Table.Cell cell : set) {
            System.out.println(cell.getRowKey() + " " + " " + cell.getColumnKey() + " " + cell.getValue());
        }


        System.out.println("=======================9.CacheLoader 强大的本地缓存=====================================");
        //如果我们的应用系统，并不想使用一些第三方缓存组件（如redis），我们仅仅想在本地有一个功能足够强大的缓存，
        // 很可惜JDK提供的那些SET/MAP还不行！

        Student student=useCacheData.apply(1L);
        System.out.println(student);
    }

}
