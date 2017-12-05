package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestSample1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(10, TimeUnit.MILLISECONDS)
        // 按照时间间隔去采样
        .sample(500, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
