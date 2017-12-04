package sample.from;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom3 {
  public static void main(String[] args) throws InterruptedException {
    final long startTime = System.currentTimeMillis();
    // 首先在其他线程进行一项耗时的任务
    Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {
      public String call() throws Exception {
        System.out.println(System.currentTimeMillis() - startTime);
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis() - startTime);
        System.out.println(Thread.currentThread().getName());
        return RandomString.next(10);
      }
    });
    // 然后我们需要耗时任务的返回结果
    // 在通常情况下，子线程按照顺序执行，通过回调传递结果，回调也是在子线程中，因此需要抛到主线程上，进行二次处理
    // 然而Future能够在我们需要它的时候，以阻塞的形式等待它的到来，类似欢迎领导莅临模式
    // 比回调要舒服得多，因为回调会导致地狱模式的开启（嵌套回调总是令人难以理解）

    // 这里的超时检测是有意义的，因为你并不需要长时间等待，作为一个望夫石，你还有儿女需要抚育
    Observable.from(future, 1100, TimeUnit.MILLISECONDS)
        // 加上这个就不阻塞主线程，但还不如用from(future, scheduler)
        //.subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe(new Subscriber<String>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(String s) {
            System.out.println(System.currentTimeMillis() - startTime);
            System.out.println(Thread.currentThread().getName());
            System.out.println(s);
          }
        });

    // 如果这里的结果出来很慢，就说明阻塞了主线程
    System.out.println(System.currentTimeMillis() - startTime);
    System.out.println("结束");
  }
}
