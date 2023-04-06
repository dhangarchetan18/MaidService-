package com.example.maidbooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
  /*  private ArrayList fname_id,lname_id,contact_id,email_id,city_id;

    public MyAdapter(Context context, ArrayList fname_id, ArrayList lname_id, ArrayList contact_id, ArrayList email_id, ArrayList city_id) {
        this.context = context;
        this.fname_id = fname_id;
        this.lname_id = lname_id;
        this.contact_id = contact_id;
        this.email_id = email_id;
        this.city_id=city_id;
    }
*/
    private List<MyModel> data;
    public MyAdapter(List<MyModel> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyModel item = data.get(position);
    holder.fname_id.setText(String.valueOf(item.getFname()));
    holder.lname_id.setText(String.valueOf(item.getLnmae()));
    holder.contact_id.setText(String.valueOf(item.getContact()));
    holder.email_id.setText(String.valueOf(item.getEmail()));
    holder.city_id.setText(String.valueOf(item.getCity())); //new first update


    //-------------------------------New Code------------------
        holder.request_maid_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            TextView txt_view=holder.email_id.findViewById(R.id.textView_email_byrecy);
            String txt_view_string=txt_view.getText().toString();
            IntentValueHol intentValueHol=new IntentValueHol();
            String fname_string=intentValueHol.getFirst_name_value();
            String lnmae_string=intentValueHol.getLast_name_value();
            String contact_string=intentValueHol.getEmail_value();
            String mob_string=intentValueHol.getMob_value();  //new first update
         //   String city_string=intentValueHol.

               // Toast.makeText(context, " "+txt_view_string+" "+fname_string+" "+lnmae_string+" "+contact_string, Toast.LENGTH_SHORT).show();

                //---------Database insert code--------------
                DBHelperRequest dbHelperRequest;
                dbHelperRequest=new DBHelperRequest(view.getContext());

              Boolean checkuseral=dbHelperRequest.checkuser(txt_view_string,contact_string);
              if (checkuseral==false)
              {
                  Boolean insert = dbHelperRequest.insertuserdetails(txt_view_string, fname_string, lnmae_string, contact_string,mob_string);
                  if (insert == true) {
                      Toast.makeText(view.getContext(), "Requested to maid Successfully", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(view.getContext(), "Data not Inserted", Toast.LENGTH_SHORT).show();
                  }
              }
              else
              {
                  Toast.makeText(view.getContext(), "Already Request to Maid", Toast.LENGTH_SHORT).show();
              }
            }
        });
        // ---------------------------End Code------------------
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fname_id,lname_id,contact_id,email_id,city_id;
        //------------------------New Code--------------------
        Button request_maid_id;
        //------------------------End Code--------------------
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fname_id=itemView.findViewById(R.id.textView_fname_byrecy);
            lname_id=itemView.findViewById(R.id.textView_lname_byrecy);
            contact_id=itemView.findViewById(R.id.textView_contact_byrecy);
            email_id=itemView.findViewById(R.id.textView_email_byrecy);
            //------------------New Code---------------
            request_maid_id=itemView.findViewById(R.id.request_maid_button);
            //------------------End Code----------------
            //------------------New Code First Update----------------
            city_id=itemView.findViewById(R.id.textView_city_byrecy);

        }
    }
}
