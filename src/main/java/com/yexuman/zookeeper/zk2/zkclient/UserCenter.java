package com.yexuman.zookeeper.zk2.zkclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Accessors(chain = true)   //链式set
@Data //生成Getter,Setter,equals,canEqual,hasCode,toString等方法
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder(toBuilder = true)
public class UserCenter implements Serializable{

    private static final long serialVersionUID = -1776114173857775665L;
    /**
     *     机器信息
     */
    private int mc_id;
    /**
     * 机器名称
     */
    private String mc_name;


}
