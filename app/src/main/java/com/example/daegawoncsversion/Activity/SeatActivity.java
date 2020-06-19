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

import com.example.daegawoncsversion.Fragment.TabFragment2;
import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Tablemodels;
import com.example.daegawoncsversion.Models.Tlistmodels;
import com.example.daegawoncsversion.Object.TableObject;
import com.example.daegawoncsversion.Object.TlistObject;
import com.example.daegawoncsversion.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class SeatActivity extends BaseActivity{

    Retrofit retrofit;
    JSONArray array, seatinfo;
    JSONObject object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10,
            object11, object12, object13, object14, object15, object16, object17, object18, object19, object20,
            object21, object22, object23, object24, object25, object26, object27, object99;
    TextView bbtt1,toast;
    Button sbt1;
    String waitingnum, phone;
    int num;
    int tcheck = 0;
    int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18,c19 = 0;
    int f2c1, f2c2, f2c3, f2c4, f2c5, f2c6, f2c7, f2c8;
    String bookingcd;
    TlistObject tlistObject;
    ArrayList<String> list;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

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

        list = new ArrayList<>();

        Intent intent = getIntent();
//        waitingnum = intent.getStringExtra("repo");
        bookingcd = intent.getStringExtra("repo");
        getTname();

        toast = findViewById(R.id.toast);

        sbt1 = findViewById(R.id.sbt1);
        sbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void getTname() {
        {
            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
            tlistObject = new TlistObject(bookingcd);
            Call<List<Tlistmodels>> call = retrofitInterface.tlist(tlistObject); // DB GET 요청
            call.enqueue(new Callback<List<Tlistmodels>>() {
                @Override
                public void onResponse(Call<List<Tlistmodels>> call, Response<List<Tlistmodels>> response) {
                    assert response.body() != null;
                    int i = response.body().size();
                    for (int t=0;t<i;t++){
                        list.add(response.body().get(t).getTname());
                    }
                    final ViewPager viewPager = findViewById(R.id.pager);
                    final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(adapter);

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

                @Override
                public void onFailure(Call<List<Tlistmodels>> call, Throwable t) {
                    AToast("   통신에 문제가 있습니다.   \n WIFI 연결을 확인해 주세요."+t.getMessage());
                }
            });
        }
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
                    TabFragment2 fragment2 = new TabFragment2();
                    Bundle bundle2 = new Bundle();
                    bundle2.putStringArrayList("list",list);
                    fragment2.setArguments(bundle2);
                    return fragment2;
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