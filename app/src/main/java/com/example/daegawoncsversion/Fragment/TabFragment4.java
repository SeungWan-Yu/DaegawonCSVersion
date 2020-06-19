package com.example.daegawoncsversion.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daegawoncsversion.Interface.RetrofitInterface;
import com.example.daegawoncsversion.Models.Tablemodels;
import com.example.daegawoncsversion.Object.Keyobject;
import com.example.daegawoncsversion.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabFragment4 extends BaseFragment {

    private OnTablePickerSetListner onTablePickerSetListner;
    Button f2t1, f2t2, f2t3, f2t4, f2t5, f2t6, f2t7, f2t8;
    int f2c1, f2c2, f2c3, f2c4, f2c5, f2c6, f2c7, f2c8 = 0;
    int tablenum, num;
    Retrofit retrofit;
    JSONArray array;
    JSONObject object0, object1, object2, object3, object4, object5, object6, object7, object8, object9,
            object10, object11, object12, object13, object14, object15, object16, object17, object18, object19,
            object20, object21, object22, object23, object24, object25, object26;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tablenum = getArguments().getInt("repo", 99);
            num = getArguments().getInt("num", 99);
        } else;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment4, container, false);

        f2t1 = view.findViewById(R.id.tt1);
        f2t2 = view.findViewById(R.id.tt2);
        f2t3 = view.findViewById(R.id.tt3);
        f2t4 = view.findViewById(R.id.tt4);
        f2t5 = view.findViewById(R.id.tt5);
        f2t6 = view.findViewById(R.id.tt6);
        f2t7 = view.findViewById(R.id.tt7);
        f2t8 = view.findViewById(R.id.tt8);

        retrofit = new Retrofit.Builder()
                .baseUrl(ipurl) // 여의 시스템 PC
                .addConverterFactory(GsonConverterFactory.create()) // Gson 사용
                .build();
        seatlist2();

        return view;
    }

    private void seatlist2() {
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class); //RetrofitInterface 연결
        final Keyobject keyobject = new Keyobject(keycode);
        Call<Tablemodels> call = retrofitInterface.seatlist(keyobject); //DB GET 요청
        call.enqueue(new Callback<Tablemodels>() {
            @Override
            public void onResponse(Call<Tablemodels> call, Response<Tablemodels> response) {
                assert response.body() != null;
                List<Tablemodels.Body> asd = response.body().getBody();
                if (response.body().getHeader().getMsg().equals("success")) {
                    array = new JSONArray();
                    for (int a = 0; a < asd.size(); a++) {
                        switch (a) {
                            case 0:
                                object0 = new JSONObject();
                                try {
                                    object0.put("table", asd.get(a).getTname());
                                    object0.put("value", asd.get(a).getIdx());
                                    array.put(object0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                object1 = new JSONObject();
                                try {
                                    object1.put("table", asd.get(a).getTname());
                                    object1.put("value", asd.get(a).getIdx());
                                    array.put(object1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                object2 = new JSONObject();
                                try {
                                    object2.put("table", asd.get(a).getTname());
                                    object2.put("value", asd.get(a).getIdx());
                                    array.put(object2);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                object3 = new JSONObject();
                                try {
                                    object3.put("table", asd.get(a).getTname());
                                    object3.put("value", asd.get(a).getIdx());
                                    array.put(object3);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                object4 = new JSONObject();
                                try {
                                    object4.put("table", asd.get(a).getTname());
                                    object4.put("value", asd.get(a).getIdx());
                                    array.put(object4);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                object5 = new JSONObject();
                                try {
                                    object5.put("table", asd.get(a).getTname());
                                    object5.put("value", asd.get(a).getIdx());
                                    array.put(object5);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 6:
                                object6 = new JSONObject();
                                try {
                                    object6.put("table", asd.get(a).getTname());
                                    object6.put("value", asd.get(a).getIdx());
                                    array.put(object6);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 7:
                                object7 = new JSONObject();
                                try {
                                    object7.put("table", asd.get(a).getTname());
                                    object7.put("value", asd.get(a).getIdx());
                                    array.put(object7);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 8:
                                object8 = new JSONObject();
                                try {
                                    object8.put("table", asd.get(a).getTname());
                                    object8.put("value", asd.get(a).getIdx());
                                    array.put(object8);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 9:
                                object9 = new JSONObject();
                                try {
                                    object9.put("table", asd.get(a).getTname());
                                    object9.put("value", asd.get(a).getIdx());
                                    array.put(object9);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 10:
                                object10 = new JSONObject();
                                try {
                                    object10.put("table", asd.get(a).getTname());
                                    object10.put("value", asd.get(a).getIdx());
                                    array.put(object10);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 11:
                                object11 = new JSONObject();
                                try {
                                    object11.put("table", asd.get(a).getTname());
                                    object11.put("value", asd.get(a).getIdx());
                                    array.put(object11);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 12:
                                object12 = new JSONObject();
                                try {
                                    object12.put("table", asd.get(a).getTname());
                                    object12.put("value", asd.get(a).getIdx());
                                    array.put(object12);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 13:
                                object13 = new JSONObject();
                                try {
                                    object13.put("table", asd.get(a).getTname());
                                    object13.put("value", asd.get(a).getIdx());
                                    array.put(object13);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 14:
                                object14 = new JSONObject();
                                try {
                                    object14.put("table", asd.get(a).getTname());
                                    object14.put("value", asd.get(a).getIdx());
                                    array.put(object14);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 15:
                                object15 = new JSONObject();
                                try {
                                    object15.put("table", asd.get(a).getTname());
                                    object15.put("value", asd.get(a).getIdx());
                                    array.put(object15);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 16:
                                object16 = new JSONObject();
                                try {
                                    object16.put("table", asd.get(a).getTname());
                                    object16.put("value", asd.get(a).getIdx());
                                    array.put(object16);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 17:
                                object17 = new JSONObject();
                                try {
                                    object17.put("table", asd.get(a).getTname());
                                    object17.put("value", asd.get(a).getIdx());
                                    array.put(object17);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 18:
                                object18 = new JSONObject();
                                try {
                                    object18.put("table", asd.get(a).getTname());
                                    object18.put("value", asd.get(a).getIdx());
                                    array.put(object18);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 19:
                                object19 = new JSONObject();
                                try {
                                    object19.put("table", asd.get(a).getTname());
                                    object19.put("value", asd.get(a).getIdx());
                                    array.put(object19);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 20:
                                object20 = new JSONObject();
                                try {
                                    object20.put("table", asd.get(a).getTname());
                                    object20.put("value", asd.get(a).getIdx());
                                    array.put(object20);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 21:
                                object21 = new JSONObject();
                                try {
                                    object21.put("table", asd.get(a).getTname());
                                    object21.put("value", asd.get(a).getIdx());
                                    array.put(object21);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 22:
                                object22 = new JSONObject();
                                try {
                                    object22.put("table", asd.get(a).getTname());
                                    object22.put("value", asd.get(a).getIdx());
                                    array.put(object22);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 23:
                                object23 = new JSONObject();
                                try {
                                    object23.put("table", asd.get(a).getTname());
                                    object23.put("value", asd.get(a).getIdx());
                                    array.put(object23);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 24:
                                object24 = new JSONObject();
                                try {
                                    object24.put("table", asd.get(a).getTname());
                                    object24.put("value", asd.get(a).getIdx());
                                    array.put(object24);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 25:
                                object25 = new JSONObject();
                                try {
                                    object25.put("table", asd.get(a).getTname());
                                    object25.put("value", asd.get(a).getIdx());
                                    array.put(object25);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 26:
                                object26 = new JSONObject();
                                try {
                                    object26.put("table", asd.get(a).getTname());
                                    object26.put("value", asd.get(a).getIdx());
                                    array.put(object26);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                    try {
                        for (int a = 0; a < array.length(); a++) {
                            JSONObject jsonObject = array.getJSONObject(a);
                            switch (a) {
                                case 0:
                                    f2c1 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 1:
                                    f2c2 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 2:
                                    f2c3 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 3:
                                    f2c4 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 4:
                                    f2c5 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 5:
                                    f2c6 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 6:
                                    f2c7 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                                case 7:
                                    f2c8 = Integer.parseInt(String.valueOf(jsonObject.get("value")));
                                    break;
                            }
                        }
                    } catch (JSONException e) { }

                        if (f2c1 != 0) {
                            f2t1.setBackgroundResource(R.drawable.table3c);
                            f2t1.setEnabled(false);
                        } if (f2c2 != 0) {
                            f2t2.setBackgroundResource(R.drawable.table3c);
                            f2t2.setEnabled(false);
                        } if (f2c3 != 0) {
                            f2t3.setBackgroundResource(R.drawable.table3c);
                            f2t3.setEnabled(false);
                        } if (f2c4 != 0) {
                            f2t4.setBackgroundResource(R.drawable.table3c);
                            f2t4.setEnabled(false);
                        } if (f2c5 != 0) {
                            f2t5.setBackgroundResource(R.drawable.table3c);
                            f2t5.setEnabled(false);
                        } if (f2c6 != 0) {
                            f2t6.setBackgroundResource(R.drawable.table4c);
                            f2t6.setEnabled(false);
                        } if (f2c7 != 0) {
                            f2t7.setBackgroundResource(R.drawable.table5c);
                            f2t7.setEnabled(false);
                        } if (f2c8 != 0) {
                            f2t8.setBackgroundResource(R.drawable.table5c);
                            f2t8.setEnabled(false);
                        }

                } else;
            }

            @Override
            public void onFailure(Call<Tablemodels> call, Throwable t) {
                AToast(t.getMessage());
            }
        });
    }

    public interface OnTablePickerSetListner {
        void onTablePickerSet(String tnum, int table);
    }
}