package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Adapter.WaitingAdapterUser;
import com.example.daegawoncsversion.Interface.ClickCallbackListner12;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.Object.DbObject;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.Object.TodayReobject;
import com.example.daegawoncsversion.R;
import com.sanbot.opensdk.function.beans.LED;
import com.sanbot.opensdk.function.beans.handmotion.NoAngleHandMotion;
import com.sanbot.opensdk.function.unit.interfaces.hardware.PIRListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DivideActivity extends BaseActivity {

    Button main;
    Keyobject keyobject;
    RecyclerView recyclerView;
    Retrofit retrofit;
    DbObject dbObject;
    WaitingAdapterUser waitingAdapterUser;
    int repo1;
    Button mlogbt;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Parcelable recyclerViewState;
    Handler hd;
    TextView toast2;
    private int count,count2=0;

    private long Back_Key_Before_Time = 0;

    private ClickCallbackListner12 callbackListner12 = new ClickCallbackListner12() {
        @Override
        public void callback12(final String repo) {
            Intent intent = new Intent(DivideActivity.this, SeatActivity.class);
            intent.putExtra("repo", repo);
            startActivity(intent);
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

        hd = new Handler();

        mlogbt = findViewById(R.id.mlogbt);
        mlogbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DivideActivity.this);
                builder.setTitle("로그아웃").setMessage("정말 로그아웃 하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("id", "");
                        editor.putString("pw", "");
                        editor.putString("type", "");
                        editor.apply();

                        Intent logout = new Intent(DivideActivity.this, LoginActivity.class);
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

        toast2 = findViewById(R.id.toast2);

        main = findViewById(R.id.mainbt);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(DivideActivity.this, MainActivity.class);
                startActivityForResult(main, 0);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        retrofitdb();

        countCDT.start();

        greet();

    }

    private void greet() {
        hardWareManager.setOnHareWareListener(new PIRListener() {
                                                  @Override
                                                  public void onPIRCheckResult(boolean isChecked, int part) {
                                                      if (count == 0) {
                                                          if (count2 == 0) {
                                                              if (part == 1) {
                                                                  CDT.cancel();
                                                                  count = 1;
                                                                  count2 = 1;
                                                                  noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_RIGHT, 5, NoAngleHandMotion.ACTION_UP);
                                                                  handMotionManager.doNoAngleMotion(noAngleHandMotion);
                                                                  hardWareManager.setLED(new LED(LED.PART_ALL, LED.MODE_FLICKER_RANDOM, (byte) 5, (byte) 3));
                                                                  hd.postDelayed(new handler(), 3000);
                                                                  speechManager.startSpeak("반갑습니다, 순 한우 생등심 전문점 대가원 입니다. 예약 하셨나요?");
                                                                  CDT.start();
                                                              }
                                                          }else {
                                                              if (isChecked == true) {
                                                                  CDT.start();
                                                              }else {
                                                                  count = 1;
                                                                  count2 = 1;
                                                                  noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_RIGHT, 5, NoAngleHandMotion.ACTION_UP);
                                                                  handMotionManager.doNoAngleMotion(noAngleHandMotion);
                                                                  hardWareManager.setLED(new LED(LED.PART_ALL, LED.MODE_FLICKER_RANDOM, (byte) 5, (byte) 3));
                                                                  hd.postDelayed(new handler(), 3000);
                                                                  speechManager.startSpeak("반갑습니다, 순 한우 생등심 전문점 대가원 입니다.");
                                                                  CDT.start();
                                                              }
                                                          }
                                                      }
                                                  }
                                              }
        );
    }

    CountDownTimer CDT = new CountDownTimer(15 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            count = 0;
        }
    };

    private class handler implements Runnable {
        public void run() {
            noAngleHandMotion = new NoAngleHandMotion(NoAngleHandMotion.PART_RIGHT, 10, NoAngleHandMotion.ACTION_RESET);
            handMotionManager.doNoAngleMotion(noAngleHandMotion);
            hardWareManager.setLED(new LED(LED.PART_ALL, LED.MODE_CLOSE, (byte) 1, (byte) 1));
        }
    }

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onActivityResult(int reqeustCode, int resultCode, Intent data) {
        super.onActivityResult(reqeustCode, resultCode, data);
        switch (resultCode) {
            case 0: //Defalt 값
                Toast.makeText(getApplicationContext(), "입력이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                retrofitdb();
                break;
            case 2: //정상적으로 알림톡이 전송되었을 때
                toast2.setVisibility(View.GONE);
                break;
            case 3: // 계정정보를 잘못 입력하거나 하트(결제금액)가 부족한 경우
                AToast("계정 정보가 바르지 않습니다. 전송을 실패하였습니다.");
                //text
                break;
            case 4: // 잘못된 요청주소거나 오류가 발생한 경우
                AToast("메세지 전송에 실패했습니다\"");
                break;
            case 5: //Refrofit 통신이 실패한 경우 (인터넷연결 등)
//                AToast("WIFI 연결을 확인해 주세요, 통신에 문제가 있습니다.");
                toast2.setVisibility(View.VISIBLE);
                toast2.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
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

    private void retrofitdb() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        String getTime = String.valueOf(Integer.parseInt(simpleDate.format(mDate)));
        String yyyy = getTime.substring(0, 4);
        String MM = getTime.substring(4, 6);
        String dd = getTime.substring(6, 8);
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        TodayReobject todayReobject = new TodayReobject(keycode, yyyy + "-" + MM + "-" + dd);
        Call<Relistmodels> call = retrofitInterface.relist2(todayReobject); //DB GET 요청
        call.enqueue(new Callback<Relistmodels>() {
            @Override
            public void onResponse(Call<Relistmodels> call, Response<Relistmodels> response) {
                toast2.setVisibility(View.GONE);
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    waitingAdapterUser = new WaitingAdapterUser(response.body().getBody());
                    waitingAdapterUser.setCallbackListner(callbackListner12);
                    recyclerView.setAdapter(waitingAdapterUser);
                    waitingAdapterUser.notifyDataSetChanged();
                    if (recyclerViewState != null)
                        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                } else
                    AToast(getString(R.string.error_key));
            }

            @Override
            public void onFailure(Call<Relistmodels> call, Throwable t) {
//                AToast(getString(R.string.error_net));
                toast2.setVisibility(View.VISIBLE);
                toast2.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
            }
        });
    }

    CountDownTimer countCDT = new CountDownTimer(3 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            retrofitdb();
            countCDT.start();
        }
    };

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();

        long result = now - Back_Key_Before_Time;

        if (result < 2000) {
            finish();
        } else {
            AToast("뒤로가기 버튼을 한번 더 누르면 종료합니다.");
            Back_Key_Before_Time = System.currentTimeMillis();
        }
    }

}
