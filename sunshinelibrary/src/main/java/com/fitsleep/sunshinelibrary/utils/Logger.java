package com.fitsleep.sunshinelibrary.utils;

import android.util.Log;

public class Logger {
	private static final int VERBOSE = 1;
	private static final int DEBUG = 2;
	private static final int INFO = 3;
	private static final int WARN = 4;
	private static final int ERROR = 5;
	private static int LOGLEVEL = 0;
	public static void v(String tag,String msg){
		if(VERBOSE>LOGLEVEL){
			Log.v(tag, msg);
		}
	}
	public static void d(String tag,String msg){
		if(DEBUG>LOGLEVEL){
			Log.d(tag, msg);
		}
	}
	public static void i(String tag,String msg){
		if(INFO>LOGLEVEL){
			Log.i(tag, msg);
		}
	}
	public static void w(String tag,String msg){
		if(WARN>LOGLEVEL){
			Log.w(tag, msg);
		}
	}
	public static void e(String tag,String msg){
		if(ERROR>LOGLEVEL){
			Log.e(tag, msg);
		}
	}
}
