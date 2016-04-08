package cyl.cframe.android.ui.find;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cyl.cframe.android.R;
import cyl.cframe.android.base.activity.BaseActivity;
import cyl.cframe.android.custom.view.HoveringScrollView;

/**
 * Created by jerry on 2016/4/7.
 */
public class SusDockActivity extends BaseActivity implements HoveringScrollView.OnScrollListener {

    private HoveringScrollView hoveringScrollView;
    private int searchLayoutTop;

    LinearLayout hoveringLayout;
    LinearLayout search01, search02;
    RelativeLayout rlayout;
    RelativeLayout rl_title_bar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_sus_dock);
    }

    @Override
    protected void findView() {
        hoveringLayout = (LinearLayout) findViewById(R.id.hoveringLayout);
        hoveringScrollView = (HoveringScrollView) findViewById(R.id.hoveringScrollview);
        search01 = (LinearLayout) findViewById(R.id.search01);
        search02 = (LinearLayout) findViewById(R.id.search02);
        rlayout = (RelativeLayout) findViewById(R.id.rlayout);

        View view=getLayoutInflater().inflate(R.layout.app_navigation_bar,null);
        rl_title_bar = (RelativeLayout) view.findViewById(R.id.rl_title_bar);
        if (rl_title_bar == null) {
            Toast.makeText(this, "没得到", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "终于得到", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        int appBarHeight=rl_title_bar.getBottom();
        if (hasFocus) {
            // 获取searchLayout的顶部位置
            searchLayoutTop = rlayout.getBottom();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        hoveringScrollView.setOnScrollListener(this);// set Listener
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY >= searchLayoutTop) {
            if (hoveringLayout.getParent() != search01) {
                search02.removeView(hoveringLayout);
                search01.addView(hoveringLayout);
            }
        } else {
            if (hoveringLayout.getParent() != search02) {
                search01.removeView(hoveringLayout);
                search02.addView(hoveringLayout);
            }
        }
    }


    public void clickListenerMe(View view) {
        if (view.getId() == R.id.btnQiaBuy) {
            Toast.makeText(this, "抢购成功", Toast.LENGTH_SHORT).show();
        }
    }
}
