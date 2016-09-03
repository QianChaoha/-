package com.example.railway.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import com.example.railway.R;
import com.example.railway.base.BaseActivity;
import com.example.railway.uitl.Config;
import com.example.railway.uitl.NetConnectedUtil;
import com.live.TMan;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.sixth.adwoad.AdwoAdView;
import com.sixth.adwoad.ErrorCode;
import com.sixth.adwoad.InterstitialAd;
import com.sixth.adwoad.InterstitialAdListener;

public class WelcomeActivity extends BaseActivity implements InterstitialAdListener {
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }

        ;
    };
    private InterstitialAd ad;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        //		Context：嵌入广告的activity。
//		ID:您在Adwo平台注册程序后提供给你的32位字符串。
//		isTesting：请求模式，默认为false,设置true为测试广告模式，提交安沃审核和发布
//		市场时请务必设置为false。
//		FullScreenAdListener：全屏广告回调接口
        ad = new InterstitialAd(this, Config.AN_WO_ID, Config.IS_TEST_AD, this);
//		默认是插屏全屏，设置可选参数值有：
//		InterstitialAd.ADWO_INTERSTITIAL 插屏全屏广告；
//		InterstitialAd.ADWO_INTERSTITIAL_ENTRY开机全屏广告；
        ad.setDesireAdForm(InterstitialAd.ADWO_INTERSTITIAL);
//		设置合作渠道ID,具体id值请联系安沃工作人员。
        ad.setMarketId((byte) 0);
        // 设置请求广告类型 可选。
        ad.setDesireAdType((byte) 0);

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
    public void onReceiveAd() {

    }

    @Override
    public void onLoadAdComplete() {
        ad.displayAd();
    }

    @Override
    public void onFailedToReceiveAd(ErrorCode errorCode) {
        System.out.println("failed");
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onAdDismiss() {
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void OnShow() {

    }

    @Override
    protected void onDestroy() {
        if (ad != null) {
            ad.dismiss();
            ad = null;
        }
        super.onDestroy();
    }
}
