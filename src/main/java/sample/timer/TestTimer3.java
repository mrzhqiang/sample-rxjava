package sample.timer;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

@Deprecated
public class TestTimer3 {
  public static void main(String[] args) throws InterruptedException {
    final SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 初始延迟1s，间隔2s周期，反复执行，这个方法已经过时，并且推荐使用interval
    Observable.timer(1, 2, TimeUnit.SECONDS)
        .map(new Func1<Long, Object>() {
          public Object call(Long aLong) {
            subscriber.time.show("变换");
            if (aLong == 2) {
              // 可以强制中断这个定时器
              subscriber.unsubscribe();
            }
            return "序号：" + aLong;
          }
        })
        .observeOn(Schedulers.newThread())
        .subscribe(subscriber);

    Thread.sleep(10000);
    // 没用，不会执行Subscriber的onCompleted方法
    subscriber.unsubscribe();
    Thread.sleep(2000);
    subscriber.time.show("结束");
  }
}
