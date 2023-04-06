package com.example.maidbooking;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterMaid extends RecyclerView.Adapter<MyAdapterMaid.MyViewHolderMaid> {

    private Context context;
    private ArrayList fname_id,lname_id,contact_id,mob_id;//New Code First Update

    public MyAdapterMaid(Context context, ArrayList fname_id, ArrayList lname_id, ArrayList contact_id,ArrayList mob_id) {
        this.context = context;
        this.fname_id = fname_id;
        this.lname_id = lname_id;
        this.contact_id = contact_id;
        this.mob_id=mob_id;
    }

    @NonNull
    @Override
    public MyViewHolderMaid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.maidentry,parent,false);
        return new MyViewHolderMaid(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMaid holder, int position) {
    holder.fname_id.setText(String.valueOf(fname_id.get(position)));
    holder.lname_id.setText(String.valueOf(lname_id.get(position)));
    holder.contact_id.setText(String.valueOf(contact_id.get(position)));
    holder.mob_id.setText(String.valueOf(mob_id.get(position)));

    holder.btn_dialer_id.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String phoneNumber=holder.mob_id.getText().toString();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            view.getContext().startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return fname_id.size();
    }

    public class MyViewHolderMaid extends RecyclerView.ViewHolder{
        TextView fname_id,lname_id,contact_id,mob_id;
        public ImageButton btn_dialer_id;
        public MyViewHolderMaid(@NonNull View itemView) {
            super(itemView);

            fname_id=itemView.findViewById(R.id.textView_fname_byrecy_maid);
            lname_id=itemView.findViewById(R.id.textView_lname_byrecy_maid);
            contact_id=itemView.findViewById(R.id.textView_contact_byrecy_maid);
            mob_id=itemView.findViewById(R.id.textView_mob_byrecy_maid);
            btn_dialer_id=itemView.findViewById(R.id.btn_dialer);
        }
    }
}
