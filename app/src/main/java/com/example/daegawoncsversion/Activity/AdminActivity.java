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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.daegawoncsversion.Adapter.WaitingAdapter2;
import com.example.daegawoncsversion.Adapter.WaitingAdapterAdmin;
import com.example.daegawoncsversion.Interface.ClickCallbackListner;
import com.example.daegawoncsversion.Interface.ClickCallbackListner12;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Callmodels;
import com.example.daegawoncsversion.Models.Dbmodels;
import com.example.daegawoncsversion.Models.Delmodels;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.Object.Seatobject;
import com.example.daegawoncsversion.Object.TodayReobject;
import com.example.daegawoncsversion.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminActivity extends BaseActivity {

    Retrofit retrofit;
    Keyobject keyobject;
    Seatobject seatobject;
    RecyclerView recyclerView,recyclerView2;
    WaitingAdapter2 DBAdapter;
    WaitingAdapterAdmin waitingAdapterAdmin;
    DataOutputStream os;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Button btn_admin_popup,alogbt1,change;
    private ServerSocket serverSocket;
    private Handler mHandler = new Handler();
    private Socket socket;
    private DataOutputStream writeSocket;
    private DataInputStream readSocket;
    TextView aaass,toast;
    Parcelable recyclerViewState;
    Parcelable recyclerViewState2;
    RecyclerView.LayoutManager layoutManager;


    private long Back_Key_Before_Time = 0;

    private ClickCallbackListner callbackListner = new ClickCallbackListner() {
        @Override
        public void callback(final String repo, String phone) {
            layoutManager.removeAllViews();
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
            seatobject = new Seatobject(keycode, String.valueOf(repo), phone);
            Call<Delmodels> call = retrofitInterface.removeWait(seatobject); // DB GET 요청
            call.enqueue(new Callback<Delmodels>() {
                @Override
                public void onResponse(Call<Delmodels> call, Response<Delmodels> response) {
                    toast.setVisibility(View.GONE);
                    assert response.body() != null;
                    if (response.body().getHeader().getMsg().equals("success"))
                        AToast("대기번호" + repo + "번 손님이 입장하였습니다.");
                    else AToast(getString(R.string.error_key));
                }

                @Override
                public void onFailure(Call<Delmodels> call, Throwable t) {
//                    AToast(getString(R.string.error_net));
                    toast.setVisibility(View.VISIBLE);
                    toast.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
                }
            });
        }
    };

    private ClickCallbackListner callbackListner3 = new ClickCallbackListner() {
        @Override
        public void callback(final String repo, String phone) {
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
            seatobject = new Seatobject(keycode, String.valueOf(repo), phone);
            Call<Callmodels> call = retrofitInterface.callWait(seatobject); //DB GET 요청
            call.enqueue(new Callback<Callmodels>() {
                @Override
                public void onResponse(Call<Callmodels> call, Response<Callmodels> response) {
                    toast.setVisibility(View.GONE);
                    assert response.body() != null;
                    if (response.body().getHeader().getMsg().equals("success")) AToast("대기번호" + repo + "번 호출이 정상적으로 처리되었습니다.");
                    else AToast(getString(R.string.error_key));
                }

                @Override
                public void onFailure(Call<Callmodels> call, Throwable t) {
//                    AToast(getString(R.string.error_net));
                    toast.setVisibility(View.VISIBLE);
                    toast.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
                }
            });
        }
    };

    private ClickCallbackListner12 callbackListner12 = new ClickCallbackListner12() {
        @Override
        public void callback12(final String repo) {
            Intent intent = new Intent(AdminActivity.this, SeatActivity11.class);
            intent.putExtra("repo", repo);
            startActivity(intent);
        }
    };

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // 로그인 부분

        recyclerView = findViewById(R.id.arc1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layoutManager = recyclerView.getLayoutManager();
        recyclerView2 = findViewById(R.id.arc2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator)animator).setSupportsChangeAnimations(false);
        }


        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        aaass = findViewById(R.id.aaass);

        toast = findViewById(R.id.toast);


        alogbt1 = findViewById(R.id.alogbt);
        alogbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("로그아웃").setMessage("정말 로그아웃 하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString("id", "");
                        editor.putString("pw", "");
                        editor.putString("type", "");
                        editor.apply();

                        Intent logout = new Intent(AdminActivity.this, LoginActivity.class);
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

        btn_admin_popup = findViewById(R.id.reserve);
        btn_admin_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popintent = new Intent(AdminActivity.this, AdminPopupActivity.class);
                startActivityForResult(popintent, 0);
            }
        });

        change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent popintent = new Intent(AdminActivity.this, ReserveChActivity.class);
                startActivityForResult(popintent, 0);
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        retrofitdb();
        retrofitdb2();

