package com.bhsc.demo.thread;


public class WtfThread {

  public static void main(String[] args) throws InterruptedException {

    yieldThread();
    blockWait();
    blockLock();

    System.out.println("hello:" + Thread.currentThread().getName());
    Thread.sleep(10000000);
  }

  public static void blockWait() {
    final Object waitObj = new Object();
    new Thread("waitThread") {
      public void run() {
        try {
          synchronized (waitObj) {
            waitObj.wait();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }

  public static void blockLock() throws InterruptedException {
    final Object waitObj = new Object();
    new Thread("ownerThread") {
      public void run() {
        synchronized (waitObj) {
          try {
            while (true) {
              Thread.sleep(1000);
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();

    Thread.sleep(1000);

    new Thread("blockThread") {
      public void run() {
        synchronized (waitObj) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();
  }

  public static void yieldThread() {
    new Thread("yieldThread") {
      public void run() {
        while (true) {
          Thread.currentThread().yield();
        }
      }
    }.start();
  }

}
