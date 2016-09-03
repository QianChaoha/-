package com.example.railway.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.railway.base.db.HuoyunyuanOperHelper;

/**
 * Title: Description: Company:
 * 
 * @author qianchao
 * @date 2016-8-267:55
 */
public class HomeWorkRecoedDBao {
	private HuoyunyuanOperHelper helper;
	private Context context;

	public HomeWorkRecoedDBao(Context context) {
		helper = new HuoyunyuanOperHelper(context);
		this.context = context;
	}

	/**
	 * type表示是哪一张表，参考MainActivity.mContents(0表示电瓶叉车司机，1表示货运安全员...)。
	 * type_id表示这道题在该表里的id方便从该表将这条字段取出(该字段从1开始)
	 * 答对homework_id为0，否则为1
	 * @param type
	 * @param type_id
	 * @param homework_id
	 */
	public void add(int type, int type_id,int homework_id,String myanswer) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		// values.put("id", id);
		values.put("type", type);
		values.put("type_id", type_id);
		values.put("homework_id", homework_id);
		values.put("myanswer", myanswer);
		long a = -1;
		switch (type) {
		case 6:// 货运员
			a = db.insert(HuoyunyuanOperHelper.HOMEWORK_RECORD, null, values);
			break;

		default:
			break;
		}
		System.out.println("insert:" + a+"  myanswer  "+myanswer);
		db.close();
	}

	public int find(int type, int type_id) {
		int result=0;
//		boolean result = false;
//		HuoYunYuan huoYunYuan = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		// 存储数据的时候id是从1开始的
		Cursor cursor = null;
		cursor = db.rawQuery("select * from " + HuoyunyuanOperHelper.HOMEWORK_RECORD + " where type=? and type_id=?", new String[] { type + "", type_id + "" });
		if (cursor != null && cursor.moveToNext()) {// cursor是结果集，能够moveToNext()表示查询到了
//			result=true;
			result= cursor.getInt(2);
//			huoYunYuan = new HuoYunYuan();
//			huoYunYuan.id = cursor.getInt(0);
//			huoYunYuan.type = cursor.getString(1);
//			huoYunYuan.content = cursor.getString(2);
//			huoYunYuan.check0 = cursor.getString(3);
//			huoYunYuan.check1 = cursor.getString(4);
//			huoYunYuan.check2 = cursor.getString(5);
//			huoYunYuan.check3 = cursor.getString(6);
//			huoYunYuan.answer = cursor.getString(7);
//			huoYunYuan.order_hyu = cursor.getInt(8);
//			huoYunYuan.nandu = cursor.getDouble(9);
//			huoYunYuan.from_hyu = cursor.getString(10);
		}
		return result;
	}

//	public String getTableName(int type) {
//		String name = "";
//		switch (type) {
//		case 6:// 货运员
//			name = HuoyunyuanOperHelper.FAULT_QUESTION_NAME;
//			break;
//
//		default:
//			break;
//		}
//		return name;
//	}

	public void delete(int type, int type_id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int a = db.delete(HuoyunyuanOperHelper.HOMEWORK_RECORD, "type=? and type_id=?", new String[] { type + "", type_id + "" });
		System.out.println("delete: " + a);
		db.close();
	}
}
