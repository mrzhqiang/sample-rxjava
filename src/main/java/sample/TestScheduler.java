package sample;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestScheduler {
  public static void main(String[] args) throws InterruptedException {
    Observable.just("1", "1212")
        // 这里表示调度到IO线程上，数据源的发射将来自IO线程，这个方法只调用一次即可，多次调用并无效果
        .subscribeOn(Schedulers.io())
        // 变换，在IO线程上进行
        .map(new Func1<String, Object>() {
          public Object call(String s) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("转换");
            return "新：" + s;
          }
        })
        // 这里是将后续的处理调度到一个新的线程，可以多次调用，并且主要是用这个方法来进行线程切换
        .observeOn(Schedulers.newThread())
        // 过滤，在新线程上进行
        .filter(new Func1<Object, Boolean>() {
          public Boolean call(Object o) {
            System.out.println(Thread.currentThread().getName());
            System.out.println("过滤");
            return o != null;
          }
        })
        // 最终调度到计算线程，在Android中则是UI线程
        .observeOn(Schedulers.computation())
        // 打印，在计算线程上进行
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(Object o) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(o);
          }
        });

    Thread.sleep(1000);
    System.out.println("结束");
  }
}
