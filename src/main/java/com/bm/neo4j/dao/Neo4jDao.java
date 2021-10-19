package com.bm.neo4j.dao;

import com.bm.neo4j.entity.Trans;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Neo4jDao extends Neo4jRepository<Trans,Long> {
    //@Query("match test=(a:switch{SSTSL:'87306'})-[*1..100]->(b:trans{SSTSL:'87306'}) return test")
    @Query("match test=(t:trans{TXID:'132944'})-[*1..100]-(s:trans{TXID:'275760'}) return nodes(test)")
    List<Map<String,List<Map<String,String>>>> getAll();

    @Query("match(s:switch{OBJID:'29DKX-36401'})-[*1..100]->(p:trans{OBJID:'29DKX-36401'}) where s.TXID = '93407' return p")
    Trans getTrans();

    @Query("match(s:switch{OBJID:'29DKX-36401'})-[*1..100]->(p:trans{OBJID:'29DKX-36401'}) where s.TXID = '93407' return p.SBID,p.SBMC")
    List<Map<String,String>> getTransInfo();
}
