package com.bhsc.demo.thread.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class CASScalab {
  private final static long MAX = 100000000;
  private final static int CORE_NUM = Runtime.getRuntime().availableProcessors();


  public static void main(String[] args) throws InterruptedException {
    preHot();
    System.out.println("==== go ====");

    oneCas(MAX);
    f4Cas(MAX);
    competeCas(MAX);
    competenNormal(MAX);
  }

  public static void preHot() throws InterruptedException {
    oneCas(10000000);
    f4Cas(10000000);
    competeCas(10000000);
    competenNormal(10000000);
  }

  public static void oneCas(long max) {
    long start = System.currentTimeMillis();
    final AtomicLong cas1 = new AtomicLong();
    while (cas1.incrementAndGet() < max) {

    }
    System.out.println("===== oneCas end:" + (System.currentTimeMillis() - start) + " =====");
  }

  public static void f4Cas(final long max) throws InterruptedException {
    long start = System.currentTimeMillis();
    final CountDownLatch cdl = new CountDownLatch(CORE_NUM);
    for (int i = 0; i < CORE_NUM; i++) {
      final AtomicLong cas2 = new AtomicLong();
      new Thread() {
        public void run() {
          long start = System.currentTimeMillis();
          while (cas2.incrementAndGet() < max) {
          }
          System.out.println(Thread.currentThread() + ":"
              + (System.currentTimeMillis() - start));
          cdl.countDown();
        }
      }.start();
    }
    cdl.await();
    System.out.println("===== f4Cas end =====" + (System.currentTimeMillis() - start));
  }

  public static void competeCas(final long max) throws InterruptedException {
    long start = System.currentTimeMillis();
    final AtomicLong cas2 = new AtomicLong();
    final CountDownLatch cdl = new CountDownLatch(CORE_NUM);
    for (int i = 0; i < CORE_NUM; i++) {
      new Thread() {
        public void run() {
          long start = System.currentTimeMillis();
          while (cas2.incrementAndGet() < max) {
          }
          System.out.println(Thread.currentThread() + ":"
              + (System.currentTimeMillis() - start));
          cdl.countDown();
        }
      }.start();
    }
    cdl.await();
    System.out.println("===== competeCas end =====" + (System.currentTimeMillis() - start));
  }

  static long lvla = 0;
  public static void competenNormal(final long max) throws InterruptedException {
    long start = System.currentTimeMillis();

    final CountDownLatch cdl = new CountDownLatch(CORE_NUM);
    for (int i = 0; i < CORE_NUM; i++) {
      new Thread() {
        public void run() {
          long start = System.currentTimeMillis();
          while (lvla++ < max) {
          }
          System.out.println(Thread.currentThread() + ":" + (System.currentTimeMillis() - start));
          cdl.countDown();
        }
      }.start();
    }
    cdl.await();
    System.out.println("===== competeCas end =====" + (System.currentTimeMillis() - start));
  }

}
