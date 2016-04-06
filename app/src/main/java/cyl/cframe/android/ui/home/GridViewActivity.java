package cyl.cframe.android.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import cyl.cframe.android.R;
import cyl.cframe.android.base.activity.BaseActivity;
import cyl.cframe.android.ui.home.gridview.WaterfallsFlowActivity;

/**
 * Created by jerry on 2016/4/1.
 */
public class GridViewActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mStaggeredRl, mDownRefreshRl, mUpRefreshRl, mUpdownRefreshRl;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_gridview);
    }

    @Override
    protected void findView() {
        mStaggeredRl = (RelativeLayout) findViewById(R.id.staggered_rl);
        mDownRefreshRl = (RelativeLayout) findViewById(R.id.down_refresh_rl);
        mUpRefreshRl = (RelativeLayout) findViewById(R.id.up_refresh_rl);
        mUpdownRefreshRl = (RelativeLayout) findViewById(R.id.up_down_refresh_rl);
    }

    @Override
    protected void initData() {
        setTitle("GridView 使用列表");
    }

    @Override
    protected void setListener() {
        mStaggeredRl.setOnClickListener(this);
        mDownRefreshRl.setOnClickListener(this);
        mUpdownRefreshRl.setOnClickListener(this);
        mUpRefreshRl.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_refresh_rl:

                break;
            case R.id.down_refresh_rl:
                break;
            case R.id.up_down_refresh_rl:
                break;
            case R.id.staggered_rl:
                startActivity(new Intent().setClass(this, WaterfallsFlowActivity.class));
                break;

            default:
                break;
        }
    }
}
