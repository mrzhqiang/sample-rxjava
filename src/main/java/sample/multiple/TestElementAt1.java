package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestElementAt1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.from(subscriber.data)
        // 弱水三千，只取一瓢
        .elementAt(0)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
