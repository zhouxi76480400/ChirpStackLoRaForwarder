package org.zhouxi.loraforwarder.pojo;

import java.io.Serializable;

public class ChirpStackRXInfoObject implements Serializable {

    public String gatewayID;

    public String uplinkID;

    public String name;

    public int rssi;

    public float loRaSNR;

    public ChirpStackLocationObject location;

}
