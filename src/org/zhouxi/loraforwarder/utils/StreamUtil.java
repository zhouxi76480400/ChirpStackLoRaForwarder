package org.zhouxi.loraforwarder.utils;

import java.io.InputStream;

public class StreamUtil {

    public static String readStreamFromInputStream(InputStream inputStream) throws Exception{
        byte[] buffer = new byte[2048];
        int readBytes =0;
        StringBuilder stringBuilder = new StringBuilder();
        while((readBytes = inputStream.read(buffer)) > 0){
            stringBuilder.append(new String(buffer, 0, readBytes));
        }
        return stringBuilder.toString();
    }

}
