package com.aiven.mygreendaotxt;

import android.app.Application;
import android.content.Context;

import com.aiven.DaoMaster;
import com.aiven.DaoSession;
import com.aiven.DaoMaster.OpenHelper;

public class MyApplication extends Application {

	private static DaoMaster daoMaster;
	private static DaoSession daoSession;

	@Override
	public void onCreate() {
		super.onCreate();		
	}

	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, context.getExternalCacheDir().getAbsolutePath()+"/aiven.db", null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	/**
	* 取得DaoSession
	*
	* @param context
	* @return
	*/
	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

}
