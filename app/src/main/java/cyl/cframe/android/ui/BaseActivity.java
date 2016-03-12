package cyl.cframe.android.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jerry on 2016/3/12.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findView();
        setListener();
        initData();
    }

    protected abstract void findView();

    protected abstract void initData();

    protected abstract void setListener();
}
