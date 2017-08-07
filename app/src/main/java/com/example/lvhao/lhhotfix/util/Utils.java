package com.example.lvhao.lhhotfix.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by gdgy_lvhao on 2017/7/28.
 */

public class Utils {

    public static String getVersionName(Context context){
        String versionName = "1.0.0";

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),0);
            versionName = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void printLog() {
        String error = null;
        Log.e("print",error);
    }
}
