package com.example.daegawoncsversion.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Reserveupmodels;
import com.example.daegawoncsversion.Models.Seatschmodels;
import com.example.daegawoncsversion.Object.ReservechObject;
import com.example.daegawoncsversion.Object.Seatschobject;
import com.example.daegawoncsversion.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminPopupChActivity extends BaseActivity {

    ReservechObject reservechObject;
    Retrofit retrofit;
    EditText chinput_phone, chinput_date, chinput_name;
    TextView chpeople_count, chseatselect, spinner;
    Button btn_close, btn_up, btn_down;
    int y = 0, m = 0, d = 0, h = 0, mi = 0;
    int ch = 0;
    int count, cnt;
    String save_date;
    Button bt_time, bt_seat, seatchoice, seatdelete, chbtn_add;
    ArrayList arrayList, arrayList2, arrayList3, seatlist;
    ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3;
    Spinner spinner2;
    int position1, seat1, seat2;
    ArrayList<String> seatList, tname3;
    int seatcheck = 0;
    String em, ed, eh, emi;
    SharedPreferences loginInfo;
    SharedPreferences.Editor editor;
    String username, userphone, bookingCd, visit, tname;
    String[] tname2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_popupch);

        chinput_date = findViewById(R.id.chinput_date);
        chinput_date.setClickable(false);
        chinput_date.setFocusable(false);
        chinput_name = findViewById(R.id.chinput_name);
        chinput_phone = findViewById(R.id.chinput_phone);
        chseatselect = findViewById(R.id.chseatselect);
        chpeople_count = findViewById(R.id.chinput_person);
        seatchoice = findViewById(R.id.chseatchoice);
        seatdelete = findViewById(R.id.chseatdelete);
        btn_close = findViewById(R.id.btn_close);
        loginInfo = getSharedPreferences("setting", 0);
        editor = loginInfo.edit();

        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        chinput_name.setText(username);
        userphone = intent.getStringExtra("phone");
        chinput_phone.setText(userphone);
        bookingCd = intent.getStringExtra("bookingCd");
        cnt = Integer.parseInt(intent.getStringExtra("cnt"));
        chpeople_count.setText(cnt + "명");
        visit = intent.getStringExtra("visit");
        save_date = visit;
        String chy = visit.substring(0, 4);
        String chm = visit.substring(5, 7);
        String chd = visit.substring(8, 10);
        String chh = visit.substring(11, 13);
        String chmm = visit.substring(14, 16);
        chinput_date.setText(chy + "년" + chm + "월" + chd + "일" + chh + "시" + chmm + "분");
        tname = intent.getStringExtra("tname");
        tname = tname.replace("[", "");
        tname = tname.replace("]", "");
        tname = tname.replace(" ", "");
        tname2 = tname.split(",");
        tname3 = new ArrayList<>();
        seatlist = new ArrayList<>();
        seatList = new ArrayList<>();
        for (int z = 0; z < tname2.length; z++) {
            seatList.add(tname2[z]);
            switch (tname2[z]) {
                case "f2t1":
                    tname3.add("2층1호");
                    break;
                case "f2t2":
                    tname3.add("2층2호");
                    break;
                case "f2t3":
                    tname3.add("2층3호");
                    break;
                case "f2t4":
                    tname3.add("2층4호");
                    break;
                case "f2t5":
                    tname3.add("2층5호");
                    break;
                case "f2t6":
                    tname3.add("2층6호");
                    break;
                case "f2t7":
                    tname3.add("2층7호");
                    break;
                case "f2t8":
                    tname3.add("2층8호");
                    break;
            }
        }

        seatcheck = seatList.size();
//        AToast(String.valueOf(seatList.size()));

        String azsx = String.valueOf(tname3);
        azsx = azsx.replace("[", "");
        azsx = azsx.replace("]", "");
        chseatselect.setText(azsx);

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        seatlist = new ArrayList<>();
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
//        Seatschobject seatschobject = new Seatschobject(keycode, save_date);
//        Call<Seatschmodels> call = retrofitInterface.seatsch(seatschobject); // DB GET 요청
//        call.enqueue(new Callback<Seatschmodels>() {
//            @Override
//            public void onResponse(Call<Seatschmodels> call, Response<Seatschmodels> response) {
//                assert response.body() != null;
//                if (response.body().getHeader().getMsg().equals("success")) {
//                    for (int i = 0; i < response.body().getBody().size(); i++) {
//                        if (response.body().getBody().get(i).getPhone().equals("0")) {
//
//                        } else {
//                            seatlist.add(response.body().getBody().get(i).getTname());
//                        }
//                    }
//                } else {
//
//                }
//            }
//            @Override
//            public void onFailure(Call<Seatschmodels> call, Throwable t) {
//
//            }
//        });

