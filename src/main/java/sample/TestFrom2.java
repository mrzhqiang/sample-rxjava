package sample;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom2 {
  public static void main(String[] args) throws InterruptedException {
    // 首先在其他线程执行一定任务
    Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
      public String call() throws Exception {
        return RandomString.next(10);
      }
    });
    // 通过这个future，可以等任务结束，同时调度到io线程
    // 一般来说，都是等待其他框架完成工作，而有时候我们需要打开一个开关，得到结果之后，进行下一步
    // 因此是没有参数传递进来的，那么使用fromCallable会比较好
    Observable.from(future, Schedulers.io())
        .map(new Func1<Object, Object>() {
          public Object call(Object o) {
            System.out.println(Thread.currentThread().getName());
            return o;
          }
        })
        // 观察在计算线程，输出结果
        .observeOn(Schedulers.computation())
        .subscribe(new Action1<Object>() {
      public void call(Object o) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(o);
      }
    });

    Thread.sleep(1000);
  }
}
