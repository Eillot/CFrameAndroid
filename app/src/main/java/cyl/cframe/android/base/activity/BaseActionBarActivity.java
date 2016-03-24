package cyl.cframe.android.base.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by jerry on 2016/3/24.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView();
        findView();
        setListener();
        initData();
    }

    protected abstract void setContentView();

    protected abstract void findView();

    protected abstract void initData();

    protected abstract void setListener();
}
