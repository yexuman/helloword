package com.yexuman.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yexuman
 * @date 2019/11/1 17:53
 */
@Data
public class Message implements Serializable {
    private Long id;
    private String msg;
    private Date sendTime;
}
