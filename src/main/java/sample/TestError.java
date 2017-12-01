package sample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestError {
  public static void main(String[] args) throws InterruptedException {
    Observable.error(new RuntimeException("运行出错"))
        .subscribeOn(Schedulers.newThread())
        .filter(new Func1<Object, Boolean>() {
          public Boolean call(Object o) {
            return o != null;
          }
        })
        .observeOn(Schedulers.io())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            System.out.println(Thread.currentThread().getName());
            throwable.printStackTrace();
          }

          public void onNext(Object o) {
            System.out.println("下一个");
          }
        });
    Thread.sleep(1000);
  }
}
