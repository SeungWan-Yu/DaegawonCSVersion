package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Canclemodels;
import com.example.daegawoncsversion.Models.Fixedmodels;
import com.example.daegawoncsversion.Object.CheckReobject;
import com.example.daegawoncsversion.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckDivideActivity extends BaseActivity {

    Button chbt1, chbt2;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    String bookingCd;
    Retrofit retrofit;
    CheckReobject checkReobject;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdivide);

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        Intent intent = getIntent();
        bookingCd = intent.getStringExtra("bookingCd");

        chbt1 = findViewById(R.id.chbt1);
        chbt2 = findViewById(R.id.chbt2);

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();

        checkReobject = new CheckReobject(keycode, bookingCd);

        chbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit1();
            }
        });

        chbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2();
            }
        });
    }

    private void retrofit1() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        Call<Fixedmodels> call = retrofitInterface.reservecheck(checkReobject); // DB GET 요청
        call.enqueue(new Callback<Fixedmodels>() {
            @Override
            public void onResponse(Call<Fixedmodels> call, Response<Fixedmodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    AToast("입장완료");
                    finish();
                } else {
                    AToast("key값 오류");
                }
            }

            @Override
            public void onFailure(Call<Fixedmodels> call, Throwable t) {
                AToast("통신에 문제가 있습니다.");
            }
        });
    }

    private void retrofit2() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        Call<Canclemodels> call = retrofitInterface.reservecancel(checkReobject); // DB GET 요청
        call.enqueue(new Callback<Canclemodels>() {
            @Override
            public void onResponse(Call<Canclemodels> call, Response<Canclemodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    AToast("취소완료");
                    finish();
                } else {
                    AToast("key값 오류");
                }
            }

            @Override
            public void onFailure(Call<Canclemodels> call, Throwable t) {
                AToast("통신에 문제가 있습니다.");
            }
        });
    }
}
