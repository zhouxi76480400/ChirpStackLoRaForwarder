package org.zhouxi.loraforwarder.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class LoRaPackageReceiveServlet extends MyServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("doPost:test");
        String in_str = null;
        try {
            in_str = readInputStream(req.getInputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(in_str != null) {
            System.out.println("test:"+in_str);
        }
    }


    private String readInputStream(InputStream inputStream) throws Exception{
        byte[] buffer = new byte[2048];
        int readBytes = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while((readBytes = inputStream.read(buffer)) > 0){
            stringBuilder.append(new String(buffer, 0, readBytes));
        }
        return stringBuilder.toString();
    }


}
