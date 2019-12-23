package org.zhouxi.loraforwarder.servlets;

import com.google.gson.Gson;
import org.zhouxi.loraforwarder.pojo.ChirpStackUPLinkObject;
import org.zhouxi.loraforwarder.utils.BytesUtil;
import org.zhouxi.loraforwarder.utils.StreamUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class LoRaPackageReceiveServlet extends MyServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("被請求");
        String in_str = null;
        try {
            in_str = StreamUtil.readStreamFromInputStream(req.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (in_str != null) {
            // 嘗試來解一下JSON
            ChirpStackUPLinkObject chirpStackUPLinkObject = null;
            try {
                Gson gson = new Gson();
                chirpStackUPLinkObject = gson.fromJson(in_str, ChirpStackUPLinkObject.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (chirpStackUPLinkObject != null) {
                // 他是一個正常的OBJECT
                if (chirpStackUPLinkObject.applicationID.equals("1")) {
                    // 是這個項目
                    String data_with_base64 = chirpStackUPLinkObject.data;
                    byte[] raw_lora_data = Base64.getDecoder().decode(data_with_base64.getBytes());
                    if (raw_lora_data.length > 4) {
                        // 必須大於4位
                        byte raw_byte_first = raw_lora_data[0];
                        byte raw_byte_second = raw_lora_data[1];
                        byte[] raw_byte_last_two = {raw_lora_data[raw_lora_data.length - 2],
                                raw_lora_data[raw_lora_data.length - 1]};
                        //算出這條信息的校驗碼
                        String checkSum = BytesUtil.bytesToHexString(raw_byte_last_two).toUpperCase();
                        int now_msg_count = raw_byte_first;
                        int all_msg_count = raw_byte_second;
                        System.out.println("checkSUM>"+checkSum+"now:"+now_msg_count+"all:"+all_msg_count);



                        System.out.println(raw_byte_first);
                        System.out.println(raw_byte_second);
//
//                        System.out.print(raw_byte_last_two[0]);
//                        System.out.println(raw_byte_last_two[1]);


                    }
                }
            }
        }


    }

}
