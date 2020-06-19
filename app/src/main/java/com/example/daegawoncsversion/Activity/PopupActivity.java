package com.example.daegawoncsversion.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Kakaomodels;
import com.example.daegawoncsversion.Object.KakaoObject;
import com.example.daegawoncsversion.R;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class PopupActivity extends BaseActivity {

    Retrofit retrofit;
    KakaoObject kakaoObject, jsonObject;
    JSONObject object;

    Intent intent;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;

    Button sub, add, send;
    TextView tperson;

    int waitingnum, currentteam, total, code, sum;
    String senderkey, userphone, hap, personn, waitingn, currentn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        userphone = loginInfo.getString("userphone", "");

        intent = new Intent(PopupActivity.this, LoadingAcvitivy.class);

        sub = findViewById(R.id.p_bt1);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = Integer.parseInt(tperson.getText().toString());
                if (sum != 0) tperson.setText(String.valueOf(sum - 1));
            }
        });

        add = findViewById(R.id.p_bt2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum = Integer.parseInt(tperson.getText().toString());
                hap = String.valueOf(sum + 1);
                tperson.setText(hap);
            }
        });

        send = findViewById(R.id.p_bt3);

        tperson = findViewById(R.id.p_tv1);
        send.setEnabled(false);

        tperson.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tperson.getText().toString().equals(String.valueOf(0))) {
                    send.setEnabled(false);
                    send.setBackgroundColor(Color.rgb(213, 213, 213));
                } else {
                    send.setEnabled(true);
                    send.setBackgroundColor(Color.rgb(255, 196, 0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechManager.stopSpeak();

                personn = tperson.getText().toString();

                retrofit = new Retrofit.Builder()
                        .baseUrl(ipurl)
                        .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                        .build();
                kakaoObject = new KakaoObject(keycode, userphone, personn);

                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
                Call<Kakaomodels> call = retrofitInterface.getkakaoJson(kakaoObject); // Post 요청
                call.enqueue(new Callback<Kakaomodels>() {
                    @Override
                    public void onResponse(Call<Kakaomodels> call, final Response<Kakaomodels> response) {
                        assert response.body() != null;
                      if (response.body().getHeader().getMsg().equals("success")){
                          setResult(2);
                          intent.putExtra("rnum", 2);
                          intent.putExtra("phone", userphone);
                          startActivity(intent);
                          finish();
                      } else {
                          setResult(3);
                          intent.putExtra("rnum", 3);
                          intent.putExtra("phone", userphone);
                          startActivity(intent);
                          finish();
                      }
                    }

                    @Override
                    public void onFailure(Call<Kakaomodels> call, Throwable t) {
                        AToast(getString(R.string.error_net));
                    }
                });
            }
        });
    }

    @Override
    protected void onMainServiceConnected() {
        speechManager.startSpeak("예약 인원을 입력해 주세요.");
    }
}