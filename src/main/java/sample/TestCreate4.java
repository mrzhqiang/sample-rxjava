package sample;

import java.security.SecureRandom;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.observables.AsyncOnSubscribe;

public class TestCreate4 {
  private static final String CHARS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final SecureRandom RANDOM = new SecureRandom();

  public static void main(String[] args) {
    // 4.不常用，所以不知所谓
    Observable.create(new AsyncOnSubscribe<Object, Object>() {
      @Override protected Object generateState() {
        int size = (int) (1 + Math.random() * 10);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
          builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return builder.toString();
      }

      @Override protected Object next(Object o, long l, Observer<Observable<?>> observer) {
        observer.onNext(Observable.just(o));
        //observer.onCompleted();
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
  }
}
