package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Dbmodels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.R;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.unit.SpeechManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InfoActivity extends BaseActivity {

    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    int waiting;
    int currentnum;
    private SpeechManager speechManager;
    Retrofit retrofit;
    Keyobject keyobject;
    String userpone;
    TextView itv2;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        register(InfoActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        speechManager = (SpeechManager) getUnitManager(FuncConstant.SPEECH_MANAGER);

        TextView itv1 = findViewById(R.id.itv1);
        itv2 = findViewById(R.id.itv2);
        Button ibt1 = findViewById(R.id.ibt1);

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        //intent로 넘어온 값 받기
        Intent intent = getIntent();
        userpone = intent.getStringExtra("phone");
        currentnum = intent.getIntExtra("current",99);
        int nnn = currentnum -1;

        retrofit = new Retrofit.Builder()
//                            .baseUrl("http://10.0.2.2:3000/") //요청할 도메인 주소
                .baseUrl(ipurl) //여의 시스템 PC
//                .baseUrl("http://211.168.0.15:10000") // 회사에서 노트북
//                            .baseUrl("http://192.168.0.37:10000") //원더플 플랫폼
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        retrofitdb();
        itv1.setText("현재 대기중인 팀은 " + nnn + " 팀 입니다.");

        ibt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechManager.stopSpeak();
                finish();
            }
        });
    }

    private void retrofitdb() {

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        keyobject = new Keyobject(keycode);
        Call<Dbmodels> call = retrofitInterface.getDB(keyobject); //DB GET 요청
        call.enqueue(new Callback<Dbmodels>() {
            @Override
            public void onResponse(Call<Dbmodels> call, Response<Dbmodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    List<Dbmodels.Body> asd = response.body().getBody();
                    for (int i=0; i < asd.size(); i++) {
                        if (asd.get(i).getPhone().equals(userpone)){
                            waiting = Integer.parseInt(asd.get(i).getIdx());
                        }
                    }
                    itv2.setText(String.valueOf(waiting));
                }else {
                    AToast("key값 오류 입니다.");
                }

            }

            @Override
            public void onFailure(Call<Dbmodels> call, Throwable t) {
                AToast("통신에 문제가 발생하였습니다.");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    @Override
    protected void onMainServiceConnected() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        keyobject = new Keyobject(keycode);
        Call<Dbmodels> call = retrofitInterface.getDB(keyobject); //DB GET 요청
        call.enqueue(new Callback<Dbmodels>() {
            @Override
            public void onResponse(Call<Dbmodels> call, Response<Dbmodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    List<Dbmodels.Body> asd = response.body().getBody();
                    for (int i=0; i < asd.size(); i++) {
                        if (asd.get(i).getPhone().equals(userpone)){
                            waiting = Integer.parseInt(asd.get(i).getIdx());
                        }
                    }
                    speechManager.startSpeak("고객님의 대기번호는"+waiting+",번 입니다");
                }else {
                    AToast("key값 오류 입니다.");
                }

            }

            @Override
            public void onFailure(Call<Dbmodels> call, Throwable t) {
                AToast("통신에 문제가 발생하였습니다.");
            }
        });
    }
}
