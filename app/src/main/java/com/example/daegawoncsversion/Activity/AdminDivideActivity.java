package com.example.daegawoncsversion.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daegawoncsversion.R;


public class AdminDivideActivity extends AppCompatActivity {

    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Button wait_list, create, mlogbt;
    private long Back_Key_Before_Time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_divide);

        mlogbt = findViewById(R.id.mlogbt);

        // SharedPreferences 정의
        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        mlogbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDivideActivity.this);
                builder.setTitle("로그아웃").setMessage(getString(R.string.info_logout));
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("id", "");
                        editor.putString("pw", "");
                        editor.putString("type", "");
                        editor.apply();

                        Intent logout = new Intent(AdminDivideActivity.this, LoginActivity.class);
                        startActivity(logout);
                        finish();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        wait_list = findViewById(R.id.wait_list);
        wait_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(AdminDivideActivity.this, AdminActivity.class);
                startActivity(main);
            }
        });

        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(AdminDivideActivity.this, AdminActivity.class);
                startActivity(search);
            }
        });
    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        long result = now - Back_Key_Before_Time;

        if (result < 2000) finish();
        else {
            Toast.makeText(AdminDivideActivity.this, getString(R.string.info_back), Toast.LENGTH_SHORT).show();
            Back_Key_Before_Time = System.currentTimeMillis();
        }
    }
}