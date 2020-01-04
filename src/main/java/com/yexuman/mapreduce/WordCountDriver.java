package com.yexuman.mapreduce;

/**
 * @author yexuman
 * @date 2019/11/22 15:03
 */

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

    public static class WordCountMapper  extends Mapper<Object, Text, Text, IntWritable> {


        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            //拿到一行数据转换为string
            String line = value.toString();
            //将这一行切分出各个单词
            String[] words = line.split(" ");
            //遍历数组，输出<单词，1>
            for(String word:words){
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    public static class WordCountRunner extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values,Context context ) throws IOException, InterruptedException {
            //定义一个计数器
            int count = 0;
            //遍历这一组kv的所有v，累加到count中
            for(IntWritable value:values){
                count += value.get();
            }
            context.write(key, new IntWritable(count));

        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job wcjob = Job.getInstance(conf);
        //指定我这个job所在的jar包
        wcjob.setJarByClass(WordCountRunner.class);

        wcjob.setMapperClass(WordCountMapper.class);
        wcjob.setReducerClass(WordCountRunner.class);
        //设置我们的业务逻辑Mapper类的输出key和value的数据类型
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(IntWritable.class);
        //设置我们的业务逻辑Reducer类的输出key和value的数据类型
        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(IntWritable.class);

        //指定要处理的数据所在的位置
        FileInputFormat.setInputPaths(wcjob, "hdfs://192.168.218.131:9000/wordcount/input/big.txt");
        //指定处理完成之后的结果所保存的位置
        FileOutputFormat.setOutputPath(wcjob, new Path("hdfs://192.168.218.131:9000/wordcount/output/o1"));

        //向yarn集群提交这个job
        boolean res = wcjob.waitForCompletion(true);
        System.exit(res?0:1);
    }
}