package ningjiaxin1.bwie.com.myjxs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
    public static boolean isGG(Context context){
        boolean flag=false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            flag= connectivityManager.getActiveNetworkInfo().isAvailable();
        }
        return flag;
    }
}
