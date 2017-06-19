package com.jtvr.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class InstallAPK {

	public static void installApk(File file,Context context)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
}
