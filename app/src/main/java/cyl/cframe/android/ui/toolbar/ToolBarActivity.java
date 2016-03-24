package cyl.cframe.android.ui.toolbar;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cyl.cframe.android.R;
import cyl.cframe.android.base.activity.BaseActivity;

/**
 * Created by jerry on 2016/3/12.
 */
public class ToolBarActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_tool_bar);
    }

    @Override
    protected void findView() {
        toolbar = (Toolbar) findViewById(R.id.id_tool_bar);

    }

    @Override
    protected void initData() {
        toolbar.setTitle("ToolBar");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void setListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_offline:
                        break;
                }
                return false;
            }
        });
    }
}
