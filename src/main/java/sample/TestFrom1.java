package sample;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom1 {
  public static void main(String[] args) throws InterruptedException {
    // 这样一般是其他框架或者自定义的线程池中，正在执行一定的任务，但是后面的执行需要这部分任务返回一个结果
    // 因此提交一个Future给RxJava
    // Observable通过这个future等待返回结果，然后继续执行任务
    // 用来作为不同框架的异步执行粘合剂，倒是一个不错的选择
    Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
      public String call() throws Exception {
        // 这里是在Executors.newSingleThreadExecutor()线程池中执行
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(500);
        return RandomString.next(20);
      }
    });

    // 1.一般的话，是从一个已有的Future中，等待返回结果
    // 通常的多线程是通过回调返回结果，而Future则是通过return返回结果
    Observable.from(future)
        .subscribeOn(Schedulers.io())
        .map(new Func1<String, Object>() {
          public Object call(String s) {
            // 这里已经调度到io线程
            System.out.println(Thread.currentThread().getName());
            return s;
          }
        })
        .observeOn(Schedulers.computation())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            // 这里调度到了计算线程
            System.out.println(Thread.currentThread().getName());
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(Object o) {
            System.out.println(o);
          }
        });

    Thread.sleep(1000);
  }
}
