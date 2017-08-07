package com.example.lvhao.lhhotfix.andfix;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by gdgy_lvhao on 2017/7/31.
 * @function: 1.检查patch文件 2.下载patch文件 3.加载下载好的patch文件
 */

public class AndFixService extends Service{
    private static final String TAG = AndFixService.class.getSimpleName();
    private static final String FILE_END = ".apatch";
    private static final int UPDATE_PATCH = 0x02;
    private static final int DOWNLOAD_PATCH = 0x01;
    private String mPatchFileDir;
    private String mPatchFile;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_PATCH:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_PATCH:
                    downloadPatch();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    //完成文件目录的构造
    private void init(){
        mPatchFileDir = getExternalFilesDir(null).getAbsolutePath();
        File patchDir = new File(mPatchFileDir);
        try{
            if (patchDir == null || !patchDir.exists()){
                patchDir.mkdirs();
            }
        }catch (Exception e){
            e.printStackTrace();
            stopSelf();
        }
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        mHandler.sendEmptyMessage(UPDATE_PATCH);
        return super.onStartCommand(intent, flags, startId);
    }

    //检查服务器是否有patch文件
    private void checkPatchUpdate(){
        //网络请求
        //onSuccess成功时发送消息并调用下载方法
        mHandler.sendEmptyMessage(DOWNLOAD_PATCH);

        //onfailure如果请求失败即停止服务
        stopSelf();
    }

    //完成patch文件的下载
    private void downloadPatch(){
        //初始化patch文件下路径
        mPatchFile = mPatchFileDir.concat(String.valueOf(System.currentTimeMillis())).concat(FILE_END);
        //下载文件请求
        //onSuccess将下载好的patch文件添加到我们的andfix中
        AndfixPatchManager.getmInstance().addPatch(mPatchFile);

        //onfailure如果下载失败即停止服务
        //stopSelf();
    }
}
