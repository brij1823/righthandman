package com.righty.sanketpatel.righthandman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Choose extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        TextView hint=(TextView) findViewById(R.id.write);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/splashfont.ttf");
        hint.setTypeface(custom_font);
        ImageButton click=(ImageButton)findViewById(R.id.click);
        click.setBackgroundDrawable(null);
        ImageButton imageButton=(ImageButton) findViewById(R.id.click);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Choose.this,scheduler.class);
                startActivity(i);
            }
        });
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant

            return;
        }


    }
}
