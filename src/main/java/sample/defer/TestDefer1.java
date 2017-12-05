package sample.defer;

import rx.Observable;
import rx.functions.Func0;
import sample.SimpleSubscriber;

public class TestDefer1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 只有当订阅者订阅，才创建Observable，为每个订阅创建一个新的Observable
    // 延迟创建，因为有可能没有任何订阅来消费这个事件
    Observable.defer(new Func0<Observable<String>>() {
      public Observable<String> call() {
        return Observable.just("mrZQ");
      }
    }).subscribe(subscriber);

    subscriber.onFinish();
  }
}
