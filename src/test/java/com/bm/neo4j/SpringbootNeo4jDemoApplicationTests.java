package com.bm.neo4j;

import com.bm.neo4j.service.impl.Neo4jServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootNeo4jDemoApplicationTests {

    @Autowired
    private Neo4jServiceImpl neo4jService;

    @Test
    void contextLoads() {
    }

    @Test
    public void getT(){
        neo4jService.getTrans();
    }

}
