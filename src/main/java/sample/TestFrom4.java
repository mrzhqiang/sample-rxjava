package sample;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import sample.data.RandomString;

public class TestFrom4 {
  public static void main(String[] args) throws InterruptedException {
    // 从一个字符串数组处理相关数据，这里是有数据源的情况
    Observable.from(RandomString.nexts(10, 7, 21))
        // 调度到IO线程吧，少年
        .subscribeOn(Schedulers.io())
        // 摘果子啦
        .subscribe(new Subscriber<String>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(String s) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s);
          }
        });

    Thread.sleep(10);
    // 打印一下，检查主线程是否阻塞
    System.out.println("过渡");
    Thread.sleep(990);
    System.out.println("结束");
  }
}
