package com.example.webmagic.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpUtil {

    /**
     * Get Request
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String realUrl = url + "?" + param;

        HttpGet httpGet = new HttpGet(realUrl);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * Post Request
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(stringEntity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 下载文件
     * @param url
     * @param fileType
     * @return
     * @throws IOException
     */
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

    /**
     * 根据图片URL找出图片类型
     * @param url
     * @return
     */
    public static String getFileTypeByUrl(String url){
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        String fileType = fileName.split("\\.")[1];
        return fileType;
    }
}
