package com.bhsc.demo.thread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TooManyThread {

  public static void main(String[] args) throws Exception {
    lotsThreads();

    Thread.sleep(100000);
  }


  public static void lotsThreads() throws Exception {
    for (int i = 0; i < 1000; i++) {
      new Thread() {
        public void run() {
          while (true) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
      }.start();
    }
  }

  public static void tooManyThreadServer() throws Exception {
    ServerSocket ss = new ServerSocket(9999);

    while (true) {
      final Socket server = ss.accept();
      new Thread() {
        public void run() {
          try {
            // read
            final InputStream is = server.getInputStream();
            byte b[] = new byte[1024];
            is.read(b);
            String str = new String(b);

            // write
            OutputStream os = server.getOutputStream();
            os.write(new String(str.replaceAll("\r\n", "") + " client!\r\n").getBytes("utf-8"));

            os.close();
            is.close();
            server.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }.start();
    }
  }

}
