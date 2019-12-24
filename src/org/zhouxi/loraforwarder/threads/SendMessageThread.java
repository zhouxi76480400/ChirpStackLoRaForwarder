package org.zhouxi.loraforwarder.threads;

import okhttp3.*;
import org.zhouxi.loraforwarder.pojo.EmergencyMessageObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SendMessageThread extends Thread {

    private EmergencyMessageObject emergencyMessageObject;

    public SendMessageThread(EmergencyMessageObject emergencyMessageObject) {
        super();
        this.emergencyMessageObject = emergencyMessageObject;
    }

    @Override
    public void run() {
        super.run();
        String address_base = "http://157.65.30.36/demo/";
        // 先加上面
        FormBody formBody = new FormBody.Builder()
                .addEncoded("name",emergencyMessageObject.n)
                .addEncoded("lat","0.0")
                .addEncoded("lng","0.0")
                .build();
        String location_api = address_base + "index.php";
        request(location_api,formBody);
        //再加下面
        FormBody formBody1 = new FormBody.Builder()
                .addEncoded("name",emergencyMessageObject.n)
                .addEncoded("message",emergencyMessageObject.c)
                .build();
        String message_api = address_base + "apiMessage.php";
        request(message_api,formBody1);
    }

    private void request(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
