package sample.multiple;

import rx.Observable;
import rx.functions.Func2;
import sample.SimpleSubscriber;

public class TestCombineLatest1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // FIXME 将最后一个发射的数据，与后面的数据源组合起来，这地方有问题，后面再回头来看
    Observable.combineLatest(Observable.just("121", "212", "111"),
        Observable.just("11", "22", "33", "44"),
        new Func2<String, String, Object>() {
          public Object call(String s, String s2) {
            return s + s2;
          }
        })
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
