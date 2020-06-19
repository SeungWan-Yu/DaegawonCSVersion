package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Adapter.WaitingAdapterResch;
import com.example.daegawoncsversion.Interface.ClickCallbackListner123;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.Object.RePhoneObject;
import com.example.daegawoncsversion.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReserveSchActivity extends BaseActivity {

    Keyobject keyobject;
    RecyclerView recyclerView;
    Retrofit retrofit;
    WaitingAdapterResch waitingAdapterResch;
    int repo1;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    Parcelable recyclerViewState;
    String reservech;
    Handler hd;
    TextView toast5;

    private long Back_Key_Before_Time = 0;

    private ClickCallbackListner123 callbackListner123 = new ClickCallbackListner123() {
        @Override
        public void callback123(List<String> info) {
            chreserve(info);
        }
    };

    private void chreserve(List<String> info) {
        Intent popintent = new Intent(ReserveSchActivity.this, AdminPopupChActivity.class);
        popintent.putExtra("name", info.get(0));
        popintent.putExtra("phone", info.get(1));
        popintent.putExtra("bookingCd", info.get(2));
        popintent.putExtra("cnt", info.get(3));
        popintent.putExtra("visit", info.get(4));
        popintent.putExtra("tname", info.get(5));
        startActivityForResult(popintent, 1);
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservesch);

        hd = new Handler();

        countCDT.start();

        //SharedPreferences 정의
        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        Intent intent = getIntent();
        reservech = intent.getStringExtra("reservech");
        recyclerView = findViewById(R.id.arc10);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toast5 = findViewById(R.id.toast5);

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        retrofitdb();
    }

    private void retrofitdb() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        RePhoneObject rePhoneObject = new RePhoneObject(keycode, reservech);
        Call<Relistmodels> call = retrofitInterface.relist3(rePhoneObject); //DB GET 요청
        call.enqueue(new Callback<Relistmodels>() {
            @Override
            public void onResponse(Call<Relistmodels> call, Response<Relistmodels> response) {
                toast5.setVisibility(View.GONE);
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    waitingAdapterResch = new WaitingAdapterResch(response.body().getBody());
                    waitingAdapterResch.setCallbackListner(callbackListner123);
                    recyclerView.setAdapter(waitingAdapterResch);
                    waitingAdapterResch.notifyDataSetChanged();
                    if (recyclerViewState != null)
                        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
                } else
                    AToast(getString(R.string.error_key));
            }

            @Override
            public void onFailure(Call<Relistmodels> call, Throwable t) {
//                AToast(getString(R.string.error_net));
                toast5.setVisibility(View.VISIBLE);
                toast5.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
            }
        });
    }
    CountDownTimer countCDT = new CountDownTimer(1 * 1000, 1000) {
        public void onTick(long millisUntilFinished) {
        }

        public void onFinish() {
            recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
            retrofitdb();
            countCDT.start();
        }
    };

    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onActivityResult(int reqeustCode, int resultCode, Intent data) {
        super.onActivityResult(reqeustCode, resultCode, data);
        switch (resultCode) {
            case 0:

                break;
            case 1:
                toast5.setVisibility(View.GONE);
                AToast("예약이 정상적으로 변경되었습니다.");
                retrofitdb();
                hd.postDelayed(new handler1(), 1500);
                break;
            case 2:
                AToast("key값이 잘못되었습니다.");
                break;
            case 3:
//                AToast("통신에 문제가 있습니다.");
                toast5.setVisibility(View.VISIBLE);
                toast5.setText("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요.");
                break;
        }

    }
    private class handler1 implements Runnable {
        public void run() {
            finish();
        }
    }
}