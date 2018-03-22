package com.xiaojie.threadpool;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by xiaojie on 2018/3/19.
 */

public class app extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        refWatcher = setupLeakCanary();
        /*if(LeakCanary.isInAnalyzerProcess(this)){
            //return;
        }
        LeakCanary.install(this);*/
    }

    public static RefWatcher getRefWatcher(Context context){
        app app = (com.xiaojie.threadpool.app) context.getApplicationContext();
        return app.refWatcher;
    }

    private RefWatcher setupLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)){
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
}






















