package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestThrottleFirst1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(1, TimeUnit.MILLISECONDS)
        // 节流阀，意思是传入一个时间间隔，定期打开阀门让发射的首个数据通过，当然它不是队列也不会阻塞，随缘
        // 实际上相当于使用interval做间隔取值
        .throttleFirst(500, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
