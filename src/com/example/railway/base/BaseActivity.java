package com.example.railway.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.railway.uitl.SharePreference;
import com.pgyersdk.crash.PgyCrashManager;

public abstract class BaseActivity extends Activity {
    protected ProgressDialog mProgressDialog;
    protected Context mContext;
    /**
     * 配置文件操作
     */
    protected SharePreference spUtil;

    /**
     * 布局ID
     *
     * @return layoutID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (setNoTitle()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        mContext=this;
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("获取数据中...");
        mProgressDialog.setCancelable(false);

        spUtil = new SharePreference(this, "config");
        setContentView(getLayoutId());
        initView();
        initData();
        PgyCrashManager.register(this);//蒲公英-上报crash异常
    }

    /**
     * Toast提醒
     */
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        PgyCrashManager.unregister();//蒲公英-上报crash异常-取消注册
    }

    /**
     * Volley用于全局的图片内存缓存
     */
    // protected final LruCache<String, Bitmap> imageCache = new
    // LruCache<String, Bitmap>((int) (Runtime.getRuntime()
    // .maxMemory() / 1024 / 8)) {
    // @SuppressLint("NewApi")
    // @Override
    // protected int sizeOf(String key, Bitmap bitmap) {
    // // 重写此方法来衡量每张图片的大小，默认返回图片数量。
    // int size = 0;
    // if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
    // size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
    // } else {
    // size = bitmap.getByteCount() / 1024;
    // }
    // Log.d("LruCache", size + "/" + imageCache.maxSize() + "/" +
    // imageCache.size() + " maxsize:"
    // + Runtime.getRuntime().maxMemory() / 1024 / 8);
    // return size;
    // }
    //
    // };
    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 是设置样式FEATURE_NO_TITLE
     *
     * @return
     */
    protected boolean setNoTitle() {
        return true;
    }

    protected void dimissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