//        (new SetServer()).start();
        countCDT.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    private void retrofitdb() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        keyobject = new Keyobject(keycode);
        Call<Dbmodels> call = retrofitInterface.getDB(keyobject); // DB GET 요청
        call.enqueue(new Callback<Dbmodels>() {
            @Override
            public void onResponse(Call<Dbmodels> call, Response<Dbmodels> response) {
                toast.setVisibility(View.GONE);
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    DBAdapter = new WaitingAdapter2(response.body().getBody());
                    DBAdapter.setCallbackListner(callbackListner);
                    DBAdapter.setCallbackListner3(callbackListner3);
                    recyclerView.setAdapter(DBAdapter);
                    DBAdapter.notifyDataSetChanged();
                    if(recyclerViewState != null)
                        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                }
                else
                    AToast(getString(R.string.error_key));
            }

            @Override
            public void onFailure(Call<Dbmodels> call, Throwable t) {
//                AToast("WIFI 연결을 확인해 주세요, 통신에 문제가 있습니다.");
                toast.setVisibility(View.VISIBLE);
                toast.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
            }
        });
    }

    private void retrofitdb2() {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        String getTime = String.valueOf(Integer.parseInt(simpleDate.format(mDate)));
        String yyyy = getTime.substring(0,4);
        String MM = getTime.substring(4,6);
        String dd = getTime.substring(6,8);
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        TodayReobject todayReobject = new TodayReobject(keycode,yyyy+"-"+MM+"-"+dd);
        Call<Relistmodels> call = retrofitInterface.relist2(todayReobject); //DB GET 요청
        call.enqueue(new Callback<Relistmodels>() {
            @Override
            public void onResponse(Call<Relistmodels> call, Response<Relistmodels> response) {
                toast.setVisibility(View.GONE);
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    waitingAdapterAdmin = new WaitingAdapterAdmin(getContext(),response.body().getBody());
                    waitingAdapterAdmin.setCallbackListner(callbackListner12);
                    recyclerView2.setAdapter(waitingAdapterAdmin);
                    waitingAdapterAdmin.notifyDataSetChanged();
                    if(recyclerViewState2 != null)
                        recyclerView2.getLayoutManager().onRestoreInstanceState(recyclerViewState2);
                }else {
                    AToast("key값 오류 입니다.");
                }

            }

            @Override
            public void onFailure(Call<Relistmodels> call, Throwable t) {
//                AToast("WIFI 연결을 확인해 주세요, 통신에 문제가 있습니다.");
                toast.setVisibility(View.VISIBLE);
                toast.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
            }
        });
    }

    CountDownTimer countCDT = new CountDownTimer(1 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
        }
        public void onFinish() {
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            recyclerViewState2 = recyclerView2.getLayoutManager().onSaveInstanceState();
            retrofitdb();
            retrofitdb2();
            countCDT.start();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                AToast("정상적으로 예약 되었습니다.");
//                toast.setVisibility(View.GONE);
                break;
            case 2:
                AToast("key값 오류 입니다.");
                break;
            case 3:
//                AToast("WIFI 연결을 확인해 주세요, 통신에 문제가 있습니다.");
                toast.setVisibility(View.VISIBLE);
                toast.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        long now = System.currentTimeMillis();

        long result = now - Back_Key_Before_Time;

        if(result < 2000)
        {
            finish();
        }
        else
        {
            AToast("뒤로가기 버튼을 한번 더 누르면 종료합니다.");
            Back_Key_Before_Time = System.currentTimeMillis();
        }
    }
}