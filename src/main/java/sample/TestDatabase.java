package sample;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TestDatabase {
  public static void main(String[] args) throws InterruptedException {
    Account account = new Account();
    account.username = "0000";
    account.password = "1111";
    Observable.just(account)
        .subscribeOn(Schedulers.io())
        .doOnNext(new Action1<Account>() {
          public void call(Account account) {
            insert(account);
          }
        })
        .observeOn(Schedulers.computation())
        .subscribe(new Action1<Account>() {
          public void call(Account account) {
            insert(account);
          }
        });

    Thread.sleep(1000);
    System.out.println("结束");
  }

  public static void insert(Account account) {
    System.out.println(Thread.currentThread().getName());
    System.out.println(account);
  }

  public static class Account {
    public String username;
    public String password;

    @Override public String toString() {
      return "<Account> username:" + username + ", password:" + password;
    }
  }

}
