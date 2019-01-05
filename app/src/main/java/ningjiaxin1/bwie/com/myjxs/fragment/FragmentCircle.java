package ningjiaxin1.bwie.com.myjxs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.adapter.CircleAdapter;

public class FragmentCircle extends Fragment {
    private CircleAdapter cAdapter;
    private String path = "http://172.17.8.100/small/circle/v1/findCircleList?userId=%d&sessionId=%s&page=%d&count=5";
    private String prisePath = "http://172.17.8.100/small/circle/verify/v1/addCircleGreat";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentcircle, container, false);
        return view;
    }
}
