package com.bm.neo4j.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder(toBuilder = true)
public class DeviceInfo implements Serializable {
    private String assetsId;
    private String displayName;
    private String feederId;
    private String feederName;
    private String graphId;
    private String id;
    private String name;
    private boolean normalOpen;
    private String psrType;
    private String psrTypeId;
    private String runCode;
    private String stationId;
    private String stationName;
    private String subStationId;
    private String subStationName;
    private String useType;
}


