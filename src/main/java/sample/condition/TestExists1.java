package sample.condition;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Func1;
import sample.SimpleSubscriber;

public class TestExists1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.interval(200, TimeUnit.MILLISECONDS)
        .exists(new Func1<Long, Boolean>() {
          public Boolean call(Long aLong) {
            // all是条件符合，继续执行，直到遇见不符合的条件；exists是条件不符合，继续执行，直到遇见条件符合的
            return aLong == 10;
          }
        })
        .subscribe(subscriber);

    // contains 是否包含
    // sequenceEqual 发射的数据是否相等：以数据源/顺序/终止状态为条件
    // isEmpty 查看是否只通知了onComplete，如果是，则通知true；否则通知false
    // switchIfEmpty 如果得到一个空的数据源，那么启动备胎计划
    // defaultIfEmpty 如果是空数据源，那么直接给出备胎
    // takeUntil 获取，直到条件达成，类似吃饱了
    // takeWhile 获取，直到条件达成，类似快吃饱了（不包含吃饱这个状态）
    // skipUntil 跳过，直到条件达成，类似挑选喜欢吃的东西
    // skipWhile 跳过，直到条件达成，类似快到某个时间点该吃饭了

    subscriber.onFinish();
  }
}
