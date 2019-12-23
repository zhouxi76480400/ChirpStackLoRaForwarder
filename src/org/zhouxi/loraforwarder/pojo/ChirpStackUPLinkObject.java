package org.zhouxi.loraforwarder.pojo;

import java.io.Serializable;
import java.util.List;

public class ChirpStackUPLinkObject implements Serializable {

    public String applicationID;

    public String applicationName;

    public String deviceName;

    public String devEUI;

    public List<ChirpStackRXInfoObject> rxInfo;

    public ChirpStackTXInfoObject txInfo;

    public boolean adr;

    public int fCnt;

    public int fPort;

    public String data;

}