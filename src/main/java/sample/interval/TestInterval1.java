package sample.interval;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestInterval1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 间隔100ms执行一次，默认在计算线程上执行
    Observable.interval(100, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);
    subscriber.onFinish();
  }
}
