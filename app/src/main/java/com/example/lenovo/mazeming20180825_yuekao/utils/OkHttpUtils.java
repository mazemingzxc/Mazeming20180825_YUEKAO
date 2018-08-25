package com.example.lenovo.mazeming20180825_yuekao.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

   private static OkHttpUtils okHttpUtils;
   private OkHttpClient okHttpClient;

   private OkHttpUtils(){

       //log日志打印
       HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
       loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(3000,TimeUnit.MICROSECONDS)
               .addInterceptor(loggingInterceptor)
                .build();
    }

    public static OkHttpUtils getinstance(){

       if(okHttpUtils==null){
           synchronized (OkHttpUtils.class){
               if(okHttpUtils==null){
                   okHttpUtils = new OkHttpUtils();
               }
           }
       }

        return okHttpUtils;

    }

    //get方式封装
    public void  getData(String url, HashMap<String,String> parms, final RequestCallBack requestCallBack){

         StringBuilder urlsb = new StringBuilder();

         String allurl = "";

         for (Map.Entry<String,String> stringStringEntry:parms.entrySet() ){
             urlsb.append("?").append(stringStringEntry.getKey()).append("=")
                     .append(stringStringEntry.getValue()).append("&");
         }

        String s = urlsb.toString().substring(0, urlsb.length() - 1);
         allurl=url+s;

        Request request = new Request.Builder()
                .url(allurl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(requestCallBack!=null){
                    requestCallBack.error(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if(requestCallBack!=null){
                  requestCallBack.onsuccess(call,response);
              }
            }
        });




    }


    //post请求方式
    public void postData(String url, HashMap<String,String> parms , final RequestCallBack requestCallBack){

        FormBody.Builder formbody = new FormBody.Builder();

        if(parms!=null && parms.size()>0){

            for (Map.Entry<String,String> stringStringEntry:parms.entrySet() ){
                formbody.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }

        }
        Request request = new Request.Builder()
                .post(formbody.build())
                .url(url)
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(requestCallBack!=null){
                    requestCallBack.error(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             if(requestCallBack!=null){
                 requestCallBack.onsuccess(call,response);
             }
            }
        });

    }

}
