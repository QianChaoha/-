package com.example.railway.activity;

import com.example.railway.R;
import com.example.railway.adapter.HuoyunyuanTitleAdapter;
import com.example.railway.base.BaseActivity;
import com.example.railway.base.BaseExpandableListView;
import com.example.railway.base.BaseListView;
import com.example.railway.bean.HyyTitle;
import com.example.railway.bean.HyyTitle1;
import com.example.railway.uitl.TopBarUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HuoYunYuanActivity extends BaseActivity {
BaseExpandableListView listView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_huoyunyuan;
    }

    @Override
    protected void initView() {
        listView= (BaseExpandableListView) findViewById(R.id.listView);
        new TopBarUtil(this).setTitle("货运员").setLeftImageView(null);
        String json= getIntent().getStringExtra("data");
        Gson gson= new Gson();
        HyyTitle hyyTitle= gson.fromJson(json, HyyTitle.class);
        if (hyyTitle!=null&&hyyTitle.hyyTitle!=null){
            listView.setAdapter(new HuoyunyuanTitleAdapter(this,hyyTitle.hyyTitle));
        }
    }


    @Override
    protected void initData() {
//        HuoyunyuanDBdao huoyunyuanDBdao = new HuoyunyuanDBdao(HuoYunYuanActivity.this);
    }

}
