package com.yexuman.dao;

import com.yexuman.po.Poem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yexuman
 * @date 2019/10/24 20:51
 */
public interface PoemRepository extends ElasticsearchRepository<Poem,Long> {
}
