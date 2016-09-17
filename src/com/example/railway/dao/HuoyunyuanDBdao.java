package com.example.railway.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.railway.base.db.HuoyunyuanOperHelper;
import com.example.railway.bean.HuoYunYuan;

public class HuoyunyuanDBdao {
	private HuoyunyuanOperHelper helper;
	private Context context;

	public HuoyunyuanDBdao(Context context) {
		helper = new HuoyunyuanOperHelper(context);
		this.context = context;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public HuoYunYuan find(int id) {
		HuoYunYuan huoYunYuan = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		// 存储数据的时候id是从1开始的
		Cursor cursor = db.rawQuery("select * from " + HuoyunyuanOperHelper.NAME + " where id=?", new String[] { id + "" });
		if (cursor.moveToNext()) {// cursor是结果集，能够moveToNext()表示查询到了
			huoYunYuan=new HuoYunYuan();
			huoYunYuan.id = cursor.getInt(0);
			huoYunYuan.type = cursor.getString(1);
			huoYunYuan.content = cursor.getString(2);
			huoYunYuan.check0 = cursor.getString(3);
			huoYunYuan.check1 = cursor.getString(4);
			huoYunYuan.check2 = cursor.getString(5);
			huoYunYuan.check3 = cursor.getString(6);
			huoYunYuan.answer = cursor.getString(7);
			huoYunYuan.order_hyu = cursor.getInt(8);
			huoYunYuan.nandu = cursor.getDouble(9);
			huoYunYuan.from_hyu = cursor.getString(10);
		}
		return huoYunYuan;
	}

	/**
	 * 查找所有程序锁包名
	 * 
	 * @return
	 */
	public List<HuoYunYuan> findAll() {
		List<HuoYunYuan> huoYunYuans = new ArrayList<HuoYunYuan>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + HuoyunyuanOperHelper.NAME, null);
		while (cursor.moveToNext()) {// cursor是结果集，能够moveToNext()表示查询到了
			HuoYunYuan huoYunYuan = new HuoYunYuan();
			huoYunYuan.id = cursor.getInt(0);
			huoYunYuan.type = cursor.getString(1);
			huoYunYuan.content = cursor.getString(2);
			huoYunYuan.check0 = cursor.getString(3);
			huoYunYuan.check1 = cursor.getString(4);
			huoYunYuan.check2 = cursor.getString(5);
			huoYunYuan.check3 = cursor.getString(6);
			huoYunYuan.answer = cursor.getString(7);
			huoYunYuan.order_hyu = cursor.getInt(8);
			huoYunYuan.nandu = cursor.getDouble(9);
			huoYunYuan.from_hyu = cursor.getString(10);
			huoYunYuans.add(huoYunYuan);
		}
		db.close();
		return huoYunYuans;
	}

	/**
	 * 添加一个要锁定程序的包名
	 * 
	 * @param packagename
	 */
	public void add(String id, String type, String content, String check0, String check1, String check2, String check3, String answer, String order,
			String nandu, String from,String title1,String title2,String title3) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		double doubleId = Double.valueOf(id);
		int intId = (int) doubleId;
		values.put("id", intId);
		values.put("type", type);
		values.put("content", content);
		values.put("check0", check0);
		values.put("check1", check1);
		values.put("check2", check2);
		values.put("check3", check3);
		values.put("answer", answer);
		values.put("title1", title1);
		values.put("title2", title2);
		values.put("title3", title3);
		double doubleOrder = Double.valueOf(order);
		int intOrder = (int) doubleOrder;
		values.put("order_hyu", intOrder);
		values.put("nandu", Double.valueOf(nandu));
		values.put("from_hyu", from);
		long a = db.insert(HuoyunyuanOperHelper.NAME, null, values);
		db.close();

	}

	/**
	 * 判断某张表是否存在
	 * 
	 * @param tabName
	 *            表名
	 * @return
	 */
	public boolean tabbleIsExist() {
		boolean result = false;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = helper.getReadableDatabase();
			String sql = "select * from "+ HuoyunyuanOperHelper.NAME;
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 删除一个锁定程序的包名
	 * 
	 * @param packagename
	 */
	public void delete(String packagename) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("appLock", "packagename=?", new String[] { packagename });
		Intent intent = new Intent();
		intent.setAction("add.app.todb");
		context.sendBroadcast(intent);
		db.close();
	}
}
