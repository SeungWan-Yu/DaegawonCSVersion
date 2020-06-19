package com.example.daegawoncsversion.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daegawoncsversion.R;

import java.util.ArrayList;


public class TabFragment21 extends BaseFragment {

    Button f2t1, f2t2, f2t3, f2t4, f2t5, f2t6, f2t7, f2t8;
    int f2c1= 0, f2c2= 0, f2c3= 0, f2c4= 0, f2c5= 0, f2c6= 0, f2c7= 0, f2c8 = 0;
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
        View view = inflater.inflate(R.layout.fragment_tab_fragment21, container, false);

        f2t1 = view.findViewById(R.id.tt1);
        f2t2 = view.findViewById(R.id.tt2);
        f2t3 = view.findViewById(R.id.tt3);
        f2t4 = view.findViewById(R.id.tt4);
        f2t5 = view.findViewById(R.id.tt5);
        f2t6 = view.findViewById(R.id.tt6);
        f2t7 = view.findViewById(R.id.tt7);
        f2t8 = view.findViewById(R.id.tt8);

        for (int i=0;i<list.size();i++){
            switch (list.get(i)){
                case "f2t1":
                    f2c1 = 1;
                    break;
                case "f2t2":
                    f2c2 = 1;
                    break;
                case "f2t3":
                    f2c3 = 1;
                    break;
                case "f2t4":
                    f2c4 = 1;
                    break;
                case "f2t5":
                    f2c5 = 1;
                    break;
                case "f2t6":
                    f2c6 = 1;
                    break;
                case "f2t7":
                    f2c7 = 1;
                    break;
                case "f2t8":
                    f2c8 = 1;
                    break;
            }
        }
            if (f2c1 != 0) {
                f2t1.setBackgroundResource(R.drawable.table3c);
                f2t1.setEnabled(false);
            }
            if (f2c2 != 0) {
                f2t2.setBackgroundResource(R.drawable.table3c);
                f2t2.setEnabled(false);
            }
            if (f2c3 != 0) {
                f2t3.setBackgroundResource(R.drawable.table3c);
                f2t3.setEnabled(false);
            }
            if (f2c4 != 0) {
                f2t4.setBackgroundResource(R.drawable.table3c);
                f2t4.setEnabled(false);
            }
            if (f2c5 != 0) {
                f2t5.setBackgroundResource(R.drawable.table3c);
                f2t5.setEnabled(false);
            }
            if (f2c6 != 0) {
                f2t6.setBackgroundResource(R.drawable.table4c);
                f2t6.setEnabled(false);
            }
            if (f2c7 != 0) {
                f2t7.setBackgroundResource(R.drawable.table5c);
                f2t7.setEnabled(false);
            }
            if (f2c8 != 0) {
                f2t8.setBackgroundResource(R.drawable.table5c);
                f2t8.setEnabled(false);
            }

        return view;
    }
}