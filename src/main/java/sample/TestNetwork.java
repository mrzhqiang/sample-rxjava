package sample;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TestNetwork {
  public static void main(String[] args) throws InterruptedException {
    TestDatabase.Account account = new TestDatabase.Account();
    account.username = "mrzq";
    account.password = "0314";
    Observable.just(account)
        .subscribeOn(Schedulers.io())
        .flatMap(new Func1<TestDatabase.Account, Observable<User>>() {
          public Observable<User> call(TestDatabase.Account account) {
            TestDatabase.insert(account);
            String username = account.username;
            User user = new User();
            user.name = username;
            user.age = 10;
            return Observable.just(user);
          }
        })
        .observeOn(Schedulers.computation())
        .subscribe(new Action1<User>() {
          public void call(User user) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(user);
          }
        });

    Thread.sleep(1000);
    System.out.println("结束");
  }

  public static class User {
    public String name;
    public int age;

    @Override public String toString() {
      return "<User> name:" + name + ", age:" + age;
    }
  }
}
