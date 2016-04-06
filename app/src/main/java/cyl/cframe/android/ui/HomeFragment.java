package cyl.cframe.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

import cyl.cframe.android.R;
import cyl.cframe.android.data.TestSimpleData;
import cyl.cframe.android.data.table.AdvertisementInfo;
import cyl.cframe.android.ui.adapter.AdvsPagerAdapter;
import cyl.cframe.android.ui.home.AlertActivity;
import cyl.cframe.android.ui.home.AnimationActivity;
import cyl.cframe.android.ui.home.AudioActivity;
import cyl.cframe.android.ui.home.GridViewActivity;
import cyl.cframe.android.ui.home.ListViewActivity;
import cyl.cframe.android.ui.home.NetworkActivity;
import cyl.cframe.library.database.DBHelper;
import cyl.cframe.library.fragment.BaseFragment;
import cyl.cframe.library.widgets.pulltorefresh.PullToRefreshNewScrollView;
import cyl.cframe.library.widgets.viewpager.ViewPagerEx;
import cyl.cframe.library.widgets.viewpagerindicator.CirclePageIndicator;

/**
 * Created by jerry on 2016/3/8.
 */
public class HomeFragment extends BaseFragment {

    protected static final String tag = "HomeFragment";

    private PullToRefreshNewScrollView scrollView;
    private ViewPagerEx mAdvViewPager;
    private CirclePageIndicator mIndicator;
    private EditText mSearchEdit;
    private LinearLayout titleMessageMainLayout;


    //Button 定义
    private Button btListView, btGridView, btNetwork, btAnimation, btAudio, btAlert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void fillView() {
        try {
            scrollView = (PullToRefreshNewScrollView) getActivity().findViewById(R.id.home_scrollview);
            //轮播
            mAdvViewPager = (ViewPagerEx) getActivity().findViewById(R.id.home_advs_viewpager);
            mAdvViewPager.setAutoStart(true);
            mIndicator = (CirclePageIndicator) getActivity().findViewById(R.id.home_adv_viewpager_indicator);
            //搜索框
            mSearchEdit = (EditText) getActivity().findViewById(R.id.home_search_et);
            titleMessageMainLayout = (LinearLayout) getActivity().findViewById(R.id.home_title_message_lv);

            //各频道入口
            btListView = (Button) getActivity().findViewById(R.id.bt_listview);
            btGridView = (Button) getActivity().findViewById(R.id.bt_gridview);
            btAnimation = (Button) getActivity().findViewById(R.id.bt_animation);
            btAudio = (Button) getActivity().findViewById(R.id.bt_audio);
            btAlert = (Button) getActivity().findViewById(R.id.bt_alert);
            btNetwork = (Button) getActivity().findViewById(R.id.bt_network);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {
        btListView.setOnClickListener(new HomeOnClickListener());
        btGridView.setOnClickListener(new HomeOnClickListener());
        btAnimation.setOnClickListener(new HomeOnClickListener());
        btAudio.setOnClickListener(new HomeOnClickListener());
        btAlert.setOnClickListener(new HomeOnClickListener());
        btNetwork.setOnClickListener(new HomeOnClickListener());
    }

    @Override
    protected void initData() {

    }

    private class HomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.bt_alert:
                        startActivity(new Intent().setClass(getActivity(), AlertActivity.class));
                        break;
                    case R.id.bt_animation:
                        startActivity(new Intent().setClass(getActivity(), AnimationActivity.class));
                        break;
                    case R.id.bt_audio:
                        startActivity(new Intent().setClass(getActivity(), AudioActivity.class));
                        break;
                    case R.id.bt_network:
                        startActivity(new Intent().setClass(getActivity(), NetworkActivity.class));
                        break;
                    case R.id.bt_listview:
                        startActivity(new Intent().setClass(getActivity(), ListViewActivity.class));
                        break;
                    case R.id.bt_gridview:
                        startActivity(new Intent().setClass(getActivity(), GridViewActivity.class));
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 显示首页广告
     *
     * @param advsList
     */
    private void showAdvertisement(List<AdvertisementInfo> advsList) {

        if (advsList.size() <= 0) {
            return;
        }
        if (advsList.size() < 2) {
            mIndicator.setVisibility(View.GONE);
        }
        AdvsPagerAdapter adapter = new AdvsPagerAdapter(getActivity(), advsList);
        mAdvViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mAdvViewPager);
        stopProgressDialog();
    }

    /**
     * 广告信息, 从本地数据库中取
     */
    private List<AdvertisementInfo> getAdvertisementLocal() {

        return DBHelper.getInstance().query(AdvertisementInfo.class, null,
                null, "sortOrder ASC");

    }
}
