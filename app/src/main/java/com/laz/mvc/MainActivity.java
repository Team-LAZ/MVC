package com.laz.mvc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btnClick;
    TextView tv;
    int clicks = 0;
    String _clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Click The Button");

        btnClick = (Button) findViewById(R.id.btn_click);
        tv = (TextView) findViewById(R.id.tv_clicks);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
                tv.setText("Clicked " + clicks + " Times");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            save();
            return true;
        } else if (id == R.id.action_load) {
            load();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void save() {
        System.out.println("Saving...");

        File file = new File(getFilesDir(), "clicks.txt");
        FileOutputStream outputStream;

        _clicks = String.valueOf(clicks);

        try {
            outputStream = openFileOutput("clicks.txt", Context.MODE_PRIVATE);
            outputStream.write(_clicks.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        System.out.println("Loading Data...");

        FileInputStream inputStream;

        try {
            inputStream = openFileInput("clicks.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            char[] charBuffer = new char[1024];
            String stringBuffer = "";
            int charIndex;

            while ((charIndex = inputStreamReader.read(charBuffer)) > 0) {
                String readString = String.copyValueOf(charBuffer, 0, charIndex);
                stringBuffer += readString;
            }
            inputStreamReader.close();
            inputStream.close();

            clicks = Integer.parseInt(stringBuffer);
            tv.setText("Clicked " + clicks + " Times");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

