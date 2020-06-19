package com.example.daegawoncsversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Interface.ClickCallbackListner123;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.R;

import java.util.ArrayList;
import java.util.List;


public class WaitingAdapterResch extends RecyclerView.Adapter<WaitingAdapterResch.ViewHolder> {

    private List<Relistmodels.Body> mData = null;

    private ClickCallbackListner123 callbackListner123;
    public void setCallbackListner(ClickCallbackListner123 callbackListner123) {
        this.callbackListner123 = callbackListner123;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userwait, username, userphone, userdate, userint, usermap;
        Button findmap;


        ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            userphone = itemView.findViewById(R.id.userphone);
            userdate = itemView.findViewById(R.id.userdate);
            userint = itemView.findViewById(R.id.userint);
            usermap = itemView.findViewById(R.id.usermap);
            usermap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Relistmodels.Body item1 = mData.get(pos);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(0,item1.getName());
                        arrayList.add(1,item1.getPhone());
                        arrayList.add(2,item1.getBookingCd());
                        arrayList.add(3,item1.getCnt());
                        arrayList.add(4,item1.getVisitDt());
                        arrayList.add(5, String.valueOf(item1.getTnames()));
                        callbackListner123.callback123(arrayList);
                        notifyItemRangeChanged(getAdapterPosition(), mData.size());
                    }
                }
            });
//            findmap = itemView.findViewById(R.id.findmap);

//            findmap.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition();
//                    if (pos != RecyclerView.NO_POSITION) {
//                        Dbmodels.Body item1 = mData.get(pos);
//                        callbackListner.callback(String.valueOf(item1.getIdx()),item1.getPhone());
//                    }
//                }
//            });
        }
    }

    public WaitingAdapterResch(List<Relistmodels.Body> list) {
        mData = list;
    }

    @NonNull
    @Override
    public WaitingAdapterResch.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item_resch, parent, false);
        WaitingAdapterResch.ViewHolder vh = new WaitingAdapterResch.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingAdapterResch.ViewHolder holder, int position) {
        Relistmodels.Body item = mData.get(position);
        holder.username.setText(item.getName());
        holder.userphone.setText((item.getPhone()).substring(7));
        holder.userdate.setText(item.getVisitDt().substring(0,16));
        holder.userint.setText(item.getCnt()+"명");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
