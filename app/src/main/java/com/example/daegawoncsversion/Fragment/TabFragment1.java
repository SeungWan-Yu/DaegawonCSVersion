package com.example.daegawoncsversion.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daegawoncsversion.R;

import java.util.ArrayList;


public class TabFragment1 extends BaseFragment {

    Button t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19;
    int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19 = 0;
    ArrayList<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list = getArguments().getStringArrayList("list");
        } else;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        t1 = view.findViewById(R.id.t1);
        t2 = view.findViewById(R.id.t2);
        t3 = view.findViewById(R.id.t3);
        t4 = view.findViewById(R.id.t4);
        t5 = view.findViewById(R.id.t5);
        t6 = view.findViewById(R.id.t6);
        t7 = view.findViewById(R.id.t7);
        t8 = view.findViewById(R.id.t8);
        t9 = view.findViewById(R.id.t9);
        t10 = view.findViewById(R.id.t10);
        t11 = view.findViewById(R.id.t11);
        t12 = view.findViewById(R.id.t12);
        t13 = view.findViewById(R.id.t13);
        t14 = view.findViewById(R.id.t14);
        t15 = view.findViewById(R.id.t15);
        t16 = view.findViewById(R.id.t16);
        t17 = view.findViewById(R.id.t17);
        t18 = view.findViewById(R.id.t18);
        t19 = view.findViewById(R.id.t19);

        for (int i=0;i<list.size();i++){
            switch (list.get(i)){
                case "t1":
                    c1 = 1;
                    break;
                case "t2":
                    c2 = 2;
                    break;
                case "t3":
                    c3 = 3;
                    break;
                case "t4":
                    c4 = 4;
                    break;
                case "t5":
                    c5 = 5;
                    break;
                case "t6":
                    c6 = 6;
                    break;
                case "t7":
                    c7 = 7;
                    break;
                case "t8":
                    c8 = 8;
                    break;
                case "t9":
                    c9 = 9;
                    break;
                case "t10":
                    c10 = 10;
                    break;
                case "t11":
                    c11 = 11;
                    break;
                case "t12":
                    c12 = 12;
                    break;
                case "t13":
                    c13 = 13;
                    break;
                case "t14":
                    c14 = 14;
                    break;
                case "t15":
                    c15 = 15;
                    break;
                case "t16":
                    c16 = 16;
                    break;
                case "t17":
                    c17 = 17;
                    break;
                case "t18":
                    c18 = 18;
                    break;
                case "t19":
                    c19 = 19;
                    break;
            }
        }
        if (c1 != 0) {
            t1.setBackgroundResource(R.drawable.tablec);
            t1.setEnabled(false);
        }
        if (c2 != 0) {
            t2.setBackgroundResource(R.drawable.tablec);
            t2.setEnabled(false);
        }
        if (c3 != 0) {
            t3.setBackgroundResource(R.drawable.tablec);
            t3.setEnabled(false);
        }
        if (c4 != 0) {
            t4.setBackgroundResource(R.drawable.tablec);
            t4.setEnabled(false);
        }
        if (c5 != 0) {
            t5.setBackgroundResource(R.drawable.tablec);
            t5.setEnabled(false);
        }
        if (c6 != 0) {
            t6.setBackgroundResource(R.drawable.tablec);
            t6.setEnabled(false);
        }
        if (c7 != 0) {
            t7.setBackgroundResource(R.drawable.tablec);
            t7.setEnabled(false);
        }
        if (c8 != 0) {
            t8.setBackgroundResource(R.drawable.tablec);
            t8.setEnabled(false);
        }
        if (c9 != 0) {
            t9.setBackgroundResource(R.drawable.tablec);
            t9.setEnabled(false);
        }
        if (c10 != 0) {
            t10.setBackgroundResource(R.drawable.tablec);
            t10.setEnabled(false);
        }
        if (c11 != 0) {
            t11.setBackgroundResource(R.drawable.table2c);
            t11.setEnabled(false);
        }
        if (c12 != 0) {
            t12.setBackgroundResource(R.drawable.table2c);
            t12.setEnabled(false);
        }
        if (c13 != 0) {
            t13.setBackgroundResource(R.drawable.table2c);
            t13.setEnabled(false);
        }
        if (c14 != 0) {
            t14.setBackgroundResource(R.drawable.tablec);
            t14.setEnabled(false);
        }
        if (c15 != 0) {
            t15.setBackgroundResource(R.drawable.tablec);
            t15.setEnabled(false);
        }
        if (c16 != 0) {
            t16.setBackgroundResource(R.drawable.tablec);
            t16.setEnabled(false);
        }
        if (c17 != 0) {
            t17.setBackgroundResource(R.drawable.tablec);
            t17.setEnabled(false);
        }
        if (c18 != 0) {
            t18.setBackgroundResource(R.drawable.tablec);
            t18.setEnabled(false);
        }
        if (c19 != 0) {
            t19.setBackgroundResource(R.drawable.tablec);
            t19.setEnabled(false);
        }
        return view;
    }
}