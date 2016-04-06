package cyl.cframe.android.ui.me;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import cyl.cframe.android.R;
import cyl.cframe.android.base.activity.BaseActivity;

/**
 * Created by jerry on 2016/4/6.
 */
public class CardViewActivity extends BaseActivity {

    private CardView cardView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_card_view_new);
    }

    @Override
    protected void findView() {
        cardView = (CardView) findViewById(R.id.card_view);
    }

    @Override
    protected void initData() {
//        cardView.setCardElevation();
    }

    @Override
    protected void setListener() {

    }


}
