package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Bundle bundle =intent.getBundleExtra("bundle");
        String s=bundle.getString("s");
        Log.d("ddd","service start:- "+s);
        return  new MyBinder();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("ddd","service destroy");
        super.onDestroy();
    }
    public class MyBinder extends Binder {

        public MyServices getService(){
            return MyServices.this;
        }

    }
}
