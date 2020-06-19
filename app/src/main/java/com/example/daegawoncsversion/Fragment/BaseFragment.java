package com.example.daegawoncsversion.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.daegawoncsversion.R;


public class BaseFragment extends Fragment {

    public String ipurl, keycode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ipurl = "http://106.10.39.137:48701";
        keycode = "0783cc9efa974aa598dc2ec2c9";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        return view;
    }

    public void AToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}