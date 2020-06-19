package com.example.daegawoncsversion.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daegawoncsversion.R;


public class SeatChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_choice);

        Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (dp.getWidth() * 0.70);
        int height = (int) (dp.getHeight() * 0.75);

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;


    }
}
