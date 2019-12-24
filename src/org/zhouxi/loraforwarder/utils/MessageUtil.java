package org.zhouxi.loraforwarder.utils;

import org.zhouxi.loraforwarder.pojo.MessageObject;
import org.zhouxi.loraforwarder.pojo.MessageRawObject;

import java.util.HashMap;

public class MessageUtil {

    private static MessageUtil instance;

    private HashMap<String, MessageObject> hashMap;

    public static MessageUtil getInstance() {
        if(instance == null) {
            instance = new MessageUtil();
        }
        return instance;
    }

    private MessageUtil() {
        super();
        hashMap = new HashMap<>();
    }

    public synchronized boolean addAMessage(int now, int all, byte[] msg, String checksum) {
        MessageObject messageObject = hashMap.get(checksum);
        if(messageObject != null) {
            if(messageObject.messageCheckSum.equals(checksum)) {
                messageObject.msgList[now - 1] = new MessageRawObject(msg);
            }
        } else {
            messageObject = new MessageObject(all,checksum);
            messageObject.msgList[now - 1] = new MessageRawObject(msg);
            hashMap.put(checksum, messageObject);
        }
        return messageObject.isFullMessage();
    }

    public synchronized String getMessage(String checkSUM) {
        String str = null;
        MessageObject messageObject = hashMap.get(checkSUM);
        if(messageObject != null && messageObject.isFullMessage()) {
               str = messageObject.getMessage();
               hashMap.remove(checkSUM);
        }
        return str;
    }



}
