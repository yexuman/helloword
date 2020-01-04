package com.yexuman.tool.lombok;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author yexuman
 * @date 2019/12/12 11:03
 */
@Accessors(chain = true)   //链式set
@Data //生成Getter,Setter,equals,canEqual,hasCode,toString等方法
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder(toBuilder = true)         //Builder的作用之一是为了解决在某个类有很多构造函数的情况，也省去写很多构造函数  修改实体，要求实体上添加@Builder(toBuilder=true)
@RequiredArgsConstructor(staticName = "of")
public class Student {
    /**
     * 告诉编译器，参数非空 和RequiredArgsConstructor配合生成静态构造方法
     */
    private Long id;
    @NonNull
    private String name;
    private Integer age;
    private String sex;
}
