package com.bhsc.demo.thread.lock;

import java.util.Random;

public class SynchronizedInstanceTest {

  public synchronized void method1(final int index) throws Exception {
    System.out.println("====" + Thread.currentThread().getName() + " method1 index:" + index
        + " start ====");
    Thread.sleep(1000);
    System.out.println("====" + Thread.currentThread().getName() + " method1 index:" + index
        + " end ====");
  }

  public synchronized void method2(final int index) throws Exception {
    System.out.println("====" + Thread.currentThread().getName() + " method2 index:" + index
        + " start ====");
    Thread.sleep(1000);
    System.out.println("====" + Thread.currentThread().getName() + " method2 index:" + index
        + " end ====");
  }

  public synchronized void method3(final int index) throws Exception {
    System.out.println("====" + Thread.currentThread().getName() + " method3 index:" + index
        + " start ====");
    Thread.sleep(1000);
    System.out.println("====" + Thread.currentThread().getName() + " method3 index:" + index
        + " end ====");
  }

  public static void main(String[] args) throws Exception {
    final SynchronizedInstanceTest test = new SynchronizedInstanceTest();
    Runnable runn = new Runnable() {
      public void run() {
        int i = 10;
        while (i-- > 0) {
          try {
            if (i % 3 == 0) {
              test.method1(i);
            } else if (i % 3 == 1) {
              test.method2(i);
            } else if (i % 3 == 2) {
              test.method3(i);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          try {
            Thread.sleep(new Random().nextInt(100));
          } catch (InterruptedException e) {
          }
        }
      }
    };

    for (int i = 0; i < 3; i++) {
      Thread t1 = new Thread(runn);
      t1.setName("t" + i);
      t1.start();
    }
  }

}
