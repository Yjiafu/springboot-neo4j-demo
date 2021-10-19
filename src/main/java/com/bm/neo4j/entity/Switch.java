package com.bm.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Data
@NodeEntity(label = "switch")
public class Switch {
    @Id
    private Long id;
    @Property
    private String C1;
    @Property
    private String C2;
    @Property
    private String C3;
    @Property
    private String DOCUMENTNAME;
    @Property
    private String ID;
    @Property
    private String MODELALIAS;
    @Property
    private String OBJID;
    @Property
    private String OID;
    @Property
    private String SBID;
    @Property
    private String SBMC;
    @Property
    private String SBZLX;
    @Property
    private String SSTSL;
    @Property
    private String TXID;
    @Property
    private String YXBH;

    public Switch(String c1, String c2, String c3, String DOCUMENTNAME, String ID, String MODELALIAS, String OBJID, String OID, String SBID, String SBMC, String SBZLX, String SSTSL, String TXID, String YXBH) {
        C1 = c1;
        C2 = c2;
        C3 = c3;
        this.DOCUMENTNAME = DOCUMENTNAME;
        this.ID = ID;
        this.MODELALIAS = MODELALIAS;
        this.OBJID = OBJID;
        this.OID = OID;
        this.SBID = SBID;
        this.SBMC = SBMC;
        this.SBZLX = SBZLX;
        this.SSTSL = SSTSL;
        this.TXID = TXID;
        this.YXBH = YXBH;
    }

}


