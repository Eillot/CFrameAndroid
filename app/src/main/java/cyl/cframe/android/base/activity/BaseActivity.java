package cyl.cframe.android.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jerry on 2016/3/12.
 */
public abstract class BaseActivity extends AppCompatActivity {
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
