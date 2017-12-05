package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestDistinct1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    subscriber.data.add("1");
    subscriber.data.add("1");
    subscriber.data.add("1");
    subscriber.data.add("1");
    Observable.from(subscriber.data)
        // 过滤所有的重复数据，即只允许出现一次
        .distinct().subscribe(subscriber);

    subscriber.onFinish();
  }
}
