package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestDistinct2 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    subscriber.data.add("1");
    subscriber.data.add("1");
    subscriber.data.add("22");
    subscriber.data.add("333");
    subscriber.data.add("4444");
    subscriber.data.add("55555");
    subscriber.data.add("666666");
    subscriber.data.add("7777777");
    Observable.from(subscriber.data)
        // 去掉相邻的重复数据，允许多次间隔出现，但不允许连续重复出现
    .distinctUntilChanged().subscribe(subscriber);

    subscriber.onFinish();
  }
}
