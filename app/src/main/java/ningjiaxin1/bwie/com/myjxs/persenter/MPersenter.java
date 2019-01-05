package ningjiaxin1.bwie.com.myjxs.persenter;

import java.util.Map;

public interface MPersenter {
    //get请求
    void startgetRequest(String url,Class clazz);
    //post请求
    void startpostRequest(String url, Map<String,String> map, Class clazz);
}
