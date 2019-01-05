package ningjiaxin1.bwie.com.myjxs.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.activity.HotShowActivity;
import ningjiaxin1.bwie.com.myjxs.activity.MainActivity;
import ningjiaxin1.bwie.com.myjxs.activity.MasigActivity;
import ningjiaxin1.bwie.com.myjxs.activity.QualityActivity;
import ningjiaxin1.bwie.com.myjxs.adapter.HomeViewPagerAdpter;
import ningjiaxin1.bwie.com.myjxs.adapter.HotAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.MagicAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.PagerTransformer;
import ningjiaxin1.bwie.com.myjxs.adapter.QualityAdapter;
import ningjiaxin1.bwie.com.myjxs.adapter.SearchAdapter;
import ningjiaxin1.bwie.com.myjxs.bean.Carou;
import ningjiaxin1.bwie.com.myjxs.bean.Query;
import ningjiaxin1.bwie.com.myjxs.bean.Shop;
import ningjiaxin1.bwie.com.myjxs.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.myjxs.persenter.MPersentermpl;
import ningjiaxin1.bwie.com.myjxs.view.IView;
import ningjiaxin1.bwie.com.myjxs.view.MView;

public class FragmentHome extends Fragment implements IView,MView {
    private int currentItem;
    private int ITEM_COUNT=2;
    private IPersentermpl mIPresenterImpl;
    //滑动
    private ScrollView scrollView;
    //弹框
    @BindView(R.id.switchover)
    ImageView switchover;
    //输入框
    @BindView(R.id.find_edit)
    EditText find_edit;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    //热销商品
    @BindView(R.id.spot_hot)
    TextView spot;
    //查找
    @BindView(R.id.glass)
    ImageView glass;
    //品质生活
    private MagicAdapter magicadapter;
    @BindView(R.id.spot_quality)
    TextView spot_quality;
    //魔力商品
    private QualityAdapter qualityadapter;
    @BindView(R.id.spot_magic)
    TextView spot_magic;
    RecyclerView hotrecyclerView,recyc_mgci,recyc_quality;
    XRecyclerView search_recyc;
    private HotAdapter hotadapter;
    private HomeViewPagerAdpter adapter;
    private MPersentermpl mPersentermpl;
    //查询的adapter
    private SearchAdapter searchadapter;
    private String mBannerUrl = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private String path="http://172.17.8.100/small/commodity/v1/commodityList";
    //查询的地址
    private String searchPath="commodity/v1/findCommodityByKeyword?keyword=%s&page=1&count=5";
    //线程
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentItem++;
            viewPager.setCurrentItem(currentItem);
            sendEmptyMessageDelayed(0,2000);
        }
    };
    private int id,Page=1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);
        Page=1;
        ButterKnife.bind(this,view);
        mIPresenterImpl = new IPersentermpl(this);
        mPersentermpl=new MPersentermpl(this);
        hotrecyclerView = view.findViewById(R.id.recyc);
        search_recyc = view.findViewById(R.id.search_recyc);
        recyc_mgci = view.findViewById(R.id.recyc_mgci);
        recyc_quality = view.findViewById(R.id.recyc_quality);
        scrollView = view.findViewById(R.id.scrollView);
        glass = view.findViewById(R.id.glass);
        //搜索的适配器

        initEdit(view);
        initbanner();
        initlayout();
        //搜索
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = find_edit.getText().toString();
                if(s.equals("")){
                    Toast.makeText(getContext(),"搜啥啊？？？",Toast.LENGTH_LONG).show();
                }else {
                    mPersentermpl.startgetRequest(String.format(searchPath,s),Query.class);
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
                    layoutManager.setOrientation(OrientationHelper.VERTICAL);
                    searchadapter=new SearchAdapter(getActivity());
                    search_recyc.setAdapter(searchadapter);
                    search_recyc.setLayoutManager(layoutManager);
                    search_recyc.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
    //调节输入框提示字大小
    private void initEdit(View view) {
        SpannableString ss = new SpannableString("请输入要搜索的内容");//定义hint的值
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(10,true);//设置字体大小 true表示单位是sp
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        find_edit.setHint(new SpannedString(ss));
    }
    //轮播画廊实现
    private void initbanner(){
     viewPager.setPageMargin(10);
     viewPager.setOffscreenPageLimit(4);
     viewPager.setPageTransformer(true,new PagerTransformer());
     currentItem=viewPager.getCurrentItem();
     handler.sendEmptyMessageDelayed(currentItem,1000);
     mIPresenterImpl.startgetRequest(mBannerUrl,Carou.class);
    }
    public void initlayout(){
        //热销布局
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        hotrecyclerView.setLayoutManager(layoutManager);
        //魔力布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyc_mgci.setLayoutManager(linearLayoutManager);
        //品质生活
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),2);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyc_quality.setLayoutManager(layoutManager1);
        mIPresenterImpl.startgetRequest(path,Shop.class);
    }
    //成功
    @Override
    public void setonSuccess(Object o) {
        //轮播图的请求
        if (o instanceof Carou){
            Carou bean= (Carou) o;
            List<Carou.Result> result = bean.getResult();
            adapter=new HomeViewPagerAdpter(result,getContext());
            viewPager.setAdapter(adapter);
        }
        if (o instanceof Shop){
            //热销商品的方法
            Shop beanhot= (Shop) o;
            List<Shop.ResultBean.RxxpBean> result = beanhot.getResult().getRxxp();
            hotadapter=new HotAdapter(getActivity(),result.get(0).getCommodityList());
            id = result.get(0).getId();
            String name = result.get(0).getName();
            hotrecyclerView.setAdapter(hotadapter);
            //魔力时尚
            List<Shop.ResultBean.MlssBean> mlss = beanhot.getResult().getMlss();
            magicadapter=new MagicAdapter(getActivity(),mlss.get(0).getCommodityList());
            recyc_mgci.setAdapter(magicadapter);
            //品质生活
            List<Shop.ResultBean.PzshBean> pzsh = beanhot.getResult().getPzsh();
            qualityadapter=new QualityAdapter(getActivity(),pzsh.get(0).getCommodityList());
            recyc_quality.setAdapter(qualityadapter);
        }

    }
    //失败
    @Override
    public void onfailed(Exception e) {
        Toast.makeText(getActivity(),"失败",Toast.LENGTH_SHORT).show();
    }
    //点击小点点进行详细的展示
    //点击热销商品跳转
     @OnClick(R.id.spot_hot)
     public void hotShow(){
         Intent intent = new Intent(getActivity(),HotShowActivity.class);
         intent.putExtra("id",id);
         startActivity(intent);
     }
    //点击魔力时尚跳转
     @OnClick(R.id.spot_magic)
     public void magisShow(){
         Intent intent = new Intent(getActivity(),MasigActivity.class);
         intent.putExtra("id",id);
         startActivity(intent);
     }
    //点击品质跳转
    @OnClick(R.id.spot_quality)
    public void quShow(){
        Intent intent = new Intent(getActivity(),QualityActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    //R的请求
    @Override
    public void success(Object data) {
        Query bean = (Query) data;
        List<Query.ResultBean> result = bean.getResult();
        if (result != null) {
            search_recyc.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            if (Page == 1) {
                searchadapter.setList(result);
            } else {
                searchadapter.addList(result);
            }
            Page++;
            search_recyc.loadMoreComplete();
            search_recyc.refreshComplete();
            ;
        }
    }
}
