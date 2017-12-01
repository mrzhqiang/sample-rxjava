package sample.data;

import java.security.SecureRandom;

public class RandomString {
  private static final String CHARS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final SecureRandom RANDOM = new SecureRandom();

  public static String next(int size) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    }
    return builder.toString();
  }

  /** [start, end) */
  public static String[] nexts(int count, int start, int end) {
    String[] result = new String[count];
    for (int i = 0; i < count; i++) {
      int size = (int) (start + Math.random() * end);
      result[i] = next(size);
    }
    return result;
  }
}
