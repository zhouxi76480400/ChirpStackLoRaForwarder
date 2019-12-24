package org.zhouxi.loraforwarder.pojo;

import java.io.Serializable;

public class MessageRawObject implements Serializable {

    public byte[] bytes;

    public MessageRawObject(byte[] msg) {
        super();
        bytes = msg;
    }

}
