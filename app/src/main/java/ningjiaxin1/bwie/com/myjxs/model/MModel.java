package ningjiaxin1.bwie.com.myjxs.model;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.utils.MyCallBack;

public interface MModel {
    //retrofitget请求
    void ongetRequest(String url, Class clazz, MyCallBack callBack);
    //retrofitpost请求
    void requestData(String url, Map<String,String> prams, MyCallBack callBack, Class clazz);
}
