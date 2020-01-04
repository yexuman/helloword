package com.yexuman.tool.ArrayListTest;

import com.google.common.collect.Lists;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Measurement 度量，其实就是一些基本的测试参数。iterations进行测试的轮次，time每轮进行的时长，timeUnit时长单位。都是一些基本的参数，可以根据具体情况调整。一般比较重的东西可以进行大量的测试，放到服务器上运行。
 * 方法注解总是优先于类的注解
 */
//参数iterations就是预热轮数
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)  //预热
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)  //测试
public class WriteTest {

    public static final int TEN_MILLION = 10000000;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testArrayList() {
        //指定初始化大小
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < TEN_MILLION; i++) {
            list.add("111");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testArrayListSize() {
        //指定初始化大小
        List<String> list = new ArrayList<>(TEN_MILLION);
        for (int i = 0; i < TEN_MILLION; i++) {
            list.add("111");
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testLinkedList() {
        //指定初始化大小
        List<String> list = new LinkedList<>();
        for (int i = 0; i < TEN_MILLION; i++) {
            list.add("111");
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(WriteTest.class.getSimpleName())
                .forks(1).build();
        new Runner(opt).run();
    }
}
