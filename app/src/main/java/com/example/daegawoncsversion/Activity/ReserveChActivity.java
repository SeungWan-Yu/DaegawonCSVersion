package com.example.daegawoncsversion.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daegawoncsversion.R;


public class ReserveChActivity extends BaseActivity {

    TextView rcmEt1, rcmTv1, rcmphone;
    Button rcmBt1, rcnumbt1, rcnumbt2, rcnumbt3, rcnumbt4, rcnumbt5, rcnumbt6, rcnumbt7, rcnumbt8, rcnumbt9, rcnumbt0, rcremove, mlogbt;
    private long mLastClickTime = 0;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_change);

        rcnumbt1 = findViewById(R.id.rcnumbt1);
        rcnumbt2 = findViewById(R.id.rcnumbt2);
        rcnumbt3 = findViewById(R.id.rcnumbt3);
        rcnumbt4 = findViewById(R.id.rcnumbt4);
        rcnumbt5 = findViewById(R.id.rcnumbt5);
        rcnumbt6 = findViewById(R.id.rcnumbt6);
        rcnumbt7 = findViewById(R.id.rcnumbt7);
        rcnumbt8 = findViewById(R.id.rcnumbt8);
        rcnumbt9 = findViewById(R.id.rcnumbt9);
        rcnumbt0 = findViewById(R.id.rcnumbt0);
        rcremove = findViewById(R.id.rcremove);
        rcmBt1 = findViewById(R.id.rcm_bt1);
        rcmEt1 = findViewById(R.id.rc_et1);

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();


        rcnumbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 1);
            }
        });
        rcnumbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 2);
            }
        });
        rcnumbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 3);
            }
        });
        rcnumbt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rcmEt1.setText(rcmEt1.getText().toString() + 4);
            }
        });
        rcnumbt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 5);
            }
        });
        rcnumbt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 6);
            }
        });
        rcnumbt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rcmEt1.setText(rcmEt1.getText().toString() + 7);
            }
        });
        rcnumbt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 8);
            }
        });
        rcnumbt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rcmEt1.setText(rcmEt1.getText().toString() + 9);
            }
        });
        rcnumbt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcmEt1.setText(rcmEt1.getText().toString() + 0);
            }
        });

        //mEt1 TextView 실시간으로 확인
        rcmEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (rcmEt1.length() == 3) {
                    rcmEt1.append("-");
                } else if (rcmEt1.length() == 8) {
                    rcmEt1.append("-");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        rcremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = rcmEt1.getText().toString();
                if (data.length() == 2) { //빈 칸일 경우 switch-case 벗어남
                } else if (data.length() == 5 || data.length() == 9) { //4번째 "-" 때문에 2글자 삭제
                    rcmEt1.setText(data.substring(0, data.length() - 2));
                } else {
                    rcmEt1.setText(data.substring(0, data.length() - 1));
                }
            }
        });
        rcmBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                String phone = rcmEt1.getText().toString();
                // 전화번호가 아무것도 입력되지 않았을 경우
                if (phone.equals("")) {
                    AToast("전화번호가 입력되지 않았습니다.");
                } //전화번호가 13글자 (11글자 + 하이픈)가 아닐 경우
                else if (phone.length() != 13) {
                    AToast("잘못된 전화번호 입니다.");
                } //정상적으로 전화번호가 입력되었을 경우
                else {
                    String reservech = rcmEt1.getText().toString().replace("-", "");
                    //PopupActivity 띄우고 데이터 넘기기
                    Intent intent = new Intent(ReserveChActivity.this, ReserveSchActivity.class);
                    intent.putExtra("reservech", reservech);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}