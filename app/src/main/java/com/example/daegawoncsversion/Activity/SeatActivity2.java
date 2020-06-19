package com.example.daegawoncsversion.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.daegawoncsversion.Fragment.TabFragment3;
import com.example.daegawoncsversion.Fragment.TabFragment4;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Tablemodels;
import com.example.daegawoncsversion.Object.TableObject;
import com.example.daegawoncsversion.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class SeatActivity2 extends BaseActivity{

    Retrofit retrofit;
    JSONArray array, seatinfo;
    JSONObject object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10,
            object11, object12, object13, object14, object15, object16, object17, object18, object19, object20,
            object21, object22, object23, object24, object25, object26, object27, object99;
    TextView bbtt1;
    Button s2bt1;
    String waitingnum, phone;
    int num;
    int tcheck = 0;
    int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18,c19 = 0;
    int f2c1, f2c2, f2c3, f2c4, f2c5, f2c6, f2c7, f2c8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat2);

        Display dp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (dp.getWidth() * 0.85);
        int height = (int) (dp.getHeight() * 0.95);

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        bbtt1 = findViewById(R.id.bbtt1);

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl) // 여의 시스템 PC
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();

        Intent intent = getIntent();
        waitingnum = intent.getStringExtra("repo");

        s2bt1 = findViewById(R.id.s2bt1);
        s2bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabLayout tabLayout = findViewById(R.id.s2tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.s2pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private void seatrequest(String table, String value) {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        final TableObject tableObject = new TableObject(keycode,table,value,String.valueOf(phone));
        Call<Tablemodels> call = retrofitInterface.seatrequest(tableObject); // DB GET 요청
        call.enqueue(new Callback<Tablemodels>() {
            @Override
            public void onResponse(Call<Tablemodels> call, Response<Tablemodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) tcheck = tcheck+1;
                else;
            }

            @Override
            public void onFailure(Call<Tablemodels> call, Throwable t) {
                AToast(getString(R.string.error_net));
            }
        });
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TabFragment3 fragment3 = new TabFragment3();
                    return fragment3;
                case 1:
                    TabFragment4 fragment4 = new TabFragment4();
                    return fragment4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}