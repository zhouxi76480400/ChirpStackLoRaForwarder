package org.zhouxi.loraforwarder.pojo;


import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MessageObject implements Serializable {

    public int messageCount;

    public MessageRawObject[] msgList;

    public String messageCheckSum;

    public MessageObject(int messageCount, String messageCheckSum) {
        super();
        System.out.println(String.format(
                "MessageObject:New: Count:"+messageCount+", CheckSum:"+messageCheckSum));
        this.messageCount = messageCount;
        this.messageCheckSum = messageCheckSum;
        msgList = new MessageRawObject[messageCount];
    }

    public boolean isFullMessage() {
        boolean isFull = true;
        for(int i = 0 ; i < messageCount ; i++) {
            MessageRawObject messageRawObject = msgList[i];
            if(messageRawObject == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public String getMessage() {
        String all_message = null;
        if(isFullMessage()) {
            int full_bytes_count = 0;
            for(int i = 0 ; i < msgList.length ; i ++) {
                MessageRawObject msg_part = msgList[i];
                full_bytes_count += msg_part.bytes.length;
            }
            byte[] all_bytes = new byte[full_bytes_count];
            int write_start_position = 0;
            for (int i = 0 ; i < msgList.length ; i ++) {
                MessageRawObject msg_part = msgList[i];
                byte[] msg_bytes = msg_part.bytes;
                int length = msg_bytes.length;
                System.arraycopy(msg_bytes, 0, all_bytes, write_start_position, length);
                write_start_position += length;
            }
            all_message = new String(all_bytes,0,all_bytes.length, StandardCharsets.UTF_8);
        }
        return all_message;
    }
}
