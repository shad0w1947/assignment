package com.example.myapplication;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    EditText textView;
    Button button;
    IBinder binder;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        textView=findViewById(R.id.text_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=textView.getText().toString();
                if(!isMyServiceRunning(MyServices.class)){
                    startService();
                }
                else{
                    Toast.makeText(MainActivity.this,"service is already running",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void startService(){

        ServiceConnection connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder=service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
       Intent i= new Intent(getApplicationContext(),MyServices.class);
       Bundle bundle=new Bundle();
        Log.d("ddd",s);
       bundle.putString("s",s);
       i.putExtra("bundle",bundle);
       bindService(i,connection,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(getApplicationContext(),MyServices.class));
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        stopService(new Intent(getApplicationContext(),MyServices.class));
        super.onStop();
    }
}
