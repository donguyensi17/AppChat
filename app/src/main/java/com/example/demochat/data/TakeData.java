package com.example.demochat.data;

import android.os.AsyncTask;

import com.example.demochat.socket.ConnectServer;


public class TakeData extends AsyncTask<String, Void, String> {

    ConnectServer connectServer;

    @Override
    protected String doInBackground(String... strings) {
        String msgClient = strings[0];
        if (connectServer == null) {
            connectServer = ConnectServer.getInstance();
        }


        String msgReceiver = connectServer.getData(msgClient);
        return msgReceiver;
    }


}
