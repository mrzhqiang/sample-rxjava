package sample.snap;

import rx.Observable;
import sample.SimpleSubscriber;

public class Test2 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.range(1, 100)
        // 收集数据，转换为列表
        /*.toList()*/
        // 收集数据，转换为有序列表
        /*.toSortedList()*/
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
