package sample;

import rx.Subscriber;

public class SimpleSubscriber extends Subscriber<Object> {

  public final PTime time;

  public static SimpleSubscriber create() {
    return new SimpleSubscriber(PTime.create());
  }

  private SimpleSubscriber(PTime time) {
    this.time = time;
  }

  public void onCompleted() {
    time.show("完成");
  }

  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  public void onNext(Object o) {
    time.show(o);
  }
}
