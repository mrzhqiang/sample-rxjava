package sample;

import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;

public class SimpleSubscriber extends Subscriber<Object> {

  public final List<String> data = new ArrayList<String>();

  public final PTime time;

  public static SimpleSubscriber create() {
    return new SimpleSubscriber(PTime.create());
  }

  private SimpleSubscriber(PTime time) {
    this.time = time;
    data.add("1");
    data.add("22");
    data.add("333");
    data.add("4444");
    data.add("55555");
    data.add("666666");
    data.add("7777777");
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

  public void onFinish() throws InterruptedException {
    Thread.sleep(8000);
    unsubscribe();
    Thread.sleep(1000);
    time.show("结束");
  }
}
