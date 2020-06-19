package com.example.daegawoncsversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Interface.ClickCallbackListner12;
import com.example.daegawoncsversion.Models.Relistmodels;
import com.example.daegawoncsversion.R;

import java.util.List;


public class WaitingAdapterUser extends RecyclerView.Adapter<WaitingAdapterUser.ViewHolder> {

    private List<Relistmodels.Body> mData = null;

    private ClickCallbackListner12 callbackListner12;
    public void setCallbackListner(ClickCallbackListner12 callbackListner12) {
        this.callbackListner12 = callbackListner12;
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
                        callbackListner12.callback12(item1.getBookingCd());
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

    public WaitingAdapterUser(List<Relistmodels.Body> list) {
        mData = list;
    }

    @NonNull
    @Override
    public WaitingAdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item_user, parent, false);
        WaitingAdapterUser.ViewHolder vh = new WaitingAdapterUser.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingAdapterUser.ViewHolder holder, int position) {
        Relistmodels.Body item = mData.get(position);
        holder.username.setText(item.getName());
        holder.userphone.setText((item.getPhone()).substring(7));
        holder.userdate.setText(item.getVisitDt().substring(10,13)+"시"+item.getVisitDt().substring(14,16)+"분");
        holder.userint.setText(item.getCnt()+"명");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
