package sample.create;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.SyncOnSubscribe;

public class TestCreate3 {
  public static void main(String[] args) throws InterruptedException {
    // 3.也不是常用的，也不知道怎么搞它
    Observable.create(new SyncOnSubscribe<Object, Object>() {
      @Override protected Object generateState() {
        return "1111";
      }

      @Override protected Object next(Object o, Observer<? super Object> observer) {
        observer.onNext(o);
        // 这里如果不调用完成方法，会一直触发下一个，并且是根据return的结果输出到下一个的回调
        observer.onCompleted();
        return o;
      }
    }).subscribe(new Subscriber<Object>() {
      public void onCompleted() {
        System.out.println("完成");
      }

      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      public void onNext(Object o) {
        System.out.println(o);
      }
    });

    Thread.sleep(1000);
  }
}
