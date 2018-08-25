package com.example.lenovo.mazeming20180825_yuekao.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface RequestCallBack {

  void onsuccess(Call call, Response response);

  void error(Call call, IOException e);

}
