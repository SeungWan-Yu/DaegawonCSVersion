package com.example.daegawoncsversion.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.daegawoncsversion.Models.Reservemodels;
import com.example.daegawoncsversion.Models.Seatschmodels;
import com.example.daegawoncsversion.Object.ReserveObject;
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

public class AdminPopupActivity extends BaseActivity {

    ReserveObject reserveObject;
    Retrofit retrofit;
    EditText input_phone, input_date, input_name;
    TextView people_count, seatselect;
    Button btn_close, btn_up, btn_down;
    int y = 0, m = 0, d = 0, h = 0, mi = 0;
    int ch = 0;
    int count = 1;
    String save_date;
    Button bt_time, bt_seat, seatchoice, seatdelete, btn_add;
    ArrayList arrayList, arrayList2, arrayList3, seatlist;
    ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3;
    Spinner spinner2;
    TextView spinner;
    int position1, seat1, seat2;
    ArrayList<String> seatList;
    int seatcheck = 0;
    String em, ed, eh, emi;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_popup);

        input_date = findViewById(R.id.input_date);
        input_date.setClickable(false);
        input_date.setFocusable(false);
        input_name = findViewById(R.id.input_name);
        input_phone = findViewById(R.id.input_phone);

        seatlist = new ArrayList<>();
        seatList = new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl)
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();

        seatselect = findViewById(R.id.seatselect);
        people_count = findViewById(R.id.input_person);

        seatchoice = findViewById(R.id.seatchoice);
//        seatchoice.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
        seatdelete = findViewById(R.id.seatdelete);

        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_up = findViewById(R.id.btn_up);
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                people_count.setText(count + "명");
            }
        });

        btn_down = findViewById(R.id.btn_down);
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 1) count--;
                people_count.setText(count + "명");
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_name.getText().toString().equals("") || input_date.getText().toString().equals("")
                || seatselect.getText().toString().equals("")) {
                    AToast("빈 칸을 모두 채워주세요.");
                } else if (input_phone.getText().toString().length() < 11) {
                    AToast("핸드폰 번호를 확인해주세요");
                } else {
                    String name = input_name.getText().toString();
                    String tel = input_phone.getText().toString();

                    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); // RetrofitInterface 연결
                    reserveObject = new ReserveObject(keycode, name, tel, save_date, String.valueOf(count), seatList);
                    Call<Reservemodels> call = retrofitInterface.reserve(reserveObject); // DB GET 요청
                    call.enqueue(new Callback<Reservemodels>() {
                        @Override
                        public void onResponse(Call<Reservemodels> call, Response<Reservemodels> response) {
                            if (response.body().getHeader().getMsg().equals("success")) {
                                setResult(1);
                            } else
                                setResult(2);
                        }

                        @Override
                        public void onFailure(Call<Reservemodels> call, Throwable t) {
                            setResult(3);
                        }
                    });
                    finish();
                }
            }
        });

        input_phone = findViewById(R.id.input_phone);
        input_phone.setText("010");

        input_date = findViewById(R.id.input_date);

        bt_time = findViewById(R.id.picker);
        bt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        bt_seat = findViewById(R.id.pickerseat);
        bt_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_date.getText().toString().equals("")) {
                    AToast("예약 시간을 먼저 입력해 주세요");
                } else {
                    seatflag();
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
//                AToast(String.valueOf("seatcheck : " + seatcheck + " / 자리 : " + seatList));
            }

            private void seatadd(int a, int b, String seat) {
                int ch = 0;
                if (input_date.getText().toString().equals("")) {
                    AToast("예약 시간을 먼저 입력해 주세요");
                } else {
                    if (seatlist.size() != 0) {
                        for (int i = 0; i < seatlist.size(); i++) {
                            if (seatlist.get(i).equals(seat)) {
                                ch = 1;
                                break;
                            } else {
                                ch = 1;
                            }
                        }
                        if (ch == 0) {
                            AToast("이미 예약된 좌석입니다.");
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
                if (seatselect.getText().toString().equals("")) {

                } else if (seatselect.length() == 4) {
                    seatcheck = seatcheck - 1;
                    seatList.remove(seatcheck);
                    seatselect.setText(seatselect.getText().toString().substring(0, seatselect.getText().toString().length() - 4));
                } else {
                    seatcheck = seatcheck - 1;
                    seatList.remove(seatcheck);
                    seatselect.setText(seatselect.getText().toString().substring(0, seatselect.getText().toString().length() - 6));
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void seatflag() {
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
                    Intent intent = new Intent(AdminPopupActivity.this, SeatActivity11.class);
                    intent.putExtra("tname", seatlist);
                    startActivity(intent);
                }
                seatchoice.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                seatchoice.setBackgroundColor(Color.rgb(255,230,0));
            }

            @Override
            public void onFailure(Call<Seatschmodels> call, Throwable t) {

            }
        });
//                    Intent seat = new Intent(AdminPopupActivity.this, SeatActivity.class);
//                    startActivity(seat);

    }

    private void seatchecking() {
        seatlist = new ArrayList<>();
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
                } else
                    AToast(String.valueOf(seatlist));
            }

            @Override
            public void onFailure(Call<Seatschmodels> call, Throwable t) {

            }
        });
//                    Intent seat = new Intent(AdminPopupActivity.this, SeatActivity.class);
//                    startActivity(seat);
    }

    private void seatc(int a, int b) {
        if (seatselect.getText().toString().equals("")) {
            seatselect.setText(a + "층" + b + "호");
        } else {
            seatselect.setText(seatselect.getText().toString() + ", " + a + "층" + b + "호");
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
                    input_date.setText(y + "년" + m + "월" + d + "일" + h + "시" + mi + "분");
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