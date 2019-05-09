package com.example.demochat.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectServer {
    private Socket socket;
    private String ip = "172.16.13.75";
    private int port = 8888;

    private BufferedWriter bufferedWriter;
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

    public String getData(String msgSend){
        String msgReceive = "";
        try {
            if (socket == null) {
                socket = new Socket(ip, port);
                setOpen();
            }
            bufferedWriter.write(msgSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            msgReceive = bufferedReader.readLine();

            if (msgSend.equals("exit")) {
                setClose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msgReceive;
    }

    private void setOpen() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void setClose() {
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
            System.out.println(ex.toString());
        }
    }
}
