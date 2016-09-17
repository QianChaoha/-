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

public class HuoYunYuanDetailActivity extends BaseActivity implements OnClickListener, ViewPager.OnPageChangeListener,AdListener {
    ViewPager mViewPager;
    private List<HuoYunYuan> huoYunYuans;
    private List<View> views = new ArrayList<View>();
    TextView tvProcess;
    RadioButton rbshowAnswer, rbWrong, rbRight, rbCollect;
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(msg.what, true);
        }
    };
    private FaultQuestionDBao faultQuestionDBao;
    private final static int MSG_DELAY = 500;
    private ToastShow toastShow;
    RelativeLayout rlSeeAll;
    RelativeLayout root;
    HomeWorkRecoedDBao homeWorkRecoedDBao;
    RelativeLayout rlContent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_huoyunyuan;
    }

    @Override
    protected void initView() {
        new TopBarUtil(this).setTitle("货运员").setLeftImageView(null);
        toastShow = new ToastShow(this);
        root = (RelativeLayout) findViewById(R.id.root);
        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        tvProcess = (TextView) findViewById(R.id.tvProcess);
        rlSeeAll = (RelativeLayout) findViewById(R.id.rlSeeAll);
        rlSeeAll.setOnClickListener(this);
        rbshowAnswer = (RadioButton) findViewById(R.id.rbshowAnswer);
        rbWrong = (RadioButton) findViewById(R.id.rbWrong);
        rbRight = (RadioButton) findViewById(R.id.rbRight);
        rbCollect = (RadioButton) findViewById(R.id.rbCollect);
        rbshowAnswer.setOnClickListener(this);
        rbCollect.setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOnPageChangeListener(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//		当不设置广告条充满屏幕宽时建议放置在父容器中间
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //  testMode：默认false;设置true出现测试广告，false为正式模式，提交安沃审核
//		和发布市场时请务必设置为false；
//		 refreshInterval：以秒为单位，0标示单次请求，最小不能为30秒。
        AdwoAdView adView = new AdwoAdView(this, Config.AN_WO_ID,Config.IS_TEST_AD, 3);
        //		adwo广告条的宽高默认20*50乘以屏幕密度，默认宽是不充满屏宽，如果您想设置设置广告条宽充满屏幕宽您可以在实例化广告对象之前调用AdwoAdView.setBannerMatchScreenWidth(true)
//		设置广告条充满屏幕宽
//		adView.setBannerMatchScreenWidth(true);
//		设置单次请求
//		adView.setRequestInterval(0);
        //如果你有合作渠道，请设置合作渠道id，具体id值请联系安沃工作人员 。可选择设置
//		adView.setMarketId((byte) 9);
        // 设置广告监听回调
        adView.setListener(this);
        // 把广告条加入界面布局
        rlContent.addView(adView, params);
        final HuoyunyuanDBdao huoyunyuanDBdao = new HuoyunyuanDBdao(HuoYunYuanDetailActivity.this);
        mProgressDialog.show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                huoYunYuans = huoyunyuanDBdao.findAll();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (huoYunYuans != null) {
                            tvProcess.setText("0/" + huoYunYuans.size());

                            for (int i = 0; i < huoYunYuans.size(); i++) {

                                View view = View.inflate(HuoYunYuanDetailActivity.this, R.layout.layout_homework, null);
                                TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                                ImageView ivTitleLeft = (ImageView) view.findViewById(R.id.ivTitleLeft);
                                tvTitle.setText(huoYunYuans.get(i).content);
                                FrameLayout flContent = (FrameLayout) view.findViewById(R.id.flContent);
                                View content = null;
                                final int j = i;
                                switch (huoYunYuans.get(i).type) {
                                    case "单选题":

                                        ivTitleLeft.setBackgroundResource(R.drawable.jiakao_practise_danxuanti_day);
                                        content = View.inflate(HuoYunYuanDetailActivity.this, R.layout.layout_single_check, null);
                                        flContent.addView(content);
                                        final RadioButton rbSingleCheck0 = (RadioButton) content.findViewById(R.id.rbCheck0);
                                        final RadioButton rbSingleCheck1 = (RadioButton) content.findViewById(R.id.rbCheck1);
                                        final RadioButton rbSingleCheck2 = (RadioButton) content.findViewById(R.id.rbCheck2);
                                        final RadioButton rbSingleCheck3 = (RadioButton) content.findViewById(R.id.rbCheck3);
                                        final RadioGroup rg = (RadioGroup) content.findViewById(R.id.rg);
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check0)) {
                                            rbSingleCheck0.setVisibility(View.VISIBLE);
                                            rbSingleCheck0.setText(huoYunYuans.get(i).check0);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check1)) {
                                            rbSingleCheck1.setVisibility(View.VISIBLE);
                                            rbSingleCheck1.setText(huoYunYuans.get(i).check1);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check2)) {
                                            rbSingleCheck2.setVisibility(View.VISIBLE);
                                            rbSingleCheck2.setText(huoYunYuans.get(i).check2);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check3)) {
                                            rbSingleCheck3.setVisibility(View.VISIBLE);
                                            rbSingleCheck3.setText(huoYunYuans.get(i).check3);
                                        }
                                        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                                            private boolean isCheckSingle = false;

                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (!isCheckSingle) {
                                                    boolean isRight = false;
                                                    int currentItemSingle = (mViewPager.getCurrentItem() == huoYunYuans.size() - 1) ? mViewPager
                                                            .getCurrentItem() : (mViewPager.getCurrentItem() + 1);
                                                    switch (huoYunYuans.get(j).answer) {
                                                        case "A":
                                                            switch (checkedId) {
                                                                case R.id.rbCheck0:
                                                                    mHandler.sendEmptyMessageDelayed(currentItemSingle, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;
                                                                case R.id.rbCheck1:
                                                                    rbSingleCheck1.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck2:
                                                                    rbSingleCheck2.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck3:
                                                                    rbSingleCheck3.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;

                                                            }
                                                            rbSingleCheck0.setButtonDrawable(R.drawable.jiakao_practise_true_day);

                                                            break;
                                                        case "B":
                                                            switch (checkedId) {
                                                                case R.id.rbCheck0:
                                                                    rbSingleCheck0.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck1:
                                                                    mHandler.sendEmptyMessageDelayed(currentItemSingle, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;
                                                                case R.id.rbCheck2:
                                                                    rbSingleCheck2.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck3:
                                                                    rbSingleCheck3.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;

                                                            }
                                                            rbSingleCheck1.setButtonDrawable(R.drawable.jiakao_practise_true_day);

                                                            break;
                                                        case "C":
                                                            switch (checkedId) {
                                                                case R.id.rbCheck0:
                                                                    rbSingleCheck0.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck1:
                                                                    rbSingleCheck1.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck2:
                                                                    mHandler.sendEmptyMessageDelayed(currentItemSingle, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;
                                                                case R.id.rbCheck3:
                                                                    rbSingleCheck3.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;

                                                            }
                                                            rbSingleCheck2.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                            break;
                                                        case "D":
                                                            switch (checkedId) {
                                                                case R.id.rbCheck0:
                                                                    rbSingleCheck0.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck1:
                                                                    rbSingleCheck1.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck2:
                                                                    rbSingleCheck2.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                                case R.id.rbCheck3:
                                                                    mHandler.sendEmptyMessageDelayed(currentItemSingle, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;

                                                            }
                                                            rbSingleCheck3.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                            break;

                                                    }
                                                    upDateNum(isRight,((RadioButton)findViewById(checkedId)).getText().toString());
                                                }
                                                isCheckSingle = true;
                                            }
                                        });
                                        break;
                                    case "多选题":
                                        ivTitleLeft.setBackgroundResource(R.drawable.jiakao_practise_duoxuanti_day);
                                        content = View.inflate(HuoYunYuanDetailActivity.this, R.layout.layout_mutiply_check, null);
                                        flContent.addView(content);
                                        CheckBox cbMutiplyCheck0 = (CheckBox) content.findViewById(R.id.cbCheck0);
                                        CheckBox cbMutiplyCheck1 = (CheckBox) content.findViewById(R.id.cbCheck1);
                                        CheckBox cbMutiplyCheck2 = (CheckBox) content.findViewById(R.id.cbCheck2);
                                        CheckBox cbMutiplyCheck3 = (CheckBox) content.findViewById(R.id.cbCheck3);
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check0)) {
                                            cbMutiplyCheck0.setVisibility(View.VISIBLE);
                                            cbMutiplyCheck0.setText(huoYunYuans.get(i).check0);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check1)) {
                                            cbMutiplyCheck1.setVisibility(View.VISIBLE);
                                            cbMutiplyCheck1.setText(huoYunYuans.get(i).check1);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check2)) {
                                            cbMutiplyCheck2.setVisibility(View.VISIBLE);
                                            cbMutiplyCheck2.setText(huoYunYuans.get(i).check2);
                                        }
                                        if (!TextUtils.isEmpty(huoYunYuans.get(i).check3)) {
                                            cbMutiplyCheck3.setVisibility(View.VISIBLE);
                                            cbMutiplyCheck3.setText(huoYunYuans.get(i).check3);
                                        }
                                        break;
                                    case "判断题":

                                        ivTitleLeft.setBackgroundResource(R.drawable.jiakao_practise_panduanti_day);

                                        content = View.inflate(HuoYunYuanDetailActivity.this, R.layout.layout_judge, null);
                                        flContent.addView(content);
                                        final RadioButton rbRightButton = (RadioButton) content.findViewById(R.id.rbCheckRight);
                                        final RadioButton rbWrongButton = (RadioButton) content.findViewById(R.id.rbCheckWrong);
                                        RadioGroup rgJudge = (RadioGroup) content.findViewById(R.id.rg);
                                        rgJudge.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                                            private boolean isCheckJudge = false;

                                            @Override
                                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                if (!isCheckJudge) {
                                                    boolean isRight = false;
                                                    int currentItemJudge = (mViewPager.getCurrentItem() == huoYunYuans.size() - 1) ? mViewPager
                                                            .getCurrentItem() : (mViewPager.getCurrentItem() + 1);

                                                    switch (huoYunYuans.get(j).answer) {
                                                        case "Y":
                                                            switch (checkedId) {
                                                                case R.id.rbCheckRight:
                                                                    rbRightButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                                    mHandler.sendEmptyMessageDelayed(currentItemJudge, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;
                                                                case R.id.rbCheckWrong:
                                                                    rbRightButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                                    rbWrongButton.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    break;
                                                            }
                                                            break;
                                                        case "N":
                                                            switch (checkedId) {
                                                                case R.id.rbCheckRight:
                                                                    rbRightButton.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                                                    rbWrongButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                                    break;
                                                                case R.id.rbCheckWrong:
                                                                    rbWrongButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                                                                    mHandler.sendEmptyMessageDelayed(currentItemJudge, MSG_DELAY);
                                                                    isRight = true;
                                                                    break;
                                                            }
                                                            break;

                                                        default:
                                                            break;
                                                    }
                                                    upDateNum(isRight,((RadioButton)findViewById(checkedId)).getText().toString());
                                                }
                                                isCheckJudge = true;
                                            }

                                        });
                                        break;

                                    default:
                                        break;
                                }
                                views.add(view);
                            }
                            mViewPager.setAdapter(new HuoyunyuanAdapter(views, HuoYunYuanDetailActivity.this));
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }.start();

    }

    /**
     * 根据答题的对错更新错题和答对题数量
     *
     * @param isRight
     */
    private void upDateNum(boolean isRight,String myAnswer) {
        if (isRight) {
            homeWorkRecoedDBao.add(6,mViewPager.getCurrentItem(),0,myAnswer);
            int rightNum = 0;
            try {
                rightNum = Integer.valueOf(rbRight.getText().toString());
            } catch (Exception e) {
            }

            rbRight.setText((rightNum + 1) + "");
        } else {
            homeWorkRecoedDBao.add(6,mViewPager.getCurrentItem(),1,myAnswer);
            int wrongNum = 0;
            try {
                wrongNum = Integer.valueOf(rbWrong.getText().toString());
            } catch (Exception e) {
            }

            rbWrong.setText((wrongNum + 1) + "");
        }
    }

    @Override
    protected void initData() {
        faultQuestionDBao = new FaultQuestionDBao(this);
        homeWorkRecoedDBao = new HomeWorkRecoedDBao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbshowAnswer:
                switch (huoYunYuans.get(mViewPager.getCurrentItem()).type) {
                    case "单选题":
                        View viewSimple = views.get(mViewPager.getCurrentItem());
                        final RadioButton rbSingleCheck0 = (RadioButton) viewSimple.findViewById(R.id.rbCheck0);
                        final RadioButton rbSingleCheck1 = (RadioButton) viewSimple.findViewById(R.id.rbCheck1);
                        final RadioButton rbSingleCheck2 = (RadioButton) viewSimple.findViewById(R.id.rbCheck2);
                        final RadioButton rbSingleCheck3 = (RadioButton) viewSimple.findViewById(R.id.rbCheck3);

                        String answerSimple = huoYunYuans.get(mViewPager.getCurrentItem()).answer.toUpperCase();
                        if (answerSimple.contains("A")) {
                            rbSingleCheck0.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                        } else if (answerSimple.contains("B")) {
                            rbSingleCheck1.setButtonDrawable(R.drawable.jiakao_practise_true_day);

                        } else if (answerSimple.contains("C")) {
                            rbSingleCheck2.setButtonDrawable(R.drawable.jiakao_practise_true_day);

                        } else {
                            rbSingleCheck3.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                        }
                        break;
                    case "多选题":
                        View viewMutiply = views.get(mViewPager.getCurrentItem());
                        CheckBox cbMutiplyCheck0 = (CheckBox) viewMutiply.findViewById(R.id.cbCheck0);
                        CheckBox cbMutiplyCheck1 = (CheckBox) viewMutiply.findViewById(R.id.cbCheck1);
                        CheckBox cbMutiplyCheck2 = (CheckBox) viewMutiply.findViewById(R.id.cbCheck2);
                        CheckBox cbMutiplyCheck3 = (CheckBox) viewMutiply.findViewById(R.id.cbCheck3);
                        String answer = huoYunYuans.get(mViewPager.getCurrentItem()).answer.toUpperCase();
                        boolean isRight = true;
                        StringBuffer myAnswer=new StringBuffer();
                        //将我的答案记录下来，存储到数据库
                        if (cbMutiplyCheck0.isChecked()){
                            myAnswer.append("A");
                        }
                        if (cbMutiplyCheck1.isChecked()){
                            myAnswer.append("B");
                        }
                        if (cbMutiplyCheck2.isChecked()){
                            myAnswer.append("C");
                        }
                        if (cbMutiplyCheck3.isChecked()){
                            myAnswer.append("D");
                        }
                        if (answer.contains("A")) {
                            if (cbMutiplyCheck0.isChecked()) {
                                cbMutiplyCheck0.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                            } else {
                                isRight = false;
                                cbMutiplyCheck0.setButtonDrawable(R.drawable.jiakao_practise_a_true_day);
                            }

                        } else {
                            if (cbMutiplyCheck0.isChecked()) {
                                cbMutiplyCheck0.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                isRight = false;
                            }
                        }
                        if (answer.contains("B")) {
                            if (cbMutiplyCheck1.isChecked()) {
                                cbMutiplyCheck1.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                            } else {
                                isRight = false;
                                cbMutiplyCheck1.setButtonDrawable(R.drawable.jiakao_practise_b_true_day);
                            }

                        } else {
                            if (cbMutiplyCheck1.isChecked()) {
                                cbMutiplyCheck1.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                isRight = false;
                            }
                        }
                        if (answer.contains("C")) {
                            if (cbMutiplyCheck2.isChecked()) {
                                cbMutiplyCheck2.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                            } else {
                                isRight = false;
                                cbMutiplyCheck2.setButtonDrawable(R.drawable.jiakao_practise_c_true_day);
                            }

                        } else {
                            if (cbMutiplyCheck2.isChecked()) {
                                cbMutiplyCheck2.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                isRight = false;
                            }
                        }
                        if (answer.contains("D")) {
                            if (cbMutiplyCheck3.isChecked()) {
                                cbMutiplyCheck3.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                            } else {
                                isRight = false;
                                cbMutiplyCheck3.setButtonDrawable(R.drawable.jiakao_practise_d_true_day);
                            }

                        } else {
                            if (cbMutiplyCheck3.isChecked()) {
                                cbMutiplyCheck3.setButtonDrawable(R.drawable.jiakao_practise_false_day);
                                isRight = false;
                            }
                        }
                        upDateNum(isRight,myAnswer.toString());
                        break;
                    case "判断题":
                        View viewJudge = views.get(mViewPager.getCurrentItem());
                        final RadioButton rbRightButton = (RadioButton) viewJudge.findViewById(R.id.rbCheckRight);
                        final RadioButton rbWrongButton = (RadioButton) viewJudge.findViewById(R.id.rbCheckWrong);

                        String answerJudge = huoYunYuans.get(mViewPager.getCurrentItem()).answer.toUpperCase();
                        if (answerJudge.contains("Y")) {
                            rbRightButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                        } else {
                            rbWrongButton.setButtonDrawable(R.drawable.jiakao_practise_true_day);
                        }

                        break;
                }
                break;
            case R.id.rbCollect:
                if (!faultQuestionDBao.find(6, huoYunYuans.get(mViewPager.getCurrentItem()).id)) {
                    // 没有收藏
                    faultQuestionDBao.add(6, huoYunYuans.get(mViewPager.getCurrentItem()).id);
                    Drawable drawable = getResources().getDrawable(R.drawable.has_collect);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    rbCollect.setCompoundDrawables(drawable, null, null, null);
                    toastShow.show("收藏成功!");
                } else {
                    // 收藏过了，取消收藏
                    faultQuestionDBao.delete(6, huoYunYuans.get(mViewPager.getCurrentItem()).id);
                    Drawable drawable = getResources().getDrawable(R.drawable.no_collect);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    rbCollect.setCompoundDrawables(drawable, null, null, null);
                    toastShow.show("取消收藏成功!");
                }

                break;
            case R.id.rlSeeAll:
                Intent intent=new Intent(HuoYunYuanDetailActivity.this,SeeAllActivity.class);
                intent.putExtra("size",huoYunYuans.size());
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tvProcess.setText(i + "/" + huoYunYuans.size());
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
    @Override
    public void onReceiveAd(Object o) {

    }

    @Override
    public void onFailedToReceiveAd(View view, ErrorCode errorCode) {

    }

    @Override
    public void onPresentScreen() {

    }

    @Override
    public void onDismissScreen() {

    }

}
