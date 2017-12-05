package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestStartWith1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.just("1", "2", "3")
        // 插队，内部是用concat的方式，连接它们
        .startWith("0")
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
