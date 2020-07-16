package com.example.recommend_service.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpHandler {

    static public String getUrlContent(String sUrl) throws IOException {

        StringBuffer document = new StringBuffer();
        URL url = new URL(sUrl);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = null;
        while((line = reader.readLine())!=null) {
            document.append(line);
        }
        reader.close();
        return document.toString();
    }

    static public String doPost(String sUrl, String content){
        String result = null;
        PrintWriter out = null;
        try{
            URL url = new URL(sUrl);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            //POST请求
            out.print(content);
            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes());
            sb.append(lines);
            }
            System.out.println(sb);
            connection.disconnect();
            result = sb.toString();
            } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }

            System.out.println("post result = " + result);
            return result;
        }

}
