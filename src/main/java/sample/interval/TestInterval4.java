package sample.interval;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

public class TestInterval4 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(1000, 700, TimeUnit.MILLISECONDS, Schedulers.io())
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
