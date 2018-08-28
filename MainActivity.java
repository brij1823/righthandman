package com.righty.sanketpatel.righthandman;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        TextView splash_font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash_font=(TextView)findViewById(R.id.write);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/splashfont.ttf");
        splash_font.setTypeface(custom_font);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Choose.class) ;
                startActivity(i);
                finish();
            }
        },4000);
        
    }
}
