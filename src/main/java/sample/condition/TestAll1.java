package sample.condition;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import sample.SimpleSubscriber;

public class TestAll1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(100, TimeUnit.MILLISECONDS)
    .all(new Func1<Long, Boolean>() {
      public Boolean call(Long aLong) {
        // false 就结束，true就一直进行
        // 意思是所有条件如果都满足，那么就继续执行，否则将发射一个false并中断？那么只能在这里处理数据了？
        // 比喻：一粒老鼠屎都不能存在，发现就必须停止所有事情
        return aLong<10;
      }
    })
    .subscribe(subscriber);

    subscriber.onFinish();
  }
}
