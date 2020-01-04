package com.yexuman.controller;

import com.yexuman.dao.PoemRepository;
import com.yexuman.po.Poem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author yexuman
 * @date 2019/10/24 20:45
 */
@RestController
public class EsController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private PoemRepository poemRepository;

    /**
     * 创建索引
     */
    @PostMapping("/poem/index")
    public void createIndex() {
        elasticsearchTemplate.createIndex(Poem.class);
    }

    /**
     * 删除索引
     */
    @DeleteMapping("/poem/index")
    public void delIndex() {
        elasticsearchTemplate.deleteIndex(Poem.class);
    }

    /**
     * 写数据
     */
    @PostMapping("/poem")
    public void insert(@RequestBody Poem poem) {
        poemRepository.save(poem);

    }


}
