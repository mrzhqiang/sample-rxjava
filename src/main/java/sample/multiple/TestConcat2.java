package sample.multiple;

import java.util.Arrays;
import java.util.List;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestConcat2 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    List<String> fromList = Arrays.asList("121", "21212");
    // 这种方式其实跟TestConcat1一样，还有就是concatMap
    // 以头尾连接的方式，将多个Observable组合起来
    Observable.from(fromList).concatWith(Observable.from(fromList))
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
