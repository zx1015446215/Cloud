package com.zhxshark.utils.nettty;

public class TimeClient {
    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer();
        timeServer.bind(8888);
    }

    private void bind(int port){

    }
}
