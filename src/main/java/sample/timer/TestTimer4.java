package sample.timer;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.SimpleSubscriber;

@Deprecated
public class TestTimer4 {
  public static void main(String[] args) throws InterruptedException {
    final SimpleSubscriber subscriber = SimpleSubscriber.create();

    // 已过时，建立一个初始延迟200ms，间隔500ms重复，在IO线程上执行的发射器
    Observable.timer(200, 500, TimeUnit.MILLISECONDS, Schedulers.io())
        // 调度后面的观察者在一个新线程上
        .observeOn(Schedulers.newThread())
        .map(new Func1<Long, Object>() {
          public Object call(Long aLong) {
            subscriber.time.show("变换");
            return "第" + (aLong + 1) + "号选手";
          }
        })
        // 调度在计算线程上
        .observeOn(Schedulers.computation())
        .subscribe(subscriber);

    Thread.sleep(10000);

    subscriber.time.show("结束");
  }
}
