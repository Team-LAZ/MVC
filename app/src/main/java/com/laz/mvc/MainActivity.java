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
    TextView tvClick;
    int nClick = 0;
    String sClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Click The Button");

        btnClick = (Button) findViewById(R.id.btnClick);
        tvClick = (TextView) findViewById(R.id.tvClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nClick++;
                tvClick.setText("Clicked " + nClick + " Times");
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

        sClick = String.valueOf(nClick);

        try {
            outputStream = openFileOutput("clicks.txt", Context.MODE_PRIVATE);
            outputStream.write(sClick.getBytes());
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

            char[] chBuffer = new char[1024];
            String sBuffer = "";
            int nIndex;

            while ((nIndex = inputStreamReader.read(chBuffer)) > 0) {
                String readString = String.copyValueOf(chBuffer, 0, nIndex);
                sBuffer += readString;
            }
            inputStreamReader.close();
            inputStream.close();

            nClick = Integer.parseInt(sBuffer);
            tvClick.setText("Clicked " + nClick + " Times");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

