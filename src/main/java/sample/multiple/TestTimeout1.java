package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import sample.SimpleSubscriber;

public class TestTimeout1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(10, TimeUnit.MILLISECONDS)
        .filter(new Func1<Long, Boolean>() {
          public Boolean call(Long aLong) {
            if (aLong == 50) {
              try {
                Thread.sleep(10000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            return true;
          }
        })
        // 超时检测，如果超过这时间没有做任何事情（阻塞），那么就会抛出一个超时异常
        .timeout(5, TimeUnit.SECONDS)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
