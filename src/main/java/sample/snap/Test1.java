package sample.snap;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;
import sample.SimpleSubscriber;

public class Test1 {
  public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = SimpleSubscriber.create();

    Observable.range(1, 100)
        // 归纳，多个数据归纳为一个数据，通常用于叠加这样的操作，它是有返回值的
        /*.reduce(new Func2<Integer, Integer, Integer>() {
          public Integer call(Integer integer, Integer integer2) {
            return integer + integer2;
          }
        })*/
        // 收集，多个数据被某个集合收集起来，通常用于流水线产品打包，返回的是一个集合
        /*.collect(new Func0<List<Integer>>() {
          public List<Integer> call() {
            return new ArrayList<Integer>();
          }
        }, new Action2<List<Integer>, Integer>() {
          public void call(List<Integer> integers, Integer integer) {
            integers.add(integer);
          }
        })*/
        // 统计数据源数量
        /*.count()*/
        /*.countLong()*/
        .subscribe(subscriber);

    subscriber.onFinish();
  }
}
