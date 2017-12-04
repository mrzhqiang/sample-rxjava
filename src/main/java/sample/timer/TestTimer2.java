package sample.timer;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

public class TestTimer2 {
  public static void main(String[] args) throws InterruptedException {
    final SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.timer(1, TimeUnit.SECONDS, Schedulers.io())
        .map(new Func1<Long, Object>() {
          public Object call(Long aLong) {
            subscriber.time.show("变换");
            return aLong;
          }
        })
        .observeOn(Schedulers.newThread())
        .flatMap(new Func1<Object, Observable<?>>() {
          public Observable<?> call(Object o) {
            subscriber.time.show("平行变换");
            return Observable.from(new String[] {"1", "2", "3"});
          }
        })
        .observeOn(Schedulers.computation())
        .subscribe(subscriber);

    Thread.sleep(3000);
    subscriber.time.show("结束");
  }
}
