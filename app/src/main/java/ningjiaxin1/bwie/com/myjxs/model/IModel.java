package ningjiaxin1.bwie.com.myjxs.model;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.utils.MCallBack;
import ningjiaxin1.bwie.com.myjxs.utils.MyCallBack;

public interface IModel {
    //okhttpget请求
    void ongetRequest(String url, Class clazz, MCallBack callBack);
    //okhttppost请求
    void onpostRequest(String url, Map<String,String> map,Class clzz,MCallBack callBack);

}
