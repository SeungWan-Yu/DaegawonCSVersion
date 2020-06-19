package com.example.daegawoncsversion.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Loginmodels;
import com.example.daegawoncsversion.Object.Loginobject;
import com.example.daegawoncsversion.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class LoginActivity extends BaseActivity {

    Retrofit retrofit;
    Loginobject loginObject;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Intent user, admin;

    EditText let1, let2;
    RadioButton lrb1, lrb2;

    String lid, lpw;
    private long Back_Key_Before_Time = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lrb1 = findViewById(R.id.lrb1);
        lrb2 = findViewById(R.id.lrb2);

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        String userid = loginInfo.getString("id", "");
        String userpw = loginInfo.getString("pw", "");
        String type = loginInfo.getString("type", "");

        user = new Intent(LoginActivity.this, DivideActivity.class);
        admin = new Intent(LoginActivity.this, AdminActivity.class);

        if (userid != "") {
            if (type.equals("user")) {
                startActivity(user);
                finish();
            } else if (type.equals("admin")) {
                startActivity(admin);
                finish();
            }
        } else {
            let1 = findViewById(R.id.let1);
            let2 = findViewById(R.id.let2);

            final Button lbt1 = findViewById(R.id.lbt1);
            lbt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    retrofit = new Retrofit.Builder()
//                            .baseUrl("http://10.0.2.2:3000/") //요청할 도메인 주소
                            .baseUrl(ipurl)
//                            .baseUrl("http://211.168.0.15:10000") // 회사에서 노트북
//                            .baseUrl("http://192.168.0.37:10000") //원더플 플랫폼
                            .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                            .build();

                    lid = let1.getText().toString();
                    lpw = let2.getText().toString();

                    if (lid.isEmpty() && lpw.isEmpty()) {
                        AToast("ID와 비밀번호를 입력해주세요.");
                    } else {
                        loginObject = new Loginobject(lid, lpw,keycode);

                        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
                        Call<Loginmodels> call = retrofitInterface.login(loginObject); //Post 요청
                        call.enqueue(new Callback<Loginmodels>() {
                            @Override
                            public void onResponse(Call<Loginmodels> call, final Response<Loginmodels> response) {
                                assert response.body() != null;
                                if (response.body().getHeader().getMsg().equals("success")) {
                                    if (response.body().getBody().getType().equals("user")) {
                                        if (lrb1.isChecked() == true) {
                                            editor.putString("id", lid);
                                            editor.putString("pw", lpw);
                                            editor.putString("type", response.body().getBody().getType());
                                            editor.apply();
                                            startActivity(user);
                                            finish();
                                        } else
                                            AToast("존재하지 않는 ID이거나, 비밀번호가 틀렸습니다.");
                                    } else if (response.body().getBody().getType().equals("admin")) {
                                        if (lrb2.isChecked() == true) {
                                            editor.putString("id", lid);
                                            editor.putString("pw", lpw);
                                            editor.putString("type", response.body().getBody().getType());
                                            editor.apply();
                                            startActivity(admin);
                                            finish();
                                        } else {
                                            AToast("존재하지 않는 ID이거나, 비밀번호가 틀렸습니다.");
                                        }
                                    }
                                }else {
                                    AToast("존재하지 않는 ID이거나, 비밀번호가 틀렸습니다.");
                                }
                            }

                            @Override
                            public void onFailure(Call<Loginmodels> call, Throwable t) {
                                AToast("통신에 문제가 발생하였습니다.");
                            }
                        });
                    }
                }
            });
        }
    }
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        long result = now - Back_Key_Before_Time;

        if(result < 2000) finish();
        else {
            AToast(getString(R.string.info_back));
            Back_Key_Before_Time = System.currentTimeMillis();
        }
    }
}