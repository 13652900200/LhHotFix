package com.example.lvhao.lhhotfix.andfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.example.lvhao.lhhotfix.util.Utils;

/**
 * Created by gdgy_lvhao on 2017/7/28.
 * @function 管理AndFix所有的api
 */

public class AndfixPatchManager {
    private static AndfixPatchManager mInstance = null;
    private static PatchManager mPatchManager = null;

    //单例模式
    public static AndfixPatchManager getmInstance(){
        if (mInstance == null){
            synchronized (AndfixPatchManager.class){
                if (mInstance == null){
                    mInstance = new AndfixPatchManager();
                }
            }
        }
        return mInstance;
    }

    //初始化AndFix方法
    public void initPatch(Context context){
        mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersionName(context));
        mPatchManager.loadPatch();
    }

    public void addPatch(String path){
        try{
            if (mPatchManager != null){
                mPatchManager.addPatch(path);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
