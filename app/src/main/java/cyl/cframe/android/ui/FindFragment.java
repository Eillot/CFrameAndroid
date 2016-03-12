package cyl.cframe.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cyl.cframe.android.R;
import cyl.cframe.android.ui.toolbar.ToolBarActivity;
import cyl.cframe.library.fragment.BaseFragment;

/**
 * Created by jerry on 2016/3/8.
 */
public class FindFragment extends BaseFragment {


    LinearLayout llToolBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setContentView(R.layout.fragment_find);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void fillView() {
        llToolBar = (LinearLayout) getActivity().findViewById(R.id.ll_toolbar);
    }

    @Override
    protected void setListener() {
        llToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent().setClass(getActivity(), ToolBarActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }
}
