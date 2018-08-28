package com.righty.sanketpatel.righthandman;

/**
 * Created by Sanket Patel on 30-05-2018.
 */

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 5/22/2018.
 */
public class BackgroundService extends Service implements LocationListener {
    boolean isGPSEnable = false;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    long notify_interval = 1000;
    public static String str_receiver = "servicetutorial.service.receiver";
    Intent intent;
    String email;
    static int a=0;
    String name;
    String shr="",smin="";
    Context context;
    private static final String TAG = "SampleActivity";
    int hr,min,day,month,year;
   static String s="";
    public BackgroundService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

         context=this;
       // Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();

        mTimer = new Timer();
        mTimer.schedule(new TimerTaskToGetLocation(),5,notify_interval);
        intent = new Intent(str_receiver);

        fn_getlocation();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @SuppressLint("MissingPermission")
    private void fn_getlocation(){
        fn_update();
    }

    private class TimerTaskToGetLocation extends TimerTask {
        @Override
        public void run() {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    fn_getlocation();
                }
            });

        }
    }

    public void fn_update(){
        Date currentTime = Calendar.getInstance().getTime();
        String s3=""+currentTime;
        String s4="";
        int flag=0;
        for(int i=0;i<s3.length();i++)
        {
            if(s3.charAt(i)==' ' && flag==0){
                flag=1;
            }
            else if(flag==1){
                  s4=s4+ s3.charAt(i);
            }
        }
        Toast toast = Toast.makeText(this,"", Toast.LENGTH_LONG);
        View view = toast.getView();

        view.setBackgroundResource(R.drawable.trans);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        toast.setGravity(Gravity.TOP| Gravity.LEFT, 100000, 0);
/*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
        toast.show();
        Database database;
        database=new Database(this);
        Cursor res=database.getalldata();
        StringBuffer buffer=new StringBuffer();
        while(res.getCount()==0){
            Toast.makeText(BackgroundService.this,"Error",Toast.LENGTH_LONG).show();
            return;
        }

        while (res.moveToNext()){
            String com,ur;
            com=res.getString(2);
            ur=res.getString(3);
          //  Toast.makeText(BackgroundService.this,com+"\n",Toast.LENGTH_SHORT).show();
           // Toast.makeText(BackgroundService.this,s+"\n",Toast.LENGTH_SHORT).show();

            if(s4.equals(com))
            {
                try {

                    PackageManager packageManager = context.getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



                    // url = "https://api.whatsapp.com/send?phone="+"919377724644"+"&text=" + URLEncoder.encode("Testing", "UTF-8");i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(ur));
                    if (i.resolveActivity(packageManager) != null) {
                        context.startActivity(i);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                //create.pop=0;
                //this.stopSelf();
            }
            Log.v(TAG, "++ ON START ++");
            sendBroadcast(intent);


            //    Toast.makeText(create.this,buffer,Toast.LENGTH_LONG).show();
        }




    }



    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
        System.exit(0);
    }

}
