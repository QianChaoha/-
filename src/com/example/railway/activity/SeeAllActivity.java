package com.example.railway.activity;

import android.view.View;
import android.widget.GridView;
import com.example.railway.R;
import com.example.railway.adapter.SeeAllAdapter;
import com.example.railway.base.BaseActivity;
import com.example.railway.uitl.TopBarUtil;

import java.util.ArrayList;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-8-2810:15
 */
public class SeeAllActivity extends BaseActivity {
    GridView gridView;
    @Override
    protected int getLayoutId() {
        return R.layout.gride_layout;
    }

    @Override
    protected void initView() {
        gridView= (GridView) findViewById(R.id.gridView);
        new TopBarUtil(this).setTitle("全部题目").setLeftImageViewVisiable(View.GONE);
        int size=Integer.valueOf(getIntent().getIntExtra("size",0));
        ArrayList<String> arrayList=new ArrayList<String>(size);
        for (int i = 0; i <size; i++) {
            arrayList.add((i+1)+"");
        }
        gridView.setAdapter(new SeeAllAdapter(this,arrayList));
    }

    @Override
    protected void initData() {

    }
}
