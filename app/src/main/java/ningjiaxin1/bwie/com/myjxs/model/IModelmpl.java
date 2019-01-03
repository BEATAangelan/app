package ningjiaxin1.bwie.com.myjxs.model;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.utils.MCallBack;
import ningjiaxin1.bwie.com.myjxs.utils.OkHttpUtils;

public class IModelmpl implements IModel {
    MCallBack mCallBack;
    @Override
    public void ongetRequest(String url, Class clazz, MCallBack callBack) {
        mCallBack=callBack;
        OkHttpUtils.getInstance().getRequest(url, clazz, new OkHttpUtils.OkCallBack() {
            @Override
            public void onSuccess(Object o) {
                mCallBack.getdata(o);
            }

            @Override
            public void failed(Exception e) {
                mCallBack.getdata(e);
            }
        });
    }

    @Override
    public void onpostRequest(String url, Map<String, String> map, Class clzz, MCallBack callBack) {
       mCallBack=callBack;
       OkHttpUtils.getInstance().postRequest(url, map, clzz, new OkHttpUtils.OkCallBack() {
           @Override
           public void onSuccess(Object o) {
               mCallBack.getdata(o);
           }

           @Override
           public void failed(Exception e) {
               mCallBack.getdata(e);
           }
       });
    }
}
