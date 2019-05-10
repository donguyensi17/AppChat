package com.example.demochat.socket;

import com.example.demochat.callback.OnListenerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectServer {
    private Socket socket;
    private String ip = "172.16.13.75";
    private int port = 8888;

    private BufferedReader bufferedReader;

    private static ConnectServer connectServer;

    private ConnectServer() {

    }

    public static ConnectServer getInstance(){
        if(connectServer == null){
            connectServer = new ConnectServer();
        }
        return connectServer;
    }

    public String getData(OnListenerServer msgServer){
        String msgReceive = "";
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                setOpen();
            }

            while (true){
                msgReceive = bufferedReader.readLine();
                if (msgReceive != null){
                    msgServer.callBackData(msgReceive);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msgReceive;
    }

    private void setOpen() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
