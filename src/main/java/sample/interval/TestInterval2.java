package sample.interval;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

public class TestInterval2 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 间隔200ms执行，调度在IO线程
    Observable.interval(200, TimeUnit.MILLISECONDS, Schedulers.io())
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
