package com.yexuman.tool.lombok;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author yexuman
 * @date 2019/12/13 15:07
 */
@AllArgsConstructor
public abstract class FilterRestTemplate implements RestOperations {
    /**
     *     代理模式 自动重写restTemplate所有方法
     */
    @Delegate
    protected volatile RestTemplate restTemplate;
}
