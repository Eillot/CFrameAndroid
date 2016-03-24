package cyl.cframe.android.ui.find;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.lang.reflect.Method;

import cyl.cframe.android.R;
import cyl.cframe.android.base.activity.BaseActionBarActivity;
import cyl.cframe.android.base.activity.BaseActivity;

/**
 * Created by jerry on 2016/3/18.
 */
public class CircleImageViewActivity extends BaseActionBarActivity {
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
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);

    }

    @Override
    protected void setListener() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_weather:
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
