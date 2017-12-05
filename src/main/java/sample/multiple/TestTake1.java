package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestTake1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(100, TimeUnit.MILLISECONDS)
        // 只获取指定范围发射的数据，类似一个定时器，通常可以用来作为副本计时或副本计数，配合Interval更酸爽
        .take(2000, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);

    // 其他如：takeLast TakeFirst first firstOrDefault last lastOrDefault
    // 就不做多余测试了，望文生义即可

    subscriber.onFinish();
  }
}
