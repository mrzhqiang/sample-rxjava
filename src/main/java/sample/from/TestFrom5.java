package sample.from;

import java.util.Arrays;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom5 {
  public static void main(String[] args) throws InterruptedException {
    String[] randoms = RandomString.nexts(10, 10, 20);
    Observable.from(Arrays.asList(randoms))
        .subscribeOn(Schedulers.io())
        .map(new Func1<String, String>() {
          public String call(String s) {
            System.out.println(Thread.currentThread().getName());
            return s;
          }
        })
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
    System.out.println("结束");
  }
}
