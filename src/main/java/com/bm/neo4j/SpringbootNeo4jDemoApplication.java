package com.bm.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "com.bm.neo4j.dao")
@EntityScan(basePackages = "com.bm.neo4j.entity")
public class SpringbootNeo4jDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNeo4jDemoApplication.class, args);
    }

}
