package sample.multiple;

import rx.Observable;
import sample.SimpleSubscriber;

public class TestConcat1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable<String> stringObservable = Observable.just("1", "2121", "1");
    Observable<Integer> integerObservable = Observable.just(1, 2121, 1);
    Observable.concat(stringObservable, integerObservable)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
