package com.example.lenovo.mazeming20180825_yuekao.presenter;

import com.example.lenovo.mazeming20180825_yuekao.bean.CartsBean;
import com.example.lenovo.mazeming20180825_yuekao.model.CartsModel;
import com.example.lenovo.mazeming20180825_yuekao.view.ICartsView;

import java.util.HashMap;

public class CartsPresenter {

    private ICartsView iCartsView;
    private CartsModel cartsModel;

    public CartsPresenter(ICartsView iCartsView ) {
        attchView(iCartsView);
         cartsModel = new CartsModel();
    }

    //绑定view
    private void attchView(ICartsView iCartsView) {
        this.iCartsView = iCartsView;
    }

    public void getCatrs(String Url, HashMap<String,String> parms) {

        cartsModel.getCarts(Url, parms, new CartsModel.CartsBack() {
            @Override
            public void success(CartsBean bean) {
                if(iCartsView!=null){
                    iCartsView.success(bean);
                }

            }

            @Override
            public void fail(String msg) {
                if(iCartsView!=null){
                    iCartsView.error(msg);
                }

            }
        }) ;

    }

    public void  dedachView(){
        this.iCartsView=null;
    }
}
