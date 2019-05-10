package com.example.demochat.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demochat.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.ViewHodel>{

    private List<String> data = new ArrayList<>();

    public AdapterMessage(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.custom_messages, viewGroup, false);
        ViewHodel viewHodel = new ViewHodel(view);
        return viewHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel viewHodel, int i) {
        viewHodel.txtMessage.setText("Server :" + data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHodel extends RecyclerView.ViewHolder {
        private TextView txtMessage;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.textview_mess);
        }
    }
}
