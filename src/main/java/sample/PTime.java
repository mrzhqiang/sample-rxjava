package sample;

public class PTime {
  private long startTime;

  private PTime() {
    this.startTime = System.currentTimeMillis();
  }

  public void show(Object message) {
    System.out.println(message);
    System.out.println("用时：" + (System.currentTimeMillis() - startTime));
    System.out.println(Thread.currentThread().getName());
    System.out.println();
  }

  public static PTime create() {
    return new PTime();
  }

}
