package cyl.cframe.android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cyl.cframe.android.R;
import cyl.cframe.library.fragment.BaseFragment;

/**
 * Created by jerry on 2016/3/8.
 */
public class NearbyFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.fragment_nearby);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void fillView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}
