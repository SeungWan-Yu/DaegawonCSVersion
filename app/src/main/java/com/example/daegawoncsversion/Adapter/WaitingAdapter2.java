package com.example.daegawoncsversion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daegawoncsversion.Interface.ClickCallbackListner;
import com.example.daegawoncsversion.Models.Dbmodels;
import com.example.daegawoncsversion.R;

import java.util.List;

public class WaitingAdapter2 extends RecyclerView.Adapter<WaitingAdapter2.ViewHolder> {

    private long Back_Key_Before_Time = 0;

    private List<Dbmodels.Body> mData;

    private ClickCallbackListner callbackListner;
    public void setCallbackListner(ClickCallbackListner callbackListner) {
        this.callbackListner = callbackListner;
    }

    private ClickCallbackListner callbackListner3;
    public void setCallbackListner3(ClickCallbackListner callbackListner3) {
        this.callbackListner3 = callbackListner3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rwaitingnum, rreserve, total, userphone;
        Button rbt1, dbremove, call;

        ViewHolder(View itemView) {
            super(itemView);

            rwaitingnum = itemView.findViewById(R.id.rwaitingnum);
            rreserve = itemView.findViewById(R.id.rreserve);
            rbt1 = itemView.findViewById(R.id.rbt1);
            dbremove = itemView.findViewById(R.id.dbremove);
//            total = itemView.findViewById(R.id.list_count);
            userphone = itemView.findViewById(R.id.userphone);
            call = itemView.findViewById(R.id.call);

            dbremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

//                    long now = System.currentTimeMillis();
//                    long result = now - Back_Key_Before_Time;
//
//                    if(result < 2000) {
                        if (pos != RecyclerView.NO_POSITION) {
                            Dbmodels.Body item1 = mData.get(pos);
                            callbackListner.callback(String.valueOf(item1.getIdx()),item1.getPhone());
                            mData.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), mData.size());
                        }
//                    } else Back_Key_Before_Time = System.currentTimeMillis();
                }
            });

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Dbmodels.Body item1 = mData.get(pos);
                        callbackListner3.callback(String.valueOf(item1.getIdx()),item1.getPhone());
                        notifyItemRangeChanged(getAdapterPosition(), mData.size());
                    }
                }
            });
        }
    }

    public WaitingAdapter2(List<Dbmodels.Body> list) {
        mData = list;
    }

    @NonNull
    @Override
    public WaitingAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        WaitingAdapter2.ViewHolder vh = new WaitingAdapter2.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingAdapter2.ViewHolder holder, int position) {
        Dbmodels.Body item = mData.get(position);

        holder.rwaitingnum.setText(String.valueOf(item.getIdx()));
        holder.rreserve.setText(String.valueOf(item.getCnt()));
        holder.userphone.setText((item.getPhone()).substring(7));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}