package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestOfType1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.just("1", 2, 222, 332131, 22)
        // 只通过指定类型
        .ofType(Integer.class)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
