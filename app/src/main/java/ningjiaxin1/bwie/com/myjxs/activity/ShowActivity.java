package ningjiaxin1.bwie.com.myjxs.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.fragment.FragmentCar;
import ningjiaxin1.bwie.com.myjxs.fragment.FragmentCircle;
import ningjiaxin1.bwie.com.myjxs.fragment.FragmentFootprint;
import ningjiaxin1.bwie.com.myjxs.fragment.FragmentHome;
import ningjiaxin1.bwie.com.myjxs.fragment.FragmentMy;

public class ShowActivity extends AppCompatActivity {
    private ViewPager pager;
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        group = (RadioGroup) findViewById(R.id.group);
        final List<Fragment> list=new ArrayList<>();
        list.add(new FragmentHome());
        list.add(new FragmentCar());
        list.add(new FragmentMy());
        list.add(new FragmentFootprint());
        list.add(new FragmentCircle());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button_home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.button_circle:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.button_car:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.button_footprint:
                        pager.setCurrentItem(3);
                        break;
                    case R.id.button_my:
                        pager.setCurrentItem(4);
                        break;
                }
            }
        });
    }
}
