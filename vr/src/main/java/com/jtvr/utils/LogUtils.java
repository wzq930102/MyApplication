package com.jtvr.utils;


import android.util.Log;

public class LogUtils {
	/** 日志输出级别NONE */
	public static final int LEVEL_NONE = 0;
	/** 日志输出级别E */
	public static final int LEVEL_ERROR =1;
	/** 日志输出级别W */
	public static final int LEVEL_WARN = 2;
	/** 日志输出级别I */
	public static final int LEVEL_INFO = 3;
	/** 日志输出级别D */
	public static final int LEVEL_DEBUG = 4;
	/** 日志输出级别V */
	public static final int LEVEL_VERBOSE = 5;

	/** 日志输出时的TAG */
	private static String mTag = "LogUtils";
	/** 是否允许输出log */
	private static int mDebuggable = LEVEL_VERBOSE;

	/** 以级别为 d 的形式输出LOG */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/** 以级别为 d 的形式输出LOG */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	/** 以级别为 i 的形式输出LOG */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}
	public static void i(String tag,String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(tag, msg);
		}
	}


	/** 以级别为 w 的形式输出LOG */
	public static void w(String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出Throwable */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/** 以级别为 w 的形式输出LOG信息和Throwable */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/** 以级别为 e 的形式输出LOG */
	public static void e(String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}

	/** 以级别为 e 的形式输出Throwable */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}


		/**
		 * 截断输出日志
		 * @param msg
		 */
		public static void es(String tag, String msg) {
			if (tag == null || tag.length() == 0
					|| msg == null || msg.length() == 0)
				return;

			int segmentSize = 4 * 1024;
			long length = msg.length();
			if (length <= segmentSize ) {// 长度小于等于限制直接打印
				Log.e(tag, msg);
			}else {
				while (msg.length() > segmentSize ) {// 循环分段打印日志
					String logContent = msg.substring(0, segmentSize );
					msg = msg.replace(logContent, "");
					Log.e(tag, logContent);
				}
				Log.e(tag, msg);// 打印剩余日志
			}
		}

}
