package sample;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class TestCreate2 {
  public static void main(String[] args) throws InterruptedException {
    // 2.好像不常用，也不知道是干嘛的
    Observable.create(new Action1<Emitter<String>>() {
      public void call(Emitter<String> emitter) {
        emitter.onNext("11");
        emitter.requested();
        emitter.onError(new RuntimeException("222"));
        emitter.onCompleted();
      }
    }, Emitter.BackpressureMode.BUFFER)
        .subscribe(new Subscriber<String>() {
          public void onCompleted() {
            System.out.println("完成");
          }

          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          public void onNext(String t) {
            System.out.println(t);
          }
        });

    Thread.sleep(1000);
  }
}
