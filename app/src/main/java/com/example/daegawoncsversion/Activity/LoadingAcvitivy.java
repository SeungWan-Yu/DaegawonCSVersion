package com.example.daegawoncsversion.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.CountModels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.R;

import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class LoadingAcvitivy extends BaseActivity {

    Retrofit retrofit;
    Socket msocket;
    Intent intent11;

    Keyobject keyobject;
    Handler hd;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;

    int result,  currentteam, waitingnum;
    String userpone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView loading = findViewById(R.id.loading);
        Glide.with(this).load(R.drawable.loading).into(loading);

        intent11 = new Intent(LoadingAcvitivy.this, InfoActivity.class);
        hd = new Handler();

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        Intent intent = getIntent();
        int rnum = intent.getIntExtra("rnum",99);
        userpone = intent.getStringExtra("phone");

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl) // 여의 시스템 PC
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        currentWait();

        switch (rnum){
            case 2:
                hd.postDelayed(new handler1(), 1500);
                break;
            case 3:
                hd.postDelayed(new handler1(), 1500);
                break;
            case 4:
                finish();
                break;
            case 5:
                finish();
                break;
            case 99:
                finish();
                break;
        }
    }

    private class handler1 implements Runnable {
        public void run() {
            intent11.putExtra("current", currentteam);
            intent11.putExtra("phone", userpone);
            startActivity(intent11);
            finish();
        }
    }

    private void currentWait() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        Keyobject keyobject = new Keyobject(keycode);
        Call<CountModels> call = retrofitInterface.currentWait(keyobject); // DB GET 요청
        call.enqueue(new Callback<CountModels>() {
            @Override
            public void onResponse(Call<CountModels> call, Response<CountModels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    currentteam = Integer.parseInt(response.body().getBody().getWaitCnt());
                } else AToast(getString(R.string.error_key));
            }

            @Override
            public void onFailure(Call<CountModels> call, Throwable t) {
                AToast(getString(R.string.error_net));
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { // 바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) return false;
        return true;
    }

    @Override
    public void onBackPressed() { // 안드로이드 백버튼 막기
        return;
    }
}