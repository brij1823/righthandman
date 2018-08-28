package com.righty.sanketpatel.righthandman;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class create extends AppCompatActivity {
    Button b;
    ImageView b2;
    TextView textView,contact;
    Context context;
    EditText editText;
    CalendarView calendar;
    static String msg;
    Button b3;
   static String num;
    static int day,month,year;
    static String url="";
    Database database;
   static String name_contact="";
    int multiple=0,lol=0;
    static int pop=0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database=new Database(this);
        editText=(EditText)findViewById(R.id.msg);
        b=(Button)findViewById(R.id.press);
        b2=(ImageView)findViewById(R.id.contact);
        b3=(Button)findViewById(R.id.timer);
        context=this;
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(create.this,timer.class);
                multiple++;
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lol!=0 && editText.getText().toString() != "" && multiple != 0) {
                    multiple = 0;
                    lol=0;
                    PackageManager packageManager = context.getPackageManager();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    String s = editText.getText().toString();
                    String s2 = "";
                    DatePicker simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker); // initiate a date picker
                    day = simpleDatePicker.getDayOfMonth();
                    month = simpleDatePicker.getMonth();// get the selected month
                    year = simpleDatePicker.getYear(); // get the selected year
                    textView = (TextView) findViewById(R.id.select);
                    String msg = editText.getText().toString();
                    name_contact = getContactName(num, context);
                    int hrs = timer.hour;
                    int min = timer.min;
                    String name = "", shr, smin;


                    switch (month + 1) {
                        case 1:
                            name = "Jan";
                            break;
                        case 2:
                            name = "Feb";
                            break;

                        case 3:
                            name = "Mar";
                            break;
                        case 4:
                            name = "Apr";
                            break;
                        case 5:
                            name = "May";
                            break;
                        case 6:
                            name = "Jun";
                            break;
                        case 7:
                            name = "Jul";
                            break;
                        case 8:
                            name = "Aug";
                            break;
                        case 9:
                            name = "Sept";
                            break;
                        case 10:
                            name = "Oct";
                            break;
                        case 11:
                            name = "Nov";
                            break;
                        case 12:
                            name = "Dec";
                            break;
                        case 13:
                            name = "Jan";
                            break;


                    }

                    String d_day = "";
                    if (hrs < 10) {
                        shr = "0" + hrs;
                    } else {
                        shr = "" + hrs;
                    }
                    if (min < 10) {
                        smin = "0" + min;
                    } else {
                        smin = "" + min;
                    }
                    if (day < 10) {
                        d_day = "0" + day;
                    } else {
                        d_day = "" + day;
                    }
                    s2 = s2 + name + " " + d_day + " " + shr + ":" + smin + ":" + "00 " + "GMT+05:30 " + year;


                    try {
                        String pop = "" + num.charAt(1) + num.charAt(2);

                        if (pop.equals("91")) {
                            url = "https://api.whatsapp.com/send?phone=" + num + "&text=" + URLEncoder.encode(msg, "UTF-8");

                        } else {
                            url = "https://api.whatsapp.com/send?phone=" + "91" + num + "&text=" + URLEncoder.encode(msg, "UTF-8");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (pop == 0) {
                        pop = 1;
                        Intent svc = new Intent(create.this, BackgroundService.class);
                        startService(svc);
                    }


                    boolean a = database.insetData(name_contact, s, s2, url);

                   
                    Cursor res = database.getalldata();
                    StringBuffer buffer = new StringBuffer();
                    while (res.getCount() == 0) {
                        Toast.makeText(create.this, "Error", Toast.LENGTH_LONG).show();
                        return;
                    }

                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + "\n");
                        buffer.append(res.getString(1) + "\n");
                        buffer.append(res.getString(2) + "\n");
                        buffer.append(res.getString(3) + "\n\n");


                        //    Toast.makeText(create.this,buffer,Toast.LENGTH_LONG).show();
                    }

                    Intent i2 = new Intent(create.this, scheduler.class);
                    startActivity(i2);
                    finish();

                }
                else{
                    if(lol==0){
                        Toast.makeText(create.this,"Click the contact icon and select a contact",Toast.LENGTH_SHORT).show();
                    }

                    if(editText.getText().toString()==null){
                        Toast.makeText(create.this,"Please write a message!",Toast.LENGTH_SHORT).show();
                    }

                    if(multiple==0){
                        Toast.makeText(create.this,"Set a time!",Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lol++;
                if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {


                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant

                    return;
                }
                Intent it= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);

                startActivityForResult(it,1);


            }
        });
    }

    private String getContactName(String phoneNumber, Context context) {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="";
        Cursor cursor=context.getContentResolver().query(uri,projection,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri contactData = data.getData();
                String number = "";
                Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                cursor.moveToFirst();
                String hasPhone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                if (hasPhone.equals("1")) {
                    Cursor phones = getContentResolver().query
                            (ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                            + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        number = phones.getString(phones.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("[-() ]", "");

                        num=number;
                         contact=(TextView)findViewById(R.id.number);
                        contact.setText(num.toString());
                    }
                    phones.close();
                    //Do something with number
                } else {
                    Toast.makeText(getApplicationContext(), "This contact has no phone number", Toast.LENGTH_LONG).show();
                }
                cursor.close();
            }
        }
        return;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request

        }
    }
}
