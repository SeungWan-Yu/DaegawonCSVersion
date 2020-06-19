package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.CountModels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.R;
import com.sanbot.opensdk.beans.FuncConstant;
import com.sanbot.opensdk.function.unit.HardWareManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;

import io.socket.client.Socket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends BaseActivity {

    Retrofit retrofit;
    Intent intent;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    TextView mEt1, mTv1, mphone, toast3;
    DataInputStream is;
    DataOutputStream os;
    String msg = "";
    private static final int PORT = 10001; //서버에서 설정한 PORT 번호
    boolean isConnected = true;
    EditText edit_ip;   //서버의 IP를 작성할 수 있는 EditText
    private HardWareManager hardWareManager;
    Button mBt1, numbt1, numbt2, numbt3, numbt4, numbt5, numbt6, numbt7, numbt8, numbt9, numbt0, remove, mlogbt;
    private long mLastClickTime = 0;

    private ServerSocket serverSocket;
    private Handler mHandler = new Handler();
    private Socket socket;
    private DataOutputStream writeSocket;
    private DataInputStream readSocket;


    @SuppressLint({"SetTextI18n", "CommitPrefEdits", "HandlerLeak"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hardWareManager = (HardWareManager) getUnitManager(FuncConstant.HARDWARE_MANAGER);

        mEt1 = findViewById(R.id.m_et1);
        mTv1 = findViewById(R.id.m_tv1);
        mphone = findViewById(R.id.mphone);
        mBt1 = findViewById(R.id.m_bt1);
        numbt1 = findViewById(R.id.numbt1);
        numbt2 = findViewById(R.id.numbt2);
        numbt3 = findViewById(R.id.numbt3);
        numbt4 = findViewById(R.id.numbt4);
        numbt5 = findViewById(R.id.numbt5);
        numbt6 = findViewById(R.id.numbt6);
        numbt7 = findViewById(R.id.numbt7);
        numbt8 = findViewById(R.id.numbt8);
        numbt9 = findViewById(R.id.numbt9);
        numbt0 = findViewById(R.id.numbt0);
        remove = findViewById(R.id.remove);

        toast3 = findViewById(R.id.toast3);

        Handler handler = new Handler();
        handler.postDelayed(new handler1(),30000);

        initListener();
        //로그인 부분

        retrofit = new Retrofit.Builder()
//                            .baseUrl("http://10.0.2.2:3000/") //요청할 도메인 주소
                .baseUrl(ipurl) //여의 시스템 PC
//                .baseUrl("http://211.168.0.15:10000") // 회사에서 노트북
//                            .baseUrl("http://192.168.0.37:10000") //원더플 플랫폼
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();

        currentWait();

        intent = new Intent(MainActivity.this, InfoActivity.class);

        mphone.setText("번호를 입력해주시면 \n문자로 안내해드립니다");
        mEt1.setText("010-");

        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        //mEt1 TextView 실시간으로 확인
        mEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEt1.length() == 3) {
                    mEt1.append("-");
                } else if (mEt1.length() == 8) {
                    mEt1.append("-");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        numbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 1);
            }
        });
        numbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 2);
            }
        });
        numbt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 3);
            }
        });
        numbt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 4);
            }
        });
        numbt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 5);
            }
        });
        numbt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 6);
            }
        });
        numbt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 7);
            }
        });
        numbt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 8);
            }
        });
        numbt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 9);
            }
        });
        numbt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt1.setText(mEt1.getText().toString() + 0);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = mEt1.getText().toString();
                if (data.length() == 2) { //빈 칸일 경우 switch-case 벗어남
                } else if (data.length() == 5 || data.length() == 9) { //4번째 "-" 때문에 2글자 삭제
                    mEt1.setText(data.substring(0, data.length() - 2));
                } else {
                    mEt1.setText(data.substring(0, data.length() - 1));
                }
            }
        });
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                String phone = mEt1.getText().toString();
                // 전화번호가 아무것도 입력되지 않았을 경우
                if (phone.equals("")) {
                    AToast("전화번호가 입력되지 않았습니다.");
                } //전화번호가 13글자 (11글자 + 하이픈)가 아닐 경우
                else if (phone.length() != 13) {
                    AToast("잘못된 전화번호 입니다.");
                } //정상적으로 전화번호가 입력되었을 경우
                else {
                    String userphone = mEt1.getText().toString().replace("-", "");
                    //PopupActivity 띄우고 데이터 넘기기
                    editor.putString("userphone", userphone);
                    editor.apply();
                    Intent popintent = new Intent(MainActivity.this, PopupActivity.class);
                    startActivityForResult(popintent, 0);
                }
            }
        });
        countCDT.start();
    }

    private class handler1 implements Runnable {
        public void run() {
            finish();
        }
    }

    CountDownTimer countCDT = new CountDownTimer(3 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
        }
        public void onFinish() {
            currentWait();
            countCDT.start();
        }
    };

    private void currentWait() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        Keyobject keyobject = new Keyobject(keycode);
        Call<CountModels> call = retrofitInterface.currentWait(keyobject); //DB GET 요청
        call.enqueue(new Callback<CountModels>() {
            @Override
            public void onResponse(Call<CountModels> call, Response<CountModels> response) {
                toast3.setVisibility(View.GONE);
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    mTv1.setText("현재 " + response.body().getBody().getWaitCnt() + " 팀이 대기중 입니다");
                } else {
                    AToast("key값 오류 입니다.");
                }

            }

            @Override
            public void onFailure(Call<CountModels> call, Throwable t) {
//                AToast("통신에 문제가 발생하였습니다.");
                toast3.setVisibility(View.VISIBLE);
                toast3.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
            }
        });
    }

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onActivityResult(int reqeustCode, int resultCode, Intent data) {
        super.onActivityResult(reqeustCode, resultCode, data);
        switch (resultCode) {
            case 0: //Defalt 값
                Toast.makeText(getApplicationContext(), "다시 시도해 주세요", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                break;
            case 2: //정상적으로 알림톡이 전송되었을 때
                toast3.setVisibility(View.GONE);
                setResult(1);
                countCDT.cancel();
                finish();
                break;
            case 3: // 계정정보를 잘못 입력하거나 하트(결제금액)가 부족한 경우
                AToast("계정 정보가 바르지 않습니다. 전송을 실패하였습니다.");
                //text
                break;
            case 4: // 잘못된 요청주소거나 오류가 발생한 경우
                AToast("메세지 전송에 실패했습니다\"");
                break;
            case 5: //Refrofit 통신이 실패한 경우 (인터넷연결 등)
//                AToast("통신 연결에 실패하였습니다.");
                toast3.setVisibility(View.GONE);
                toast3.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
                break;
            case 99: //LodingActivity에서 값 불러오기 실패
                AToast("오류.");
                break;
        }
    }

    //    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }

    private void initListener() {
        //PIR 감지
//        hardWareManager.setOnHareWareListener(new PIRListener() {
//              @Override
//              public void onPIRCheckResult(boolean b, int part) {
//                  if(part == 1){
//                      speechManager.startSpeak("안녕하세요 대가원입니다. 예약을 원하시면 저한테 와주세요.");
//                  }
//              }
//          }
//        );
    }

    @Override
    protected void onMainServiceConnected() {
        speechManager.startSpeak("고객님의 전화번호를 입력해 주세요");
    }
}
