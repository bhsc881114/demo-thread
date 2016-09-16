package com.bhsc.demo.thread.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCallableTest {

  public static void main(String[] args) {
    Callable<Integer> callable = new Callable<Integer>() {
      public Integer call() throws Exception {
        Thread.sleep(1000);
        return new Random().nextInt(100);
      }
    };
    FutureTask<Integer> future = new FutureTask<Integer>(callable);
    new Thread(future).start();
    try {
      Thread.sleep(5000);// 可能做一些事情
      System.out.println(future.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
