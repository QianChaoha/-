package com.example.railway.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.example.railway.R;
import com.example.railway.adapter.HuoyunyuanAdapter;
import com.example.railway.base.BaseActivity;
import com.example.railway.base.BaseListView;
import com.example.railway.bean.HuoYunYuan;
import com.example.railway.dao.FaultQuestionDBao;
import com.example.railway.dao.HomeWorkRecoedDBao;
import com.example.railway.dao.HuoyunyuanDBdao;
import com.example.railway.uitl.Config;
import com.example.railway.uitl.TopBarUtil;
import com.example.railway.view.ToastShow;
import com.sixth.adwoad.AdListener;
import com.sixth.adwoad.AdwoAdView;
import com.sixth.adwoad.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class HuoYunYuanActivity extends BaseActivity {
BaseListView listView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_huoyunyuan;
    }

    @Override
    protected void initView() {
        listView= (BaseListView) findViewById(R.id.listView);
        new TopBarUtil(this).setTitle("货运员").setLeftImageView(null);
        String title1 = getSharedPreferences("config", MODE_PRIVATE).getString(Config.HYY_TITLE1, "");
        if (!TextUtils.isEmpty(title1)) {
            String[] title1Arr = title1.split(",");
            if (title1Arr!=null&&title1Arr.length>0){
                List<String> title1List=new ArrayList<String>();
                for (int i = 0; i < title1Arr.length; i++) {
                    title1List.add(title1Arr[i]);
                }
                if (title1List.size()>0){

                }
            }

        }
    }


    @Override
    protected void initData() {
        HuoyunyuanDBdao huoyunyuanDBdao = new HuoyunyuanDBdao(HuoYunYuanActivity.this);
    }

}
