package com.example.railway.activity;

import java.util.List;
import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.railway.BaMan;
import com.example.railway.BanView;
import com.example.railway.R;
import com.example.railway.base.BaseActivity;
import com.example.railway.dao.HuoyunyuanDBdao;
import com.example.railway.uitl.*;
import com.example.railway.view.CircleImageView;
import com.example.railway.view.Flowlayout;
import com.live.TMan;

public class MainActivity extends BaseActivity implements OnClickListener {
	private static final String[] mContents = new String[] { "电瓶叉车司机", "货运安全员", "货运核算员", "货运计划员", "货运起重工", "货运调度员", "货运员", "货运值班员", "门吊司机", "内燃叉车司机",
			"装卸值班员", "装载机司机" };

	private List<String[]> list;
	private static final String[] fileNameArr = new String[] { "dp_cc.xls", "hy_aq.xls", "hy_hs.xls", "hy_jh.xls", "hy_qz.xls", "hy_dd.xls",
			"huo_yun_yuan.xls", "hy_zb.xls", "md_sj.xls", "nr_cc.xls", "zx_zb.xls", "zaj_sj.xls" };
	private final String FILE_NAME = "huo_yun_yuan.xls";
	private final String URL = "";
	LinearLayout llContent;
	CircleImageView myInfo;
	private DrawerLayout mDrawerLayout;
	private LinearLayout rightMenuLinearLayout;
	public static String[] menuUrl = new String[] { "http://mobile.12306.cn/weixin/wxcore/init", "http://mobile.12306.cn/weixin/wxcore/initCC",
			"http://mobile.12306.cn/weixin/wxcore/qssj", "http://wx.tielu5.com/Study/Default.aspx?openID=oVICFuHmDMZpLNWNy3iaY48FS-NE",
			"http://wx.tielu5.com/Study/ExerciseResult.aspx?openID=oVICFuHmDMZpLNWNy3iaY48FS-NE",
			"http://wx.tielu5.com/User/wxUnitBind.aspx?openID=oVICFuHmDMZpLNWNy3iaY48FS-NE", "http://mobile.12306.cn/weixin/wxcore/ysqcx",
			"http://wx.tielu5.com/user/newslist.aspx?type=today", "http://wx.tielu5.com/user/newslist.aspx?type=rl",
			"http://wx.tielu5.com/user/newslist.aspx?type=sh" };
	public static String[] menuContent = new String[] { "余票查询", "车次查询", "起售时间", "铁路技能答题", "模块化教学-职工绑定(合作单位)", "预售期", "答题铁路排行榜", "今日头条", "铁路头条",
			"社会头条" };
	LinearLayout menuLeft,menuRight;
	TextView tvYp, tvCc, tvQs, Tljn, tvDttl, tvMkhjx, tvYsq, tvJrtt, tvTltt, tvShtt, tvScore,tvShare;
	private RelativeLayout mLayout;
	private TMan tMan;//插屏
	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		//模式：  1:应用内和外都会展示
//		2:只允许应用外展示
//		3:只允许应用内展示
//		4:监听第三方应用打开展示插屏
		tMan = TMan.getInstance(this, Config.JU_ID, "official", 3);
//		tMan.show(mContext);

