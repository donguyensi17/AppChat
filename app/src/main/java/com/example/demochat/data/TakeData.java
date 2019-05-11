package com.example.demochat.data;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.demochat.callback.OnListenerServer;
import com.example.demochat.home.view.UpdateData;
import com.example.demochat.socket.ConnectServer;


public class TakeData extends AsyncTask<String, String, String> {

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
                publishProgress(mess);
            }
        });
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        mUpdateData.updateUi(values[0]);
    }
}
