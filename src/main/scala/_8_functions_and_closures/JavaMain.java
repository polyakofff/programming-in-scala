package _8_functions_and_closures;

import java.util.List;
import java.util.function.Function;

public class JavaMain {

    static void f(int... args) {
        System.out.println(args.length);
    }

    static Function<Integer, Integer> g(int x) {
//        x = 2;
        return (Integer y) -> x + y;
    }

    public static void main(String... args) {
        // не работает!
//        List<Integer> someNumbers = List.of(-3, -2, -1, 0, 1, 2);
//        int sum = 0;
//        someNumbers.forEach(num -> sum += num);
//        System.out.println(sum);

        f(new int[]{1, 2, 3});

        System.out.println(g(10).apply(3));

    }
}
