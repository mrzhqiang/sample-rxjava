package sample.timer;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestTimer1 {
  public static void main(String[] args) throws InterruptedException {
    final long startTime = System.currentTimeMillis();
    // 延迟3秒之后，发射一个默认为0的Long值，内部默认调度为计算线程
    Observable.timer(3, TimeUnit.SECONDS)
        // 切换到IO线程
        .observeOn(Schedulers.io())
        .flatMap(new Func1<Long, Observable<?>>() {
          public Observable<?> call(Long aLong) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("处理：" + (System.currentTimeMillis() - startTime));
            // 从1开始发射10个数
            return Observable.range(1, 10);
          }
        })
        // 切换到新线程
        .observeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(Object o) {
            System.out.println(System.currentTimeMillis() - startTime);
            System.out.println(Thread.currentThread().getName());
            System.out.println(o);
          }
        });

    Thread.sleep(10000);
    System.out.println(System.currentTimeMillis() - startTime);
    System.out.println("结束");
  }
}
