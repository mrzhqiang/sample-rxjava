package sample.multiple;

import rx.Observable;
import rx.functions.Func1;
import sample.SimpleSubscriber;

public class TestFilter1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.from(subscriber.data)
        // 过滤，满足条件的才发射
        .filter(new Func1<String, Boolean>() {
          public Boolean call(String s) {
            return s.length() > 2;
          }
        }).subscribe(subscriber);

    subscriber.onFinish();
  }
}
