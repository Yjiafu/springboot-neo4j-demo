package com.bm.neo4j.service.impl;

import com.bm.neo4j.dao.Neo4jDao;
import com.bm.neo4j.entity.*;
import com.bm.neo4j.service.Neo4jService;
import lombok.extern.slf4j.Slf4j;
import oracle.spatial.network.*;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class Neo4jServiceImpl implements Neo4jService {
    @Autowired
    private Neo4jDao neo4jDao;

    public Trans getTrans() {
        //获取一条线路
        List<Map<String, List<Map<String, String>>>> all = neo4jDao.getAll();
        Map<String, List<Map<String, String>>> map = all.get(0);

        List<Map<String, String>> list = map.get("nodes(test)");

        List<NodeImpl> nodeList = new LinkedList<>();
        List<LinkImpl> linkList = new LinkedList<>();
        //List<NodeImpl> nodeList=new ArrayList<>();
        //List<LinkImpl> linkList=new ArrayList<>();
        HashMap<Integer, Link> linkMap = new HashMap<>();
        HashMap<Integer, Node> nodeMap = new HashMap<>();
        int id;
        String name, type;
        //数据库信息转换为网络模型
        for (int i = 0; i < list.size(); i++) {

            NodeImpl node = new NodeImpl(i);

            if (list.get(i) instanceof Trans) {
                Trans trans = (Trans) list.get(i);
                id = Integer.parseInt(trans.getId().toString());
                name = trans.getSBMC();
                type = "trans";
            } else if (list.get(i) instanceof Switch) {
                Switch aSwitch = (Switch) list.get(i);
                id = Integer.parseInt(aSwitch.getId().toString());
                name = aSwitch.getSBMC();
                type = "switch";
            } else {
                Device device = (Device) list.get(i);
                id = Integer.parseInt(device.getId().toString());
                name = device.getSBMC();
                type = "device";
            }
            node.setID(id);
            node.setName(name);
            node.setType(type);
            if (i == 0 || i == 10)
                //电源点信息设置
                node.setUserData("originNodeNo", "");
            if (i == 8)
                //联络开关信息设置
                node.setUserData("normalOpen", "false");

            node.setCost(0);


            nodeList.add(node);
            nodeMap.put(i, node);
        }
        log.info("生成node");
        for (int j = 0; j < nodeList.size() - 1; j++) {
            LinkImpl link = new LinkImpl(-j, nodeList.get(j), nodeList.get(j + 1), 1);


            linkList.add(link);
            linkMap.put(-j, link);
        }
        log.info("生成link");

        try {
            Network network = NetworkManager.createLogicalNetwork(true, linkMap, nodeMap);
            //联络开关
            List<Node> connectBreakerList = nodeList.parallelStream()
                    .filter(e -> "false".equals(e.getUserData("normalOpen")))
                    .collect(Collectors.toList());
            //将所有联络开关设置为分
            connectBreakerList.parallelStream()
                    .forEach(e -> e.setState(false));

            Vector x = NetworkManager.findConnectedComponentVector(network);
            //检修开关
            nodeList.get(1).setState(false);

            nodeList.get(4).setState(false);

            //断开检修点的电气岛
            Vector y = NetworkManager.findConnectedComponentVector(network);
            //检修范围
            List m3 = ListUtils.subtract(y, x);

            //将所有联络开关设置为合
            connectBreakerList.parallelStream()
                    .forEach(e -> e.setState(true));

            Vector z = NetworkManager.findConnectedComponentVector(network);
            //转供区域
            List<HashSet<NodeImpl>> m4 = ListUtils.subtract(m3, z);
            Iterator<NodeImpl> iterator = m4.get(0).iterator();
            while (iterator.hasNext()){
                NodeImpl node = iterator.next();


            }

            //检修区域
            List union = ListUtils.subtract(m3, m4);

            Path path = NetworkManager.shortestPath(network, 1, 11);
            log.info("生成最短路径");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return neo4jDao.getTrans();
    }
}
