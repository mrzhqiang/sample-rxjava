package sample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestNever {
  public static void main(String[] args) throws InterruptedException {
    Observable.never()
        .subscribeOn(Schedulers.immediate())
        .filter(new Func1<Object, Boolean>() {
          public Boolean call(Object o) {
            return o != null;
          }
        })
        .observeOn(Schedulers.immediate())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
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
