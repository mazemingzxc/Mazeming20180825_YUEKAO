package com.example.lenovo.mazeming20180825_yuekao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.mazeming20180825_yuekao.weight.Frawlayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Frawlayout frawlayout;
    private ViewGroup.MarginLayoutParams layoutParams;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frawlayout = findViewById(R.id.frawlayout);

        layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.leftMargin=40;
        layoutParams.rightMargin=40;

        list = new ArrayList<>();

        list.add("笔记本");
        list.add("空气净化器");
        list.add("安卓手机");
        list.add("超级大号圆珠笔");
        list.add("空气滤芯");
        list.add("超级大号钢笔");

        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(this);

            textView.setText(list.get(i));
            textView.setTextColor(getResources().getColor(R.color.colorAccent));
            textView.setLayoutParams(layoutParams);
            frawlayout.addchild(textView);
        }


    }

    public void sousuo(View view) {
    }


    //点击进入购物车
    public void getcarts(View view) {
        Intent intent = new Intent(MainActivity.this,ShowCartsActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.leftMargin=40;
        layoutParams.rightMargin=40;

        list = new ArrayList<>();



        for (int i = 0; i < list.size(); i++) {
            TextView textView = new TextView(this);

            textView.setText(list.get(i));
            textView.setTextColor(getResources().getColor(R.color.colorAccent));
            textView.setLayoutParams(layoutParams);
            frawlayout.addchild(textView);
        }


    }
}
