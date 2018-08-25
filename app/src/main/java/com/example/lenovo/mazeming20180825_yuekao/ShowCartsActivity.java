package com.example.lenovo.mazeming20180825_yuekao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.mazeming20180825_yuekao.adapter.AllCheckBoxLisnter;
import com.example.lenovo.mazeming20180825_yuekao.adapter.CartsAdapter;
import com.example.lenovo.mazeming20180825_yuekao.bean.CartsBean;
import com.example.lenovo.mazeming20180825_yuekao.common.API;
import com.example.lenovo.mazeming20180825_yuekao.presenter.CartsPresenter;
import com.example.lenovo.mazeming20180825_yuekao.view.ICartsView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

public class ShowCartsActivity extends AppCompatActivity implements ICartsView ,AllCheckBoxLisnter {

    private CheckBox allcheckbox;
    private XRecyclerView recy;
    private TextView price_Z;
    private CartsPresenter presenter;
    private CartsAdapter adapter;
    private double tolprice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_carts);

        initView();

        initData();

    }


   //初始化控件
    private void initView() {
        allcheckbox = findViewById(R.id.allcheckbox);
        recy = findViewById(R.id.recy);
        price_Z = findViewById(R.id.price_Z);





    }
    //初始化数据
    private void initData() {

        presenter = new CartsPresenter(this);

        HashMap<String,String> parms = new HashMap<>();
        parms.put("uid","91");

        presenter.getCatrs(API.GETCARTS_URL,parms);



    }

    @Override
    public void success(final CartsBean bean) {

        Toast.makeText(ShowCartsActivity.this,bean+"",Toast.LENGTH_LONG).show();

        adapter = new CartsAdapter(bean.getData(), ShowCartsActivity.this);

        recy.setLayoutManager(new LinearLayoutManager(ShowCartsActivity.this));
        recy.setAdapter(adapter);

        adapter.setAllCheckBoxLisnter(this);
        //全选反选
        allcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allcheckbox.isChecked()){

                    for (int i = 0; i < bean.getData().size(); i++) {
                        bean.getData().get(i).setSelected(true);
                        for (int i1 = 0; i1 < bean.getData().get(i).getList().size(); i1++) {
                            bean.getData().get(i).getList().get(i1).setSelected(true);
                        }
                    }
                }else {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        bean.getData().get(i).setSelected(false);
                        for (int i1 = 0; i1 < bean.getData().get(i).getList().size(); i1++) {
                            bean.getData().get(i).getList().get(i1).setSelected(false);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public void error(String msg) {

    }

    //取消一个 取消全选
    @Override
    public void notifyAllckeckLisnter() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < adapter.getCartList().size(); i++) {
            stringBuilder.append(adapter.getCartList().get(i).isSelected());
            for (int i1 = 0; i1 < adapter.getCartList().get(i).getList().size(); i1++) {
                stringBuilder.append(adapter.getCartList().get(i).getList().get(i1).isSelected());
            }
        }

        if(stringBuilder.toString().contains("false")){
            allcheckbox.setChecked(false);
        }else {
            allcheckbox.setChecked(true);
        }

        //计算总价
        getTotprice();

    }

    private void getTotprice() {

       double tolprice =0;

        for (int i = 0; i < adapter.getCartList().size(); i++) {

            for (int i1 = 0; i1 < adapter.getCartList().get(i).getList().size(); i1++) {

                if(adapter.getCartList().get(i).getList().get(i1).isSelected()){
                    CartsBean.DataBean.ListBean listBean = adapter.getCartList().get(i).getList().get(i1);
                    tolprice+= listBean.getBargainPrice()*listBean.getTotNum();
                }

            }
            
        }

        price_Z.setText("总价：￥"+tolprice);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dedachView();
    }
}
