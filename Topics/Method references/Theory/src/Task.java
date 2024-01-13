// You can experiment here, it won’t be checked

import java.util.function.Function;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Function<Integer, Integer> lambda = n -> n + 2;
    System.out.println(lambda.apply(3));

    MyOwnFunction<Integer, Integer> lambda2 = n -> n + 2;
    System.out.println(lambda2.execute(2));

    TwoMethods<Integer, Integer> lambda3 = new TwoMethods<Integer, Integer>() {
      @Override
      public Integer execute(Integer value) {
        return value + 5;
      }

      @Override
      public Integer run(Integer value) {
        return value + 6;
      }
    };
    System.out.println(lambda3.execute(3));
    System.out.println(lambda3.run(3));
  }
}

interface MyOwnFunction<T, R> {
  R execute(T value);
}

interface TwoMethods<T, R> {
  R execute(T value);
  R run(T value);
}