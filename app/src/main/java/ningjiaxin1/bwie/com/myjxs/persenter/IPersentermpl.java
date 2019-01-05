package ningjiaxin1.bwie.com.myjxs.persenter;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.model.IModelmpl;
import ningjiaxin1.bwie.com.myjxs.utils.MCallBack;
import ningjiaxin1.bwie.com.myjxs.view.IView;

public class IPersentermpl implements IPersenter{
    IModelmpl modelmpl;
    IView mview;
    public IPersentermpl(IView iView){
        modelmpl=new IModelmpl();
        mview=iView;
    }
    @Override
    public void startgetRequest(String url, Class clazz) {
        modelmpl.ongetRequest(url, clazz, new MCallBack() {

            @Override
            public void getdata(Object o) {
               mview.setonSuccess(o);
            }

            @Override
            public void getfailed(Exception e) {
              mview.onfailed(e);
            }
        });
    }

    @Override
    public void startpostRequest(String url, Map<String, String> map, Class clazz) {
        modelmpl.onpostRequest(url, map, clazz, new MCallBack() {
            @Override
            public void getdata(Object o) {
                mview.setonSuccess(o);
            }

            @Override
            public void getfailed(Exception e) {
               mview.onfailed(e);
            }
        });
    }
   public void destroy(){
        if(modelmpl!=null){
            modelmpl=null;
            }
            if(mview!=null){
            mview=null;
            }
   }
}
