package ningjiaxin1.bwie.com.myjxs.utils;

public interface MCallBack <T>{
    void getdata(T o);
    void getfailed(Exception e);
}
