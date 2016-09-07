package com.example.railway.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.example.railway.R;
import com.example.railway.base.BaseActivity;
import com.example.railway.uitl.Config;
import com.example.railway.uitl.DownloadImageTask;
import com.example.railway.uitl.NetConnectedUtil;
import com.example.railway.uitl.ScreenUtil;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.sixth.adwoad.ErrorCode;
import com.sixth.adwoad.NativeAdListener;
import com.sixth.adwoad.NativeAdView;
import org.json.JSONObject;

public class WelcomeActivity extends BaseActivity implements NativeAdListener {
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case 0:
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                    break;
                case 1:
                    progressBar.setProgress(progress);
                    tvProgress.setText((PROGRESS_MAX-progress)+"");
                    if (progress<PROGRESS_MAX){
                        mHandler.sendEmptyMessageDelayed(1, 1000);
                    }else {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        finish();
                    }
                    progress++;
                    break;
            }
        }
    };
    int progress=0;
    private NativeAdView ad;
    ImageView splashIv ;
    LinearLayout root;
    TextView splashTv,tvProgress;
    ProgressBar progressBar;
    private static final int PROGRESS_MAX=3;
    FrameLayout     flProgress;
;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        ad = new NativeAdView(this,Config.AN_WO_ID,Config.IS_TEST_AD,this);

        flProgress= (FrameLayout) findViewById(R.id.flProgress);
        splashTv= (TextView) findViewById(R.id.splashTv);
        tvProgress= (TextView) findViewById(R.id.tvProgress);
        root= (LinearLayout) findViewById(R.id.root);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(PROGRESS_MAX);
        splashIv = (ImageView) findViewById(R.id.splashIv);

        //		Context：嵌入广告的activity。
//		ID:您在Adwo平台注册程序后提供给你的32位字符串。
//		isTesting：请求模式，默认为false,设置true为测试广告模式，提交安沃审核和发布
//		市场时请务必设置为false。
//		FullScreenAdListener：全屏广告回调接口
//        ad = new InterstitialAd(this, Config.AN_WO_ID, Config.IS_TEST_AD, this);
////		默认是插屏全屏，设置可选参数值有：
////		InterstitialAd.ADWO_INTERSTITIAL 插屏全屏广告；
////		InterstitialAd.ADWO_INTERSTITIAL_ENTRY开机全屏广告；
//        ad.setDesireAdForm(InterstitialAd.ADWO_INTERSTITIAL);
////		设置合作渠道ID,具体id值请联系安沃工作人员。
//        ad.setMarketId((byte) 0);
//        // 设置请求广告类型 可选。
//        ad.setDesireAdType((byte) 0);


        if (NetConnectedUtil.isConnected(this)) {
            PgyUpdateManager.register(this, new UpdateManagerListener() {

                @Override
                public void onUpdateAvailable(final String result) {
                    System.out.println("onUpdateAvailable");
                    // 将新版本信息封装到AppBean中
                    final AppBean appBean = getAppBeanFromString(result);
                    new AlertDialog.Builder(WelcomeActivity.this)
                            .setTitle("更新")
                            .setMessage("")
                            .setNegativeButton(
                                    "确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            startDownloadTask(
                                                    WelcomeActivity.this,
                                                    appBean.getDownloadURL());
                                        }
                                    }).show();
                }

                @Override
                public void onNoUpdateAvailable() {
                    System.out.println("onNoUpdateAvailable");
                    // 开始请求全屏广告
                    ad.prepareAd();
//                    mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            });
        } else {
//            mHandler.sendEmptyMessageDelayed(0, 3000);
            ad.prepareAd();
        }


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onReceiveAd(String adInfo) {
        try {
            System.out.println( " onReceiveAd:" + adInfo);
            JSONObject json = new JSONObject(adInfo);
            int imgwidth = json.getInt("imgwidth");
            int imgheight = json.getInt("imgheight");
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) splashIv.getLayoutParams();

            if (imgwidth>imgheight){
                params.width=ScreenUtil.getWidth(this);
                params.height = (int) (imgwidth*0.6);
            }else {
                int leftHieght=ScreenUtil.getHeight(this)-splashTv.getHeight();//除了标题栏剩下的高度
                int height=ScreenUtil.getWidth(this)/imgwidth*imgheight;
                params.height=Math.min(leftHieght,height);
                params.width=ScreenUtil.getWidth(this);
            }

            splashIv.setLayoutParams(params);
            ad.setLayoutParams(params);

            DownloadImageTask task = new DownloadImageTask(splashIv) {
                @Override
                public void downLoadFinish() {
                    flProgress.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessage(1);
                }
            };
            String imgurl = json.getString("img");
            int ln = imgurl.lastIndexOf("/");
            String filename = imgurl.substring(ln + 1);
            String[] urlContect = imgurl.split("/");
            String adid = urlContect[urlContect.length-2];
//			为了避免广告图片相互覆盖建议在存储图片名加上广告id。
            task.execute(Environment.getExternalStorageDirectory()+"/"+adid+filename,imgurl );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailedToReceiveAd(View view, ErrorCode errorCode) {
        System.out.println("onFailedToReceiveAd");
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onPresentScreen() {

    }

    @Override
    public void onDismissScreen() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad != null) {
            ad = null;
        }
        mHandler.removeMessages(0);
        mHandler.removeMessages(1);
    }
}
