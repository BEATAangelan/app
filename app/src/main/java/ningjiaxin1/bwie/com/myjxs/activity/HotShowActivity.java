
package ningjiaxin1.bwie.com.myjxs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.adapter.ClassifyAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.HotAdapter;
import ningjiaxin1.bwie.com.myjxs.bean.Hot;
import ningjiaxin1.bwie.com.myjxs.bean.Shop;
import ningjiaxin1.bwie.com.myjxs.model.MModel;
import ningjiaxin1.bwie.com.myjxs.persenter.MPersentermpl;
import ningjiaxin1.bwie.com.myjxs.view.MView;

public class HotShowActivity extends AppCompatActivity implements MView {
    private XRecyclerView hot_recycler;
    private ClassifyAdapter classifyAdapter;
    MPersentermpl mPersentermpl;
    private int Page=1;
    String path="commodity/v1/findCommodityListByLabel?labelId=%d&page=%d&count=%d";
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_show);
        hot_recycler = findViewById(R.id.hot_recycler);
        Page=1;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        classifyAdapter=new ClassifyAdapter(this);
        hot_recycler.setAdapter(classifyAdapter);
        hot_recycler.setLayoutManager(layoutManager);
        hot_recycler.setPullRefreshEnabled(true);
        hot_recycler.setLoadingMoreEnabled(true);
        hot_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Page=1;
                init();
            }
            @Override
            public void onLoadMore() {
                init();
            }
        });
        init();
    }
    private void init(){
        mPersentermpl = new MPersentermpl(this);
        mPersentermpl.startgetRequest(String.format(path,id,Page,5),Hot.class);

    }
    @Override
    public void success(Object data) {
        Hot bean= (Hot) data;
        List<Hot.ResultBean> result = bean.getResult();
        if (Page==1){
            classifyAdapter.setList(result);
        }
        else {
            classifyAdapter.addList(result);
        }
        Page++;
        hot_recycler.loadMoreComplete();
        hot_recycler.refreshComplete();
    }
}
