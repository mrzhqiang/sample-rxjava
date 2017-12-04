package sample.create;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Deprecated
public class TestCreate1 {
  public static void main(String[] args) throws InterruptedException {
    // 1.已过时
    Observable.create(new Observable.OnSubscribe<Object>() {
      public void call(Subscriber<? super Object> subscriber) {
        subscriber.onStart();
        subscriber.onNext("欢迎来到兰达尔");
        subscriber.onNext("冥冥之中，你感受到一股温暖正在逼近，你睁开眼睛，发现一团柔和的光芒正在前方，原来是天使托梦");
        subscriber.onNext("天使在梦中告诉你，远方的一座叫兰达尔的小镇，突然间失去了与教堂的联系，希望你前去调查");
        subscriber.onNext("你决定立刻动身前往这个小镇");
        subscriber.onCompleted();
      }
    })
        .subscribeOn(Schedulers.io())
        .map(new Func1<Object, Object>() {
          public Object call(Object o) {
            System.out.println(Thread.currentThread().getName());
            if (o instanceof String) {
              String temp = (String) o;
              if (temp.contains("欢迎")) {
                return temp.replace("欢迎", "Welcome");
              }
            }
            return o;
          }
        })
        .observeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Object>() {
          public void onCompleted() {
            System.out.println(Thread.currentThread().getName());
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
  }
}
