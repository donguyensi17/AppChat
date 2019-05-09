package com.example.demochat.home;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demochat.data.TakeData;
import com.example.demochat.home.adapter.AdapterMessage;
import com.example.demochat.R;
import com.example.demochat.socket.ConnectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterMessage mMessAdapter;
    private List<String> data;

    private Button btnSent;
    private EditText edtInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        evenButton();
    }

    private void evenButton() {
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtInput.getText().toString();
                if(message.equals("exit")){
                    edtInput.setEnabled(false);
                    return;
                }


                if (message.length() > 0) {

                    try {
                        String msg = new TakeData().execute(message).get();

                        data.add(message);
                        data.add(msg);
                        mMessAdapter.notifyDataSetChanged();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.RecyslerView);
        btnSent = findViewById(R.id.Button);
        edtInput = findViewById(R.id.Edittex);

        data = new ArrayList<>();

        mMessAdapter = new AdapterMessage(data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMessAdapter);

    }
}
