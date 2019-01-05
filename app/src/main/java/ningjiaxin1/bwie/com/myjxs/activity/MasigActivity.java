package ningjiaxin1.bwie.com.myjxs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.adapter.ClassifyAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.MagicxAdapter;
import ningjiaxin1.bwie.com.myjxs.bean.Hot;
import ningjiaxin1.bwie.com.myjxs.bean.Magic;
import ningjiaxin1.bwie.com.myjxs.persenter.MPersentermpl;
import ningjiaxin1.bwie.com.myjxs.view.IView;
import ningjiaxin1.bwie.com.myjxs.view.MView;

public class MasigActivity extends AppCompatActivity implements MView {
    private XRecyclerView ma_recycler;
    private MagicxAdapter magicxAdapter;
    MPersentermpl mPersentermpl;
    private int Page=1;
    String path="commodity/v1/findCommodityListByLabel?labelId=%d&page=%d&count=%d";
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masig);
        ma_recycler = findViewById(R.id.ma_recycler);
        Page=1;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        magicxAdapter=new MagicxAdapter(this);
        ma_recycler.setAdapter(magicxAdapter);
        ma_recycler.setLayoutManager(layoutManager);
        ma_recycler.setPullRefreshEnabled(true);
        ma_recycler.setLoadingMoreEnabled(true);
        ma_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        mPersentermpl.startgetRequest(String.format(path,id,Page,5),Magic.class);

    }

    @Override
    public void success(Object data) {
        Magic bean= (Magic) data;
        List<Magic.ResultBean> result = bean.getResult();
        if (Page==1){
            magicxAdapter.setList(result);
        }
        else {
            magicxAdapter.addList(result);
        }
        Page++;
        ma_recycler.loadMoreComplete();
        ma_recycler.refreshComplete();

    }
}
