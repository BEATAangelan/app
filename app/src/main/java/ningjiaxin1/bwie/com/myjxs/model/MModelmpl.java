package ningjiaxin1.bwie.com.myjxs.model;

import com.google.gson.Gson;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.utils.MCallBack;
import ningjiaxin1.bwie.com.myjxs.utils.MyCallBack;
import ningjiaxin1.bwie.com.myjxs.utils.RetrofitManager;

public class MModelmpl implements MModel {

    @Override
    public void ongetRequest(final String url, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().get(url,new RetrofitManager.HttListener() {
            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                Object o = gson.fromJson(data, clazz);
                callBack.setData(o);
            }

            @Override
            public void onFail(String error) {
                 callBack.setData(error);
            }
        });
    }

    @Override
    public void requestData(String url, Map<String, String> prams, final MyCallBack callBack, final Class clazz) {
        RetrofitManager.getInstance().post(url,prams,new RetrofitManager.HttListener() {
            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                Object o = gson.fromJson(data, clazz);
                callBack.setData(o);
            }

            @Override
            public void onFail(String error) {
                callBack.setData(error);
            }
        });
    }
}
