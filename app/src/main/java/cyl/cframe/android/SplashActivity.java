package cyl.cframe.android;

/**
 * Created by jerry on 2016/3/10.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

import org.json.JSONException;
import org.json.JSONObject;

import cyl.cframe.android.data.model.VersionUpdateInfo;
import cyl.cframe.android.utils.BaseHelper;

/**
 * app启动动画界面，做以下动作：
 * 1.读取SharedPreferences，并初始化状态。
 * 2.请求版本信息。
 * 3.如果版本信息中初始化文件版本更新，则使用新版本的初始化文件来初始化数据库，否则使用默认的。
 * 4.进入下个页面
 */
public class SplashActivity extends FragmentActivity {
    //	private ZhuniuApplicition mApp = null;
    private static String deviceId = "";
    private VersionUpdateInfo versionUpdateInfo = new VersionUpdateInfo();
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    /* 升级 */
                    showUpdateDialog();
                    break;
                case 1:
                    //加载数据进入首页面
                    try {
                        Thread.sleep(800);
                        new InitData().execute("appInitData.xml");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    startMainActivity();
                    break;
                case 2:
                    //检查升级
                    checkVersion();

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        loadData();
    }

    protected void loadData() {

        Message msg = Message.obtain();
        msg.what = 2;
        mHandler.sendMessage(msg);

    }

    public void checkVersion() {
        new Thread() {
            public void run() {

                try {
                    String url = null;//Constant.HttpVisitServerUrl.APP_VERSION_UPDATE_URL+ Constant.ApiSaveParams.getKey1();
                    String resObj = null;//HttpUtils.get(url, null);
                    JSONObject jsonObject = new JSONObject();

                    try {
                        Log.i("log", "checkVersion :" + resObj);
                        jsonObject = new JSONObject(resObj);
                        versionUpdateInfo.setVersion(jsonObject.getString("version"));
                        versionUpdateInfo.setForcedUpdate(jsonObject.getString("forcedUpdate"));
                        versionUpdateInfo.setUpdateUrl(jsonObject.getString("updateUrl"));
                        versionUpdateInfo.setUpdateInfo(jsonObject.getString("updateInfo"));
                    } catch (JSONException e) {
                        Log.e("log", "checkVersion error1");
                        e.printStackTrace();
                    }

                    Message msg = Message.obtain();
                    if (versionUpdateInfo.getVersion() != null) {
                        String versionNumber = BaseHelper.getVersion(SplashActivity.this);
                        String updateVersionNumber = versionUpdateInfo.getVersion();//WebServiceContext.getInstance().getVersoin(); //result.getJSONObject("objValue")
                        if ((updateVersionNumber != null) && !updateVersionNumber.equals(versionNumber)) {
                            String[] strings = versionNumber.split("\\.");
                            String[] updateStrings = updateVersionNumber.split("\\.");
                            for (int i = 0; i < updateStrings.length; i++) {
                                float oldVersion = Float.parseFloat(strings[i]);
                                float updateVersion = Float.parseFloat(updateStrings[i]);
                                if (updateVersion > oldVersion) {
                                    //更新版本
                                    msg.what = 0;
                                    mHandler.sendMessage(msg);
                                    return;
                                } else if (updateVersion < oldVersion) {
                                    break;
                                }
                            }
                        }
                    }

                    msg.what = 1;
                    mHandler.sendMessage(msg);

                } catch (Exception e) {
                    Log.e("log", "checkVersion error2");
                    e.printStackTrace();
                }

            }
        }.start();
    }


    private void showUpdateDialog() {
        Dialog alertDialog = new AlertDialog.Builder(SplashActivity.this).setTitle("新版本提示")
                .setMessage(versionUpdateInfo.getUpdateInfo())
                .setPositiveButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //是否强制更新
                        if ("1".equals(versionUpdateInfo.getForcedUpdate())) {
                            System.exit(0);
                        }
                        new InitData().execute("appInitData.xml");
                        startMainActivity();
                    }
                })
                .setNegativeButton("升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startWebBrowser(versionUpdateInfo.getUpdateUrl());
                        System.exit(0);
                    }
                })
                .create();
        alertDialog.show();
        alertDialog.setCancelable(false);//设置这个对话框不能被用户按[返回键]而取消掉
    }

    public void startWebBrowser(String url) {
        Uri myUri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
        this.startActivity(intent);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


    private void startMainActivity() {
        finish();
//        SharedPreferences pre = getSharedPreferences(
//                Constant.SHARE_PREFERENCES, Activity.MODE_PRIVATE);
//        if (pre.getBoolean(PrefrencesKeys.KEY_FIRST_LAUNCH, true)) {
//            Intent myIntent = new Intent();
//            myIntent.setClass(SplashActivity.this, GuidesActivity.class);
//            myIntent.putExtra("msg", "");
//            startActivity(myIntent);
//            SharedPreferences.Editor editor = pre.edit();
//            editor.putBoolean(PrefrencesKeys.KEY_FIRST_LAUNCH, false);
//            editor.commit();
//        } else {
//            Intent myIntent = new Intent();
//            myIntent.setClass(SplashActivity.this, MainActivity.class);
//            startActivity(myIntent);
//            overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//        }

    }

    /*
     * 获取当前程序的版本号
     */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 初始化数据异步任务。
     */
    class InitData extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

//            SharedPreferences pre = getSharedPreferences(
//                    Constant.SHARE_PREFERENCES, Activity.MODE_PRIVATE);
//            // 如果没有初始化过数据库，则要初始化数据库
//            if (!pre.getBoolean(PrefrencesKeys.KEY_INIT_DATA, false)) {
//                String fileName = strings[0];
//                return DataLoader.initDataFromAssets(SplashActivity.this,
//                        fileName);
//            } else {
//                return true;
//            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

//            // 如果初始化数据库成功，则写入状态并请求版本信息。
//            if (aBoolean) {
//
//                SharedPreferences pre = getSharedPreferences(
//                        Constant.SHARE_PREFERENCES, Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = pre.edit();
//                editor.putBoolean(PrefrencesKeys.KEY_INIT_DATA, true);
//                editor.commit();
//
//                // startActivity(intent);
//
//            } else {
//                new AlertDialog.Builder(SplashActivity.this)
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
        }
    }
}