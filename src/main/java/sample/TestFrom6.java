package sample;

import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom6 {
  public static void main(String[] args) throws InterruptedException {
    Observable.fromCallable(new Callable<String>() {
      public String call() throws Exception {
        // 这里不需要任何参数传递进来，可以作为一系列任务的起始点
        System.out.println(Thread.currentThread().getName());
        return RandomString.next(10);
      }
    })
        // 丢到IO线程
        .subscribeOn(Schedulers.io())
        // 进行条件过滤
        .filter(new Func1<String, Boolean>() {
          public Boolean call(String s) {
            System.out.println(Thread.currentThread().getName());
            return s.length() > 5;
          }
        })
        // 进行映射变换
        .map(new Func1<String, String>() {
          public String call(String s) {
            System.out.println(Thread.currentThread().getName());
            return "Rx " + s;
          }
        })
        // 抛到计算线程，在Android中，则是UI线程
        .observeOn(Schedulers.computation())
        .subscribe(new Subscriber<String>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(String s) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s);
          }
        });

    Thread.sleep(1000);
  }
}
