package com.example.lenovo.mazeming20180825_yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.mazeming20180825_yuekao.R;
import com.example.lenovo.mazeming20180825_yuekao.bean.CartsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CartsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements CartsCheckBoxLisnter{

    private List<CartsBean.DataBean> list;
    private Context context;
    private AllCheckBoxLisnter allCheckBoxLisnter;

    public CartsAdapter(List<CartsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setAllCheckBoxLisnter(AllCheckBoxLisnter allCheckBoxLisnter) {
        this.allCheckBoxLisnter = allCheckBoxLisnter;
    }

    public List<CartsBean.DataBean> getCartList(){
        return list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.text_name.setText(list.get(position).getSellerName());

        final List<CartsBean.DataBean.ListBean> listBeans = list.get(position).getList();
        ProductAdapter productAdapter = new ProductAdapter(listBeans, context);
        productAdapter.setCartsCheckBoxLisnter(this);
        viewHolder.recy_one.setAdapter(productAdapter);
        viewHolder.recy_one.setLayoutManager(new LinearLayoutManager(context));

        viewHolder.checkbox.setChecked(list.get(position).isSelected());

        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.checkbox.isChecked()){
                    list.get(position).setSelected(true);
                    for (int i = 0; i < listBeans.size(); i++) {
                        listBeans.get(i).setSelected(true);
                    }
                }else {
                    list.get(position).setSelected(false);
                    for (int i = 0; i < listBeans.size(); i++) {
                        listBeans.get(i).setSelected(false);
                    }
                }
             notifyDataSetChanged();
               if(allCheckBoxLisnter!=null){
                   allCheckBoxLisnter.notifyAllckeckLisnter();
               }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void notifParent() {
        notifyDataSetChanged();
        if(allCheckBoxLisnter!=null){
            allCheckBoxLisnter.notifyAllckeckLisnter();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox;
        private TextView text_name;
        private XRecyclerView recy_one;

        public ViewHolder(View itemView) {
            super(itemView);

            checkbox=itemView.findViewById(R.id.checkbox);
            text_name=itemView.findViewById(R.id.text_name);
            recy_one=itemView.findViewById(R.id.recy_one);



        }
    }

}
