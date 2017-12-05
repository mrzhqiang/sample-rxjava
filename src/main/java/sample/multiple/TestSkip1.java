package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestSkip1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(300, TimeUnit.MILLISECONDS)
        .skip(3)
        .subscribe(subscriber);

    subscriber.onFinish();

    // skipLast
  }
}
