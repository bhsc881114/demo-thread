package com.bhsc.demo.thread.atomic;

import java.util.concurrent.CountDownLatch;

public class AtomicTest extends Thread {

  private static volatile int count;

  public static void main(String[] args) throws Exception {
    final CountDownLatch cdl = new CountDownLatch(50);
    for (int i = 0; i < 50; i++) {
      new Thread() {
        public void run() {
          for (int j = 0; j < 50; j++) {
            count++;
          }
          cdl.countDown();
        }
      }.start();
    }
    cdl.await();

    System.out.println("count:" + count);
  }

}