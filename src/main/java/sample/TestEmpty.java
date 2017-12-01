package sample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestEmpty {
  public static void main(String[] args) throws InterruptedException {
    Observable.empty()
        // 所有的中间过程都不执行
        .filter(new Func1<Object, Boolean>() {
          public Boolean call(Object o) {
            System.out.println("过滤，不执行");
            return o == null;
          }
        })
        .subscribeOn(Schedulers.io())
        .map(new Func1<Object, Object>() {
          public Object call(Object o) {
            System.out.println("映射/转换，不执行");
            return o;
          }
        })
        .observeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("会通知完成");
            // 抛出异常不会进入OnError
            //throw new RuntimeException("抛出异常");
          }

          public void onError(Throwable throwable) {
            System.err.println("不会进入出错");
          }

          public void onNext(Object o) {
            System.out.println("没有返回：" + o);
          }
        });

    Thread.sleep(1000);
  }
}
