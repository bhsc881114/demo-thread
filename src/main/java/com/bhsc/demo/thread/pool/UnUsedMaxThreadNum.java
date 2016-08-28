package com.bhsc.demo.thread.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UnUsedMaxThreadNum {

  static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60L,
      TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());

  public static void main(String[] args) throws InterruptedException {
    int max = 100;
    final CountDownLatch cdl = new CountDownLatch(max);
    Runnable runn = new Runnable() {
      public void run() {
        try {
          Thread.sleep(500);
          cdl.countDown();
          System.out.println(executor.getActiveCount() + "," + executor.getQueue().size());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    for (int i = 0; i < max; i++) {
      executor.submit(runn);
    }
    cdl.await();
  }

}
