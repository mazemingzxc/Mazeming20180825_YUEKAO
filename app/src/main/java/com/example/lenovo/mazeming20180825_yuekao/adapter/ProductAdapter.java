package com.example.lenovo.mazeming20180825_yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.mazeming20180825_yuekao.R;
import com.example.lenovo.mazeming20180825_yuekao.bean.CartsBean;
import com.example.lenovo.mazeming20180825_yuekao.weight.myJiaJianView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CartsBean.DataBean.ListBean> list;
    private Context context;
    private AllCheckBoxLisnter allCheckBoxLisnter;
    private CartsCheckBoxLisnter cartsCheckBoxLisnter;

    public void setAllCheckBoxLisnter(AllCheckBoxLisnter allCheckBoxLisnter) {
        this.allCheckBoxLisnter = allCheckBoxLisnter;
    }

    public void setCartsCheckBoxLisnter(CartsCheckBoxLisnter cartsCheckBoxLisnter) {
        this.cartsCheckBoxLisnter = cartsCheckBoxLisnter;
    }

    public ProductAdapter(List<CartsBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_item_child, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.text_price.setText("优惠价：￥"+list.get(position).getBargainPrice());

        viewHolder.text_title.setText(list.get(position).getTitle());

        String images = list.get(position).getImages();
        String[] imgs = images.split("\\|");

        Glide.with(context).load(imgs[0]).into(viewHolder.img_icon);

        viewHolder.checkbox_two.setChecked(list.get(position).isSelected());

        viewHolder.jiajianView.setNum(list.get(position).getTotNum());
        viewHolder.jiajianView.setJiajianLisnter(new myJiaJianView.jiajianLisnter() {
            @Override
            public void getNum(int num) {
             list.get(position).setTotNum(num);
             if(cartsCheckBoxLisnter!=null){
                 cartsCheckBoxLisnter.notifParent();
             }
            }
        });


        viewHolder.checkbox_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.checkbox_two.isChecked()){
                    list.get(position).setSelected(true);
                }else {
                    list.get(position).setSelected(false);
                }
                if(cartsCheckBoxLisnter!=null){
                    cartsCheckBoxLisnter.notifParent();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox_two;
        private TextView text_price,text_title;
        private ImageView img_icon;
        private myJiaJianView jiajianView;

        public ViewHolder(View itemView) {
            super(itemView);

            checkbox_two=itemView.findViewById(R.id.checkbox_two);
            text_price=itemView.findViewById(R.id.text_price);
            text_title=itemView.findViewById(R.id.text_title);
            img_icon=itemView.findViewById(R.id.img_icon);
            jiajianView=itemView.findViewById(R.id.jiajianView);




        }
    }

}
