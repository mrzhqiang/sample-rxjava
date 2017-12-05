package sample.range;

import rx.Observable;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

public class TestRange2 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.range(10, 50, Schedulers.io())
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
