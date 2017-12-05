package sample.multiple;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import sample.SimpleSubscriber;

public class TestDebounce1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(100, TimeUnit.MILLISECONDS)
        // 两次发射的间隔超过指定时间，则丢弃前一次数据，如果后续指定时间内没有新数据，则发射这数据
        // 防抖动，比如快速点击了两下Button，这个时候，指定时间内的点击是无效的
        // 事实上，应该是丢弃当前的新数据，因为这新数据距离上一次产生数据，时间间隔太短，可以认为是无效的数据
        .debounce(99, TimeUnit.MILLISECONDS)
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
