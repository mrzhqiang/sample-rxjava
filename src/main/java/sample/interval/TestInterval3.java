package sample.interval;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestInterval3 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(2000, 500, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
