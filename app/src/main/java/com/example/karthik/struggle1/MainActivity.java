package com.example.karthik.struggle1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.StringTokenizer;

import static com.example.karthik.struggle1.R.layout.activity_main;
import static com.example.karthik.struggle1.R.layout.notification_action;


public class MainActivity extends AppCompatActivity {public String [] str = new String[50];
    int please =0;
    int k=0;
    int powerhelp=0;
    int helper = 1;
    int x=1;int flag;
 public    int listindex = 0;int c=1;
    int help;
    String strlist= new String();
int power=0;
    int finalvar=0;
    int flag1=0;public int[] savedList = new int[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        final TextView temp = (TextView) findViewById(R.id.textView1);
        final TextView templist = (TextView) findViewById(R.id.textView);
     if(getcolor()!=Color.parseColor("#FFFFFF")){
         temp.setBackgroundColor(getcolor());
     }
        if (gettext() != "") {
            temp.setText(gettext());
            Toast.makeText(this, "Using shared preference", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "First time", Toast.LENGTH_SHORT).show();
        }

        Button reset = (Button) findViewById(R.id.button3);
        reset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
               temp.setText("");
                temp.setBackgroundColor(Color.WHITE);
                templist.setText("");
                 str = new String[50];
                if(x==6||getindex()==5)
                    please=1;
                k=0;
                 powerhelp=0;
                 helper = 1;
                 x=1;
                   listindex = 0; c=1;

                 strlist= new String();
                 power=0;
                 finalvar=0;
                 flag1=0; savedList = new int[20];

            }
        });
        final TextView t1 = (TextView) findViewById(R.id.textView);
        final String[] stone = new String[6];
        stone[0] = "Powerstone";
        stone[1] = "Spacestone";
        stone[2] = "Timestone";
        stone[3] = "Realitystone";
        stone[4] = "Soulstone";
        stone[5] = "Mindstone";
        if (getlist() != "" ) {c=0;
helper=0;

            String savedString = getlist();
            StringTokenizer st = new StringTokenizer(savedString, " ");

            int i;

            for (i = 0; i <=getindex(); i++) {finalvar++;
                if(!st.hasMoreElements())
                    break;
                savedList[i] = Integer.parseInt(st.nextToken());

                t1.append("\n" + stone[savedList[i]]);

            }

        }
        final TextView t = (TextView) findViewById(R.id.textView1);

        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {flag1=0;
                flag = 0;
                if (x == 6||getindex()==5&&please!=1)
                    Toast.makeText(MainActivity.this, "You have obtained all 6 stones", Toast.LENGTH_SHORT).show();


                else {
                    Random random = new Random();
                    help = random.nextInt(6);
                    str[k] = stone[help];
                    if (k!=0 && str[k] == str[k - 1]) {
                        t.setText("You have got a " + str[k] + " again consecutively");
                        saving("You have got a " + str[k] + " again consecutively");
                    } else


                    {
                        t.setText("You have got a " + str[k]);
                        saving("You have got a " + str[k]);
                    }

                    if (str[k] == "Powerstone")
                    {   t.setBackgroundColor(Color.parseColor("#800080"));
                    savecolor(Color.parseColor("#800080"));}
                    else if (str[k] == "Soulstone")
                    {  t.setBackgroundColor(Color.parseColor("#FFA500"));
                    savecolor(Color.parseColor("#FFA500"));}
                    else if (str[k] == "Timestone")
                    { t.setBackgroundColor(Color.GREEN);
                    savecolor(Color.GREEN);}
                    else if (str[k] == "Mindstone")
                    {t.setBackgroundColor(Color.parseColor("#FFFFF0"));
                                  savecolor(Color.parseColor("#FFFFF0")); }
                    else if (str[k] == "Realitystone")
                    {t.setBackgroundColor(Color.RED);
                            savecolor(Color.RED);         }
                    else if (str[k] == "Spacestone") {
                        t.setBackgroundColor(Color.BLUE);
                              savecolor(Color.BLUE);                }
                    if (getlist() == ""||helper==1){
                        if (k == 0) {
                            t1.append("\n " + str[k]);

                            savinglist(help);
                        } else {
                            for (int i = 0; i < k; i++) {
                                if (str[k] == str[k - i - 1])
                                    flag++;
                            }
                            if (flag == 0) {

                                t1.append("\n " + str[k]);

                                savinglist(help);
                                x++;

                            }
                        }
                    k++;
                }
                else if(helper==0){int i;

                        for( i=0 ; i<finalvar;i++){
                            Log.d("for powerstone",String.valueOf(savedList[i]));
                            if(help==savedList[i]){
                                flag1++;

                                break;
                            }
                        }
                    if(flag1==0){finalvar++;

                t1.append("\n"+stone[help]);
                        savinglist(help);
                savedList[i]=help;}
                }
                }
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
            Toast.makeText(MainActivity.this,"Welcome to Landscape mode",Toast.LENGTH_LONG).show();
    }
    private void saving(String save ){
        SharedPreferences share = getSharedPreferences("Save mode", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString("textView",save);
        edit.apply();
    }
    private String gettext(){
        SharedPreferences share = getSharedPreferences("Save mode", Context.MODE_PRIVATE);
        String returntxt = share.getString("textView","" );
        return returntxt;
    }
    private void savinglist(int index){
        SharedPreferences share = getSharedPreferences("Save mode", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();





        String strtemp = new Integer(index).toString();
if(c==0)
{strlist=getlist();
listindex=getindex();
listindex++;}
            strlist=strlist+" "+strtemp;

Log.d("checking strlist",strlist);
        edit.putString("List",strlist);
        edit.putInt("maxindex",listindex);
        edit.apply();
        listindex++;}
    private String getlist(){SharedPreferences share = getSharedPreferences("Save mode", Context.MODE_PRIVATE);
        String returnlist = share.getString("List","");
        return returnlist;

    }
    private int getindex(){
        SharedPreferences share = getSharedPreferences("Save mode",Context.MODE_PRIVATE);
        return share.getInt("maxindex",0);
    }
    private void savecolor(int color){
        SharedPreferences share = getSharedPreferences("Save mode",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putInt("coloring",color);
        edit.apply();
    }
private int getcolor(){
    SharedPreferences share = getSharedPreferences("Save mode",Context.MODE_PRIVATE);
    return share.getInt("coloring",Color.WHITE);
}
}
