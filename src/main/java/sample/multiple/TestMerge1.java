package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestMerge1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // merge是按照时间线来组合多个Observable，mergeWith内部使用了merge操作符
    Observable.just("1", "2", "3").delay(200, TimeUnit.MILLISECONDS)
        .mergeWith(Observable.just("0", "0", "0").delay(100, TimeUnit.MILLISECONDS))
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
