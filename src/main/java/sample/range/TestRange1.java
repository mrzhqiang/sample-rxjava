package sample.range;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

public class TestRange1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 从1开始，发射10个数字，总共：(start+count-1)
    Observable.range(1, 10)
        // 等待一段时间之后，执行后续的操作
        .delay(3000, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
