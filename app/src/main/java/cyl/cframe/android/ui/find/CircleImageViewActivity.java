package cyl.cframe.android.ui.find;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import cyl.cframe.android.R;
import cyl.cframe.android.ui.BaseActivity;
import cyl.cframe.library.widgets.Toast;

/**
 * Created by jerry on 2016/3/18.
 */
public class CircleImageViewActivity extends BaseActivity {
    Toolbar toolbar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_circle_image_view);
    }

    @Override
    protected void findView() {
        toolbar = (Toolbar) findViewById(R.id.id_tool_bar);
    }

    @Override
    protected void initData() {
        toolbar.setTitle("Cilcle ImageVIew");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void setListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_msg:
                        android.widget.Toast.makeText(CircleImageViewActivity.this, "消息", android.widget.Toast.LENGTH_SHORT).show();

                        break;
                    case android.R.id.home:
                        finish();
                        break;

                }
                return false;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
