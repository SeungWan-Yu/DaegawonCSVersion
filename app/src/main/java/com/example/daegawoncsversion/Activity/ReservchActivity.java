package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Interface.ClickCallbackListner12;
import com.example.daegawoncsversion.Object.DbObject;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservchActivity extends BaseActivity {

    Button main;
    Keyobject keyobject;
    RecyclerView recyclerView;
    Retrofit retrofit;
    DbObject dbObject;
    int repo1;
    Button mlogbt;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Parcelable recyclerViewState;

    private long Back_Key_Before_Time = 0;

    private ClickCallbackListner12 callbackListner12 = new ClickCallbackListner12() {
        @Override
        public void callback12(final String repo) {
            AToast(String.valueOf(repo));
        }
    };

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide);

        //SharedPreferences 정의
        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        mlogbt = findViewById(R.id.mlogbt);
        mlogbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReservchActivity.this);
                builder.setTitle("로그아웃").setMessage("정말 로그아웃 하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("id", "");
                        editor.putString("pw", "");
                        editor.putString("type", "");
                        editor.apply();

                        Intent logout = new Intent(ReservchActivity.this, LoginActivity.class);
                        startActivity(logout);
                        finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        recyclerView = findViewById(R.id.arc10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        main = findViewById(R.id.mainbt);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(ReservchActivity.this, MainActivity.class);
                startActivityForResult(main, 0);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();

        countCDT.start();
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
                countCDT.cancel();
                break;
            case 2: //정상적으로 알림톡이 전송되었을 때
                break;
            case 3: // 계정정보를 잘못 입력하거나 하트(결제금액)가 부족한 경우
                AToast("계정 정보가 바르지 않습니다. 전송을 실패하였습니다.");
                //text
                break;
            case 4: // 잘못된 요청주소거나 오류가 발생한 경우
                AToast("메세지 전송에 실패했습니다\"");
                break;
            case 5: //Refrofit 통신이 실패한 경우 (인터넷연결 등)
                AToast("통신 연결에 실패하였습니다.");
                break;
            case 99: //LodingActivity에서 값 불러오기 실패
                AToast("오류.");
                break;

        }
    }

    //        private ClickCallbackListner callbackListner = new ClickCallbackListner() {
//        @Override
//        public void callback(final String repo, String phone) {
//            Intent intent = new Intent(DivideActivity.this, MapActivity.class);
//            intent.putExtra("repo",repo);
//            intent.putExtra("num",1);
//            intent.putExtra("phone",phone);
//            intent.putExtra("map",1);
//            repo1 = Integer.parseInt(repo);
//            startActivity(intent);
//        }
//    };
    {

    }

    CountDownTimer countCDT = new CountDownTimer(3 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            countCDT.start();
        }
    };
}
