package com.example.webmagic.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpUtil {

    public static String downloadFile(String url, String fileType) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);

        String fileName = "image\\" + System.currentTimeMillis() + "." + fileType;
        File storeFile = new File(fileName);
        FileOutputStream output = new FileOutputStream(storeFile);

        //存在下载内容
        if(response.getEntity() != null){

            InputStream inputStream = response.getEntity().getContent();
            byte b[] = new byte[1024];
            int j;
            while( (j = inputStream.read(b))!=-1){
                output.write(b,0,j);
            }
            output.flush();
            output.close();
            httpClient.close();

            return fileName;
        }

        return "fail";
    }

    public static String getFileTypeByUrl(String url){
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        String fileType = fileName.split("\\.")[1];
        return fileType;
    }
}
