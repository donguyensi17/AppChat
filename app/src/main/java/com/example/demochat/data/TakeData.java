package com.example.demochat.data;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.demochat.callback.OnListenerServer;
import com.example.demochat.home.MainActivity;
import com.example.demochat.home.view.UpdateData;
import com.example.demochat.socket.ConnectServer;


public class TakeData extends AsyncTask<String, Void, String> {

    ConnectServer connectServer;
    UpdateData mUpdateData;

    public TakeData(UpdateData updateData) {
      mUpdateData = updateData;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (connectServer == null) {
            connectServer = ConnectServer.getInstance();
        }
        connectServer.getData(new OnListenerServer() {
            @Override
            public void callBackData(final String mess) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUpdateData.updateUi(mess);
                    }
                }, 0);
            }
        });
        return null;
    }
}
