package com.yexuman.tool.schedule;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yexuman
 * @date 2019/9/19 16:33
 */
@Component
public class ScheduleTask {

    @Scheduled(fixedRate = 50000)
    @Async
    public void scheduleGet() {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        System.out.println("定时任务：" + System.currentTimeMillis());

        Thread thread=new Thread();
        thread.getState();
        HttpGet httpGet=new HttpGet("https://blog.csdn.net/qq_41864967/article/details/100737036");
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            System.out.println("发送请求！");
            System.out.println(response.getStatusLine());
            System.out.println(response.getAllHeaders());
            System.out.println(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
