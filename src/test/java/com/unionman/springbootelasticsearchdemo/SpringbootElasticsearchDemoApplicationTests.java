package com.unionman.springbootelasticsearchdemo;


import com.alibaba.fastjson.JSON;
import com.unionman.springbootelasticsearchdemo.entity.Vehicle;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

import java.util.List;

import static com.unionman.springbootelasticsearchdemo.repository.impl.VehicleRepositoryImpl.TIMESTAMP;

@SpringBootTest
@RunWith(SpringRunner.class)

public class SpringbootElasticsearchDemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void contextLoads() throws Exception {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 排序方式
        PageSortHighLight psh = new PageSortHighLight(0, 200);
        org.zxp.esclientrhl.repository.Sort.Order order = new org.zxp.esclientrhl.repository.Sort.Order(SortOrder.DESC, TIMESTAMP);
        psh.setSort(new org.zxp.esclientrhl.repository.Sort(order));
        PageList<Vehicle> search = elasticsearchTemplate.search(queryBuilder, psh, Vehicle.class);
        List<Vehicle> list = search.getList();
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);

    }

}