//        AToast(String.valueOf(seatlist.get(0)));

        btn_up = findViewById(R.id.chbtn_up);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                chpeople_count.setText(cnt + "명");
            }
        });

        btn_down = findViewById(R.id.chbtn_down);
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cnt != 1) {
                    cnt--;
                    chpeople_count.setText(cnt + "명");
                }
            }
        });

        chbtn_add = findViewById(R.id.chbtn_add);
        chbtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chinput_name.getText().toString().equals("") || chinput_date.getText().toString().equals("")
                || chseatselect.getText().toString().equals("")) {
                    AToast("빈 칸을 모두 채워주세요.");
                } else if (chinput_phone.getText().toString().length() < 11) {
                    AToast("핸드폰 번호를 확인해주세요");
                } else {
                    String name = chinput_name.getText().toString();
                    String tel = chinput_phone.getText().toString();

                    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
                    reservechObject = new ReservechObject(keycode, bookingCd, name, tel, save_date, String.valueOf(cnt), seatList);
                    Call<Reserveupmodels> call = retrofitInterface.reservech(reservechObject); // DB GET 요청
                    call.enqueue(new Callback<Reserveupmodels>() {
                        @Override
                        public void onResponse(Call<Reserveupmodels> call, Response<Reserveupmodels> response) {
                            if (response.body().getHeader().getMsg().equals("success")) {
                                AToast("예약이 변경되었습니다");
                                setResult(1);
                            } else
                                AToast("예약 변경에 실패하였습니다");
                            setResult(2);
                        }

                        @Override
                        public void onFailure(Call<Reserveupmodels> call, Throwable t) {
                            AToast("통신에 문제가 있습니다.");
                            setResult(3);
                        }
                    });
                    finish();
                }
            }
        });

        chinput_phone = findViewById(R.id.chinput_phone);

        chinput_date = findViewById(R.id.chinput_date);

        bt_time = findViewById(R.id.chpicker);
        bt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        bt_seat = findViewById(R.id.chpickerseat);
        bt_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chinput_date.getText().toString().equals("")) {
                    AToast("예약 시간을 먼저 입력해 주세요");
                } else {
                    chseatflag();
                }
            }
        });
        spinner = findViewById(R.id.spinner);
        spinner.setText("2");

        arrayList3 = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            arrayList3.add(i);
        }
        seat1 = 2;
        arrayAdapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_spinner, arrayList3);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setAdapter(arrayAdapter3);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "선택된 아이템 :" + arrayList2.get(position), Toast.LENGTH_SHORT).show();
                seat2 = Integer.parseInt(String.valueOf(arrayList3.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        seatchoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seat1 == 1) {
                    switch (seat2) {
                        case 1:
                            seatadd(seat1, seat2, "t1");
                            break;
                        case 2:
                            seatadd(seat1, seat2, "t2");
                            break;
                        case 3:
                            seatadd(seat1, seat2, "t3");
                            break;
                        case 4:
                            seatadd(seat1, seat2, "t4");
                            break;
                        case 5:
                            seatadd(seat1, seat2, "t5");
                            break;
                        case 6:
                            seatadd(seat1, seat2, "t6");
                            break;
                        case 7:
                            seatadd(seat1, seat2, "t7");
                            break;
                        case 8:
                            seatadd(seat1, seat2, "t8");
                            break;
                        case 9:
                            seatadd(seat1, seat2, "t9");
                            break;
                        case 10:
                            seatadd(seat1, seat2, "t10");
                            break;
                        case 11:
                            seatadd(seat1, seat2, "t11");
                            break;
                        case 12:
                            seatadd(seat1, seat2, "t12");
                            break;
                        case 13:
                            seatadd(seat1, seat2, "t13");
                            break;
                        case 14:
                            seatadd(seat1, seat2, "t14");
                            break;
                        case 15:
                            seatadd(seat1, seat2, "t15");
                            break;
                        case 16:
                            seatadd(seat1, seat2, "t16");
                            break;
                        case 17:
                            seatadd(seat1, seat2, "t17");
                            break;
                        case 18:
                            seatadd(seat1, seat2, "t18");
                            break;
                    }
                } else if (seat1 == 2) {
                    switch (seat2) {
                        case 1:
                            seatadd(seat1, seat2, "f2t1");
                            break;
                        case 2:
                            seatadd(seat1, seat2, "f2t2");
                            break;
                        case 3:
                            seatadd(seat1, seat2, "f2t3");
                            break;
                        case 4:
                            seatadd(seat1, seat2, "f2t4");
                            break;
                        case 5:
                            seatadd(seat1, seat2, "f2t5");
                            break;
                        case 6:
                            seatadd(seat1, seat2, "f2t6");
                            break;
                        case 7:
                            seatadd(seat1, seat2, "f2t7");
                            break;
                        case 8:
                            seatadd(seat1, seat2, "f2t8");
                            break;
                    }
                } else {

                }
//                AToast(String.valueOf("seatcheck : " + seatcheck + " / 자리 : " + seatList));
            }

            private void seatadd(int a, int b, String seat) {
                int ch = 0;
                if (chinput_date.getText().toString().equals("")) {
                    AToast("예약 시간을 먼저 입력해 주세요");
                } else {
                    if (seatlist.size() != 0) {
//                        for (int i = 0; i < seatlist.size(); i++) {
//                            if (seatlist.get(i).equals(seat)) {
//                                ch = 0;
//                                break;
//                            } else {
                                ch = 1;
//                            }
//                        }
                        if (ch == 0) {
                            AToast("이미 예약된 좌석입니다.");
                            AToast(String.valueOf(seatlist));
                        } else {
                            if (seatList.size() == 0) {
                                seatList.add(seat);
                                seatc(a, b);
                                seatcheck = seatcheck + 1;
                            } else {
                                for (int i = 0; i < seatList.size(); i++) {
                                    if (seatList.get(i).equals(seat)) {
                                        AToast("이미 지정된 좌석입니다.");
                                        ch = 1;
                                    } else {
                                        ch = 0;
                                    }
                                }
                                if (ch == 0) {
                                    seatList.add(seat);
                                    seatc(a, b);
                                    seatcheck = seatcheck + 1;
                                } else {

                                }
                            }
                        }
                    } else {
                        if (seatList.size() == 0) {
                            seatList.add(seat);
                            seatc(a, b);
                            seatcheck = seatcheck + 1;
                        } else {
                            for (int i = 0; i < seatList.size(); i++) {
                                if (seatList.get(i).equals(seat)) {
                                    AToast("이미 지정된 좌석입니다.");
                                    ch = 1;
                                } else {
                                    ch = 0;
                                }
                            }
                            if (ch == 0) {
                                seatList.add(seat);
                                seatc(a, b);
                                seatcheck = seatcheck + 1;
                            } else {

                            }
                        }
                    }
                }

            }
        });

        seatdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chseatselect.getText().toString().equals("")) {

                } else if (chseatselect.length() == 4) {
                    seatcheck = seatcheck - 1;
                    seatList.remove(seatcheck);
//                    seatlist.remove()
                    chseatselect.setText(chseatselect.getText().toString().substring(0, chseatselect.getText().toString().length() - 4));
                } else {
                    seatcheck = seatcheck - 1;
                    seatList.remove(seatcheck);
                    chseatselect.setText(chseatselect.getText().toString().substring(0, chseatselect.getText().toString().length() - 6));
                }
            }
        });
    }

    private void chseatflag() {
//        seatlist = new ArrayList<>();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
        Seatschobject seatschobject = new Seatschobject(keycode, save_date);
        Call<Seatschmodels> call = retrofitInterface.seatsch(seatschobject); // DB GET 요청
        call.enqueue(new Callback<Seatschmodels>() {
            @Override
            public void onResponse(Call<Seatschmodels> call, Response<Seatschmodels> response) {
                assert response.body() != null;
                if (response.body().getHeader().getMsg().equals("success")) {
                    for (int i = 0; i < response.body().getBody().size(); i++) {
                        if (response.body().getBody().get(i).getPhone().equals("0")) {

                        } else {
                            seatlist.add(response.body().getBody().get(i).getTname());
                        }
                    }
                } else {

                }
                if (seatlist.size() == 0) {
                    AToast("예약된 좌석이 없습니다.");
                } else {
                    Intent intent = new Intent(AdminPopupChActivity.this, SeatActivity11.class);
                    intent.putExtra("tname", seatlist);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Seatschmodels> call, Throwable t) {

            }
        });
//                    Intent seat = new Intent(AdminPopupActivity.this, SeatActivity.class);
//                    startActivity(seat);
    }

    private void seatc(int a, int b) {
        if (chseatselect.getText().toString().equals("")) {
            chseatselect.setText(a + "층" + b + "호");
        } else {
            chseatselect.setText(chseatselect.getText().toString() + ", " + a + "층" + b + "호");
        }
    }

    private void showDate() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        int year = Integer.parseInt(yearFormat.format(currentTime));
        int month = Integer.parseInt(monthFormat.format(currentTime)) - 1;
        int day = Integer.parseInt(dayFormat.format(currentTime));
        if (ch == 0) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    y = year;
                    m = month + 1;
                    d = dayOfMonth;
                    ch = 1;
                    showDate();
                }
            }, year, month, day);

            datePickerDialog.setMessage("날짜를 선택해 주세요.");
            datePickerDialog.show();

        } else {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    h = hourOfDay;
                    mi = minute;
                    ch = 0;
                    int month = String.valueOf(m).length();
                    if (month == 1) {
                        em = "0" + m;
                    } else {
                        em = String.valueOf(m);
                    }
                    int day = String.valueOf(d).length();
                    if (day == 1) {
                        ed = "0" + d;
                    } else {
                        ed = String.valueOf(d);
                    }
                    int hour = String.valueOf(h).length();
                    if (hour == 1) {
                        eh = "0" + h;
                    } else {
                        eh = String.valueOf(h);
                    }
                    int minut = String.valueOf(mi).length();
                    if (minut == 1) {
                        emi = "0" + mi;
                    } else {
                        emi = String.valueOf(mi);
                    }
                    save_date = y + "-" + em + "-" + ed + " " + eh + ":" + emi + ":00";
                    chinput_date.setText(y + "년" + m + "월" + d + "일" + h + "시" + mi + "분");
                }
            }, 12, 00, false);

            timePickerDialog.setMessage("시간을 선택해 주세요.");
            timePickerDialog.show();
        }
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
}