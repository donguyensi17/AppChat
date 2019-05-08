package com.example.demochat;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    RecyclerView mRecyclerView;
    AdapterMessage mMessAdapter;
    List<String> data;

    private Button btnSent;
    private EditText edtInput;

    private Socket socket;
    private String ip = "172.16.13.75";
    private int port = 8888;

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

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


    public class TakeData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String mess = strings[0];
            String messIncoming = "";

            try {

                if (socket == null) {
                    socket = new Socket(ip, port);
                    setOpenServer();
                }


                bufferedWriter.write(mess);
                bufferedWriter.newLine();
                bufferedWriter.flush();


                messIncoming = bufferedReader.readLine();

                if (mess.equals("exit")) {
                    setCloseServer();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return messIncoming;
        }

        private void setOpenServer() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }

        private void setCloseServer() {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
            }

        }
    }
}
