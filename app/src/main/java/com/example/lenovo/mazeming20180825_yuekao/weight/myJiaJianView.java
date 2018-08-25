package com.example.lenovo.mazeming20180825_yuekao.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mazeming20180825_yuekao.R;

public class myJiaJianView extends LinearLayout {

    private TextView jia;
    private TextView jian;
    private EditText numET;
    private int num= 1;

    public myJiaJianView(Context context) {
        this(context,null);
    }

    public myJiaJianView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public myJiaJianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_jiajian, this, true);
        //初始化控件
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        numET = view.findViewById(R.id.num);

        numET.setText(num+"");

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                numET.setText(num+"");

                if(jiajianLisnter!=null){
                    jiajianLisnter.getNum(num);
                }
            }
        });

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                if(num<=1){
                    num=1;
                    Toast.makeText(context,"商品数量不能小于1",Toast.LENGTH_LONG).show();
                }

                numET.setText(num+"");
                if(jiajianLisnter!=null){
                    jiajianLisnter.getNum(num);
                }
            }
        });


    }


    public void setNum(int n){
        numET.setText(n+"");

         num = Integer.parseInt(numET.getText().toString());

    }

    private jiajianLisnter jiajianLisnter;

    public void setJiajianLisnter(myJiaJianView.jiajianLisnter jiajianLisnter) {
        this.jiajianLisnter = jiajianLisnter;
    }

    public interface jiajianLisnter{
        void getNum(int num);
   }

}
