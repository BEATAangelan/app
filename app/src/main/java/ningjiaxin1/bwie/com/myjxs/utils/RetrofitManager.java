package ningjiaxin1.bwie.com.myjxs.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager {
    private final String BASE_URL="http://172.17.8.100/small/";
    private static RetrofitManager mretrofitManager;
    public static synchronized RetrofitManager getInstance(){
        if(mretrofitManager==null){
            mretrofitManager=new RetrofitManager();
        }
        return mretrofitManager;
    }
    private BaseApis mbaseApis;
    private RetrofitManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15,TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        builder.writeTimeout(15,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        mbaseApis  = retrofit.create(BaseApis.class);
    }
//    public Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
//        Map<String, RequestBody> requestBodyMap = new HashMap<>();
//        for (String key : requestDataMap.keySet()) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
//                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
//            requestBodyMap.put(key, requestBody);
//        }
//        return requestBodyMap;
//    }
    //get请求
    public RetrofitManager get(String url,HttListener listener){
        mbaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return mretrofitManager;
    }
    //普通post请求
    public RetrofitManager  post(String url, Map<String,String> map,HttListener listener){
        if (map==null){
            map = new HashMap<>();
        }
        mbaseApis.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return mretrofitManager;
    }
    //返回数据
    private Observer getObserver(final HttListener listener) {
        Observer  observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.onFail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if (listener != null) {
                        listener.onSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onFail(e.getMessage());
                    }
                }
            }

        };
        return observer;

    }
    public interface HttListener{
        void onSuccess(String data);
        void onFail(String error);
    }
}
