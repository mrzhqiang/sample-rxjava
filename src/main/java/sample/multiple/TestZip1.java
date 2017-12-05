package sample.multiple;

import rx.Observable;
import rx.functions.Func2;
import sample.SimpleSubscriber;

public class TestZip1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 把两个数据源发射出来的数据，压缩成需要的样子
    Observable.zip(Observable.just("11212"), Observable.just("口号"),
        new Func2<String, String, Object>() {
          public Object call(String s, String s2) {
            return s2 + s;
          }
        })
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
