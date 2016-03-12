package cyl.cframe.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import cyl.cframe.android.constant.ActivityRequestCode;
import cyl.cframe.android.constant.Constant;
import cyl.cframe.android.ui.CategoriesFragment;
import cyl.cframe.android.ui.FindFragment;
import cyl.cframe.android.ui.HomeFragment;
import cyl.cframe.android.ui.MeFragment;
import cyl.cframe.library.activitys.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    protected static final String tag = "MainActivity";
    private final int mHomeIndex = 0;                            // 当前选中tab
    // index
    private final int mWantIndex = 1;
    private final int mFindIndex = 2;
    private final int mAccountIndex = 3;
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    private LinearLayout homeMainLayout;
    private LinearLayout wantMainLayout;
    private LinearLayout findMainLayout;
    private LinearLayout mineMainLayout;
    private ImageView home_main_iv;
    private ImageView home_want_iv;
    private ImageView home_find_iv;
    private ImageView home_mine_iv;
    private HomeFragment homeFragment;
    private CategoriesFragment categoriesFragment;
    private FindFragment findFragment;
    private MeFragment meFragment;

    private int mCurrentIndex;                                        // 当前选中tab
    private int mNomalColor, mSelectColor;                            // 当前选中tab
    private ArrayList<ImageView> mBottomIVs = new ArrayList<ImageView>();
    private ArrayList<TextView> mBottomTVs = new ArrayList<TextView>();
    private ArrayList<Integer> mSelectDrawables = new ArrayList<Integer>();
    private ArrayList<Integer> mNomalDrawables = new ArrayList<Integer>();

    private FragmentManager fm;
    private final String mPageName = "MainActivity";

    @Override
    protected void init() {

        setContentView(R.layout.activity_main);

        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.setAutoLocation(true);
        // MobclickAgent.setSessionContinueMillis(1000);

        fm = getSupportFragmentManager();

    }

    @Override
    protected void fillView() {

        super.fillView();

        try {
            homeMainLayout = (LinearLayout) findViewById(R.id.home_main_layout);
            wantMainLayout = (LinearLayout) findViewById(R.id.home_want_layout);
            findMainLayout = (LinearLayout) findViewById(R.id.home_find_layout);
            mineMainLayout = (LinearLayout) findViewById(R.id.home_mine_layout);

            home_main_iv = (ImageView) findViewById(R.id.home_main_iv);
            home_want_iv = (ImageView) findViewById(R.id.home_want_iv);
            home_find_iv = (ImageView) findViewById(R.id.home_find_iv);
            home_mine_iv = (ImageView) findViewById(R.id.home_mine_iv);

            TextView mall_main_tv = (TextView) findViewById(R.id.home_main_tv);
            TextView home_want_tv = (TextView) findViewById(R.id.home_want_tv);
            TextView home_find_tv = (TextView) findViewById(R.id.home_find_tv);
            TextView home_mine_tv = (TextView) findViewById(R.id.home_mine_tv);

            mBottomIVs.clear();
            mBottomIVs.add(home_main_iv);
            mBottomIVs.add(home_want_iv);
            mBottomIVs.add(home_find_iv);
            mBottomIVs.add(home_mine_iv);

            mBottomTVs.clear();
            mBottomTVs.add(mall_main_tv);
            mBottomTVs.add(home_want_tv);
            mBottomTVs.add(home_find_tv);
            mBottomTVs.add(home_mine_tv);

            mSelectDrawables.clear();
            mSelectDrawables.add(R.mipmap.icon_home_selected);
            mSelectDrawables.add(R.mipmap.icon_purchase_selected);
            mSelectDrawables.add(R.mipmap.icon_find_selected);
            mSelectDrawables.add(R.mipmap.icon_me_selected);

            mNomalDrawables.clear();
            mNomalDrawables.add(R.mipmap.icon_home);
            mNomalDrawables.add(R.mipmap.icon_purchase);
            mNomalDrawables.add(R.mipmap.icon_find);
            mNomalDrawables.add(R.mipmap.icon_me);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void setListener() {

        super.setListener();

        homeMainLayout.setOnClickListener(this);
        wantMainLayout.setOnClickListener(this);
        findMainLayout.setOnClickListener(this);
        mineMainLayout.setOnClickListener(this);

    }

    @Override
    protected void loadData() {

        super.loadData();

        try {
            mNomalColor = getResources().getColor(R.color.home_tab_text_color);
            mSelectColor = getResources().getColor(R.color.home_tab_text_color_selected);

            changeSelectFragment(mHomeIndex);
            mCurrentIndex = mHomeIndex;
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public void onClick(View v) {

        try {
            Intent intent = new Intent();

            switch (v.getId()) {
                case R.id.home_main_layout:
                    // 首页
                    if (mCurrentIndex != mHomeIndex) {

                        changeSelectFragment(mHomeIndex);
                        mCurrentIndex = mHomeIndex;

                    }
                    break;
                case R.id.home_want_layout:
                    // 我要
                    if (mCurrentIndex != mWantIndex) {

                        changeSelectFragment(mWantIndex);
                        mCurrentIndex = mWantIndex;

                    }
                    break;
                case R.id.home_find_layout:
                    // 发现
                    if (mCurrentIndex != mFindIndex) {

                        changeSelectFragment(mFindIndex);
                        mCurrentIndex = mFindIndex;

                    }
                    break;
                case R.id.home_mine_layout:
                    // 我
                    if (mCurrentIndex != mAccountIndex) {
                        changeSelectFragment(mAccountIndex);
                        mCurrentIndex = mAccountIndex;
                        //添加，是否登陆判断
//                        if (UserManager.getInstance().isLogin(this)) {
//                            changeSelectFragment(mAccountIndex);
//                            mCurrentIndex = mAccountIndex;
//                        } else {
//                            // 登陆
//                            intent.setClass(this, LoginActivity.class);
//                            startActivityForResult(intent,
//                                    ActivityRequestCodeDef.SETTING_CODE);
//                        }

                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeSelectFragment(int postion) {

        try {
            FragmentTransaction ft = fm.beginTransaction();

            // 想要显示一个fragment,先隐藏所有fragment，防止重叠
            hideFragments(ft);

            switch (postion) {
                case mHomeIndex:
                    changeSelect_bg(mHomeIndex);

                    if (homeFragment != null)
                        ft.show(homeFragment);
                    else {
                        homeFragment = new HomeFragment();
                        ft.add(R.id.home_container, homeFragment);
                    }

                    break;
                case mWantIndex:
                    changeSelect_bg(mWantIndex);
                    if (categoriesFragment != null)
                        ft.show(categoriesFragment);
                    else {
                        categoriesFragment = new CategoriesFragment();
                        ft.add(R.id.home_container, categoriesFragment);
                    }

                    break;
                case mFindIndex:
                    changeSelect_bg(mFindIndex);
                    if (findFragment != null)
                        ft.show(findFragment);
                    else {
                        findFragment = new FindFragment();
                        ft.add(R.id.home_container, findFragment);
                    }

                    break;
                case mAccountIndex:
                    changeSelect_bg(mAccountIndex);
                    if (meFragment != null)
                        ft.show(meFragment);
                    else {
                        meFragment = new MeFragment();
                        ft.add(R.id.home_container, meFragment);
                    }

                    break;
                default:
                    break;
            }
            ft.commitAllowingStateLoss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeSelect_bg(int postion) {

        try {
            for (int i = 0; i < mBottomIVs.size(); i++) {
                ImageView iv = mBottomIVs.get(i);
                TextView tv = mBottomTVs.get(i);

                if (i == postion) {

                    iv.setImageResource(mSelectDrawables.get(i));
                    tv.setTextColor(mSelectColor);

                } else {
                    iv.setImageResource(mNomalDrawables.get(i));
                    tv.setTextColor(mNomalColor);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 当fragment已被实例化，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {

        if (homeFragment != null)
            ft.hide(homeFragment);
        if (categoriesFragment != null)
            ft.hide(categoriesFragment);
        if (findFragment != null)
            ft.hide(findFragment);
        if (meFragment != null)
            ft.hide(meFragment);

    }

    public void exitApp() {

        try {
            String title = getString(R.string.base_progress_dialog_title);
            String msg = getString(R.string.base_exit_app);
            showDialogForPrompt(title, msg, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //退出应用，统计使用
                    MobclickAgent.onKillProcess(MainActivity.this);
//                    int pid = android.os.Process.myPid();
//                    android.os.Process.killProcess(pid);

                    dialog.dismiss();
                    System.exit(0);
                    overridePendingTransition(activityCloseEnterAnimation,
                            activityCloseExitAnimation);
                }

            }, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void showDialogForPrompt(String title, String msg,
                                       DialogInterface.OnClickListener oklistener,
                                       DialogInterface.OnClickListener cancellistener) {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setPositiveButton(getString(R.string.base_progress_dialog_ok),
                            oklistener);
            if (cancellistener != null) {
                builder.setNegativeButton(
                        getString(R.string.base_progress_dialog_cancel),
                        cancellistener);
            }
            AlertDialog dialog = builder.create();
            dialog.setTitle(title);
            dialog.setMessage(msg);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            if (resultCode == RESULT_OK) {
                switch (requestCode) { // resultCode为回传的标记，我在B中回传的是RESULT_OK
                    case ActivityRequestCode.SETTING_CODE:
                        Bundle b = data.getExtras(); // data为B中回传的Intent
                        boolean str = b.getBoolean("islogin", false); // str即为回传的值
                        if (str) {
                            changeSelectFragment(mAccountIndex);
                            mCurrentIndex = mAccountIndex;
                        }
                        break;
                    case ActivityRequestCode.NOTICE_CODE:
                        Bundle b2 = data.getExtras(); // data为B中回传的Intent
                        boolean str2 = b2.getBoolean("islogin", false); // str即为回传的值
                        if (str2) {
//                            Intent intent = new Intent();
//                            intent.setClass(this, NoticeActivity.class);
//                            startActivity(intent);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化省份
     */
    private void initData() {

        // TODO Auto-generated method stub
//        new InitData().execute("appInitData.xml");

    }


    /**
     * 初始化数据异步任务。
     */
//    class InitData extends AsyncTask<String, Integer, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//
//            SharedPreferences pre = getSharedPreferences(
//                    Constant.SHARE_PREFERENCES, Activity.MODE_PRIVATE);
//            // 如果没有初始化过数据库，则要初始化数据库
//            if (!pre.getBoolean(PrefrencesKeys.KEY_INIT_DATA, false)) {
//                String fileName = strings[0];
//                return DataLoader.initDataFromAssets(MainActivity.this,
//                        fileName);
//            } else {
//                return true;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//
//            // 如果初始化数据库成功，则写入状态并请求版本信息。
//            if (aBoolean) {
//
//                SharedPreferences pre = getSharedPreferences(
//                        Constant.SHARE_PREFERENCES, Activity.MODE_PRIVATE);
//                Editor editor = pre.edit();
//                editor.putBoolean(PrefrencesKeys.KEY_INIT_DATA, true);
//                editor.commit();
//
//                // startActivity(intent);
//
//            } else {
//                new AlertDialog.Builder(MainActivity.this)
//                        .setTitle("提示框")
//                        .setMessage("初始化数据失败")
//                        .setPositiveButton("确定",
//                                new DialogInterface.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(
//                                            DialogInterface dialog, int which) {
//
//                                        // TODO Auto-generated method stub
//
//                                    }
//                                }).show();
//            }
//        }
//    }

}
