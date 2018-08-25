package com.example.lenovo.mazeming20180825_yuekao.model;

import android.os.Handler;
import android.os.Message;

import com.example.lenovo.mazeming20180825_yuekao.bean.CartsBean;
import com.example.lenovo.mazeming20180825_yuekao.utils.OkHttpUtils;
import com.example.lenovo.mazeming20180825_yuekao.utils.RequestCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class CartsModel {

 private Handler handler = new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
     }
 };


    public void getCarts(String url, HashMap<String,String> parms, final CartsBack cartsBack) {

        OkHttpUtils.getinstance().postData(url, parms, new RequestCallBack() {
            @Override
            public void onsuccess(Call call, Response response) {
               if(200==response.code()){

                   try {
                       String request = response.body().string();
                       ShowGsonData(request,cartsBack);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }
            }

            @Override
            public void error(Call call, IOException e) {
                if(cartsBack!=null){
                    cartsBack.fail("网络请求失败");
                }
            }
        });

    }

    private void ShowGsonData(String request, final CartsBack cartsBack) {

        final CartsBean bean = new Gson().fromJson(request, CartsBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
              cartsBack.success(bean);
            }
        });

    }


    public interface CartsBack{
        void success(CartsBean bean);
        void fail(String msg);
    }

}