		View menuContent = View.inflate(this, R.layout.menu_content_layout, null);
		tvYp = (TextView) menuContent.findViewById(R.id.tvYp);
		tvCc = (TextView) menuContent.findViewById(R.id.tvCc);
		tvQs = (TextView) menuContent.findViewById(R.id.tvQs);
		Tljn = (TextView) menuContent.findViewById(R.id.Tljn);
		tvDttl = (TextView) menuContent.findViewById(R.id.tvDttl);
		tvMkhjx = (TextView) menuContent.findViewById(R.id.tvMkhjx);
		tvYsq = (TextView) menuContent.findViewById(R.id.tvYsq);
		tvJrtt = (TextView) menuContent.findViewById(R.id.tvJrtt);
		tvTltt = (TextView) menuContent.findViewById(R.id.tvTltt);
		tvShtt = (TextView) menuContent.findViewById(R.id.tvShtt);
		tvShare = (TextView) menuContent.findViewById(R.id.tvShare);
		tvScore = (TextView) menuContent.findViewById(R.id.tvScore);
		tvYp.setOnClickListener(this);
		tvCc.setOnClickListener(this);
		tvQs.setOnClickListener(this);
		Tljn.setOnClickListener(this);
		tvDttl.setOnClickListener(this);
		tvMkhjx.setOnClickListener(this);
		tvYsq.setOnClickListener(this);
		tvJrtt.setOnClickListener(this);
		tvTltt.setOnClickListener(this);
		tvShtt.setOnClickListener(this);
		tvShare.setOnClickListener(this);
		tvScore.setOnClickListener(this);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		menuLeft = (LinearLayout) findViewById(R.id.menuLeft);
		menuLeft.addView(menuContent);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
		rightMenuLinearLayout = (LinearLayout) mDrawerLayout.getChildAt(1);
		myInfo = (CircleImageView) findViewById(R.id.myInfo);
		myInfo.setOnClickListener(this);
		new TopBarUtil(this).setTitle("路考宝典").setLeftImageViewId(R.drawable.user).setLeftImageView(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		}).setRightImageView(R.drawable.icon_more, new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
					closeRightMenu();
				} else {
					openRightMenu();
				}
			}
		});
		llContent = (LinearLayout) findViewById(R.id.llContent);
		llContent.setBackgroundResource(R.drawable.grid_item_bg_normal);
	}

	@Override
	protected void initData() {
		//广告的初始化
		BaMan.getInstance(this, Config.JU_ID,"");
		Flowlayout layout = new Flowlayout(this);
		int padding = ScreenUtil.dip2px(this, 13);
		layout.setPadding(padding, padding, padding, padding);
		// layout.setOrientation(LinearLayout.VERTICAL);// 设置线性布局的方向
		int backColor = 0xffcecece;
		Drawable pressedDrawable = DrawableUtils.createShape(this, backColor);// 按下显示的图片
		for (int i = 0; i < mContents.length; i++) {
			TextView textView = new TextView(this);
			final String str = mContents[i];
			textView.setText(str);

			Random random = new Random(); // 创建随机
			int red = random.nextInt(200) + 22;
			int green = random.nextInt(200) + 22;
			int blue = random.nextInt(200) + 22;
			int color = Color.rgb(red, green, blue);// 范围 0-255
			GradientDrawable createShape = DrawableUtils.createShape(this, color); // 默认显示的图片
			StateListDrawable createSelectorDrawable = DrawableUtils.createSelectorDrawable(pressedDrawable, createShape);// 创建状态选择器
			textView.setBackgroundDrawable(createSelectorDrawable);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			int textPaddingV = ScreenUtil.dip2px(this, 4);
			int textPaddingH = ScreenUtil.dip2px(this, 15);
			textView.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV); // 设置padding
			textView.setGravity(Gravity.CENTER);
			textView.setClickable(true);// 设置textView可以被点击
			final int j = i;
			textView.setOnClickListener(new OnClickListener() { // 设置点击事件

				@Override
				public void onClick(View v) {
					switch (j) {
					case 6:
						// 货运员
						HuoyunyuanDBdao huoyunyuanDBdao = new HuoyunyuanDBdao(MainActivity.this);
						if (huoyunyuanDBdao.tabbleIsExist()) {
							Intent intent = new Intent(MainActivity.this, HuoYunYuanActivity.class);
							startActivity(intent);
						} else {
							mProgressDialog.setMessage("初始化货运员数据中...");
							mProgressDialog.show();
							new ServiceTask().execute(FILE_NAME);
						}
						break;

					default:
						break;
					}
				}
			});
			layout.addView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, -2));// -2
		}

		llContent.addView(layout);
		// 增加banner
		BanView	banView=new	BanView(this);
		llContent.addView(banView);



	}


	private class ServiceTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {

			String urlStr = params[0];

			try {
				// 读取assets中的excel文件
				ExcelReader er = new ExcelReader(MainActivity.this.getAssets().open(urlStr));

				list = er.getExcelContent();

				HuoyunyuanDBdao huoyunyuanDBdao = new HuoyunyuanDBdao(MainActivity.this);
				for (int i = 1; i < list.size(); i++) {
					huoyunyuanDBdao.add(list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3], list.get(i)[4], list.get(i)[5],
							list.get(i)[6], list.get(i)[7], list.get(i)[8], list.get(i)[9], list.get(i)[10]);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return urlStr;

		}

		@Override
		protected void onPostExecute(String result) {
			mProgressDialog.dismiss();
			super.onPostExecute(result);
			switch (result) {
			case FILE_NAME:// 货运员
				Intent intent = new Intent(MainActivity.this, HuoYunYuanActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, NetHomeWorkActivity.class);
		switch (v.getId()) {
		case R.id.myInfo:
			intent=new Intent(this, NetHomeWorkActivity.class);
			break;
		case R.id.tvYp:
			intent.putExtra("url", menuUrl[0]);
			break;
		case R.id.tvCc:
			intent.putExtra("url", menuUrl[1]);
			break;
		case R.id.tvQs:
			intent.putExtra("url", menuUrl[2]);
			break;
		case R.id.Tljn:
			intent.putExtra("url", menuUrl[3]);
			break;
		case R.id.tvDttl:
			intent.putExtra("url", menuUrl[4]);
			break;
		case R.id.tvMkhjx:
			intent.putExtra("url", menuUrl[5]);
			break;
		case R.id.tvYsq:
			intent.putExtra("url", menuUrl[6]);
			break;
		case R.id.tvJrtt:
			intent.putExtra("url", menuUrl[7]);
			break;
		case R.id.tvTltt:
			intent.putExtra("url", menuUrl[8]);
			break;
		case R.id.tvShtt:
			intent.putExtra("url", menuUrl[9]);
			break;
		case R.id.tvShare:
			intent = null;
			showShare();
			break;
		case R.id.tvScore:
//			intent= new Intent(this, NetHomeWorkActivity.class);
			break;

		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.app_name));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

	public void openRightMenu() {
		mDrawerLayout.openDrawer(Gravity.RIGHT);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
	}

	public void closeRightMenu() {
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
			closeRightMenu();
		} else {
			tMan.showExitDialog(this, new View.OnClickListener() {
				public void onClick(View arg0) {
					Button button= (Button) arg0;
					if ("退出".equals(button.getText().toString())){
						tMan.colseExitDialog();
						MainActivity.super.onBackPressed();
					}

				}
			});

		}
	}
}
