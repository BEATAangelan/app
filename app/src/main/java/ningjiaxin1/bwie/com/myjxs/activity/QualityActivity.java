package ningjiaxin1.bwie.com.myjxs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.adapter.MagicxAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.QualiAdapter;
import ningjiaxin1.bwie.com.myjxs.bean.Magic;
import ningjiaxin1.bwie.com.myjxs.bean.Quality;
import ningjiaxin1.bwie.com.myjxs.persenter.MPersentermpl;
import ningjiaxin1.bwie.com.myjxs.view.MView;

public class QualityActivity extends AppCompatActivity implements MView {
    private XRecyclerView qu_recycler;
    private QualiAdapter qualiAdapter;
    MPersentermpl mPersentermpl;
    private int Page=1;
    String path="commodity/v1/findCommodityListByLabel?labelId=%d&page=%d&count=%d";
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality);
        qu_recycler = findViewById(R.id.qu_recycler);
        Page=1;
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        qualiAdapter=new QualiAdapter(this);
        qu_recycler.setAdapter(qualiAdapter);
        qu_recycler.setLayoutManager(layoutManager);
        qu_recycler.setPullRefreshEnabled(true);
        qu_recycler.setLoadingMoreEnabled(true);
        qu_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        mPersentermpl.startgetRequest(String.format(path,id,Page,5),Quality.class);

    }

    @Override
    public void success(Object data) {
        Quality bean= (Quality) data;
        List<Quality.ResultBean> result = bean.getResult();
        if (Page==1){
            qualiAdapter.setList(result);
        }
        else {
            qualiAdapter.addList(result);
        }
        Page++;
        qu_recycler.loadMoreComplete();
        qu_recycler.refreshComplete();

    }
}
