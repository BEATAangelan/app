package ningjiaxin1.bwie.com.myjxs.persenter;

import java.util.Map;

import ningjiaxin1.bwie.com.myjxs.model.IModelmpl;
import ningjiaxin1.bwie.com.myjxs.model.MModelmpl;
import ningjiaxin1.bwie.com.myjxs.utils.MyCallBack;
import ningjiaxin1.bwie.com.myjxs.view.IView;
import ningjiaxin1.bwie.com.myjxs.view.MView;

public class MPersentermpl implements MPersenter{
    MModelmpl modelmpl;
    MView mview;
    public MPersentermpl(MView iView){
        modelmpl=new MModelmpl();
        mview=iView;
    }
    @Override
    public void startgetRequest(String url, Class clazz) {
        modelmpl.ongetRequest(url, clazz, new MyCallBack() {
                            @Override
                            public void setData(Object data) {
                                mview.success(data);
                            }
                        });
    }

    @Override
    public void startpostRequest(String url, Map<String, String> map, Class clazz) {
       modelmpl.requestData(url, map, new MyCallBack() {
           @Override
           public void setData(Object data) {
               mview.success(data);
           }
       },clazz);
    }
    public void destroyRe(){
        if(modelmpl!=null){
            modelmpl=null;
        }
        if(mview!=null){
            mview=null;
        }
    }
}
