package ningjiaxin1.bwie.com.myjxs.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private static volatile OkHttpUtils instance;
    private OkHttpClient mClient;
    private Handler handler=new Handler(Looper.getMainLooper());
    //单例懒汉式
    public static OkHttpUtils getInstance(){
        if(instance==null){
            synchronized (OkHttpUtils.class){
                instance=new OkHttpUtils();
            }
        }
       return instance;
    }
    public OkHttpUtils(){
        //日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
    //接口
    public interface OkCallBack{
        void onSuccess(Object o);
        void failed(Exception e);
    }
    //使用get请求
    public void getRequest(final String url,final Class clazz,final OkCallBack okCallBack){
        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = new OkHttpClient().newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
    //使用post请求
    public void postRequest(final String url, final Map<String,String> map, final Class clazz, final OkCallBack okCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody build = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(build)
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                okCallBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okCallBack.onSuccess(o);
                    }
                });
            }
        });
    }
}
