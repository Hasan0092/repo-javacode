import java.util.function.*;
import java.util.*;

public class FunctionalInterface {
    public static void main(String[] args) {

        // 1. Predicate<T> - tests a condition
        Predicate<String> isLongName = name -> name.length() > 5;
        System.out.println("Is 'Hasan' long? " + isLongName.test("Hasan"));

        // and() + negate()
        Predicate<String> startsWithH = name -> name.startsWith("H");
        System.out.println("Starts with H AND long? " + isLongName.and(startsWithH).test("Hasanuddin"));
        System.out.println("NOT long? " + isLongName.negate().test("Ali"));

        // 2. Function<T, R> - transforms data
        Function<String, Integer> nameLength = String::length;
        System.out.println("Length of 'Hasan': " + nameLength.apply("Hasan"));

        // andThen() + compose()
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Length squared: " + nameLength.andThen(square).apply("Hasan"));
        System.out.println("Square then length: " + square.compose(nameLength).apply("Hasan"));

        // 3. Supplier<T> - provides value without input
        Supplier<String> randomId = () -> UUID.randomUUID().toString();
        System.out.println("Random ID: " + randomId.get());

        // 4. Consumer<T> - processes value without return
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        printUpper.accept("Hasan");

        // andThen()
        Consumer<String> printLower = s -> System.out.println(s.toLowerCase());
        printUpper.andThen(printLower).accept("Java8");

        // 5. BiFunction<T, U, R> - takes two inputs
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("10 + 20 = " + add.apply(10, 20));

        // 6. UnaryOperator<T> - special case of Function<T, T>
        UnaryOperator<String> addExclamation = s -> s + "!";
        System.out.println(addExclamation.apply("Hello"));

        // 7. BinaryOperator<T> - special case of BiFunction<T, T, T>
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("5 * 4 = " + multiply.apply(5, 4));

        // 8. BiPredicate<T, U> - tests condition with two inputs
        BiPredicate<String, Integer> nameLengthCheck = (name, len) -> name.length() == len;
        System.out.println("Is 'Hasan' length 5? " + nameLengthCheck.test("Hasan", 5));

        // 9. BiConsumer<T, U> - takes two inputs, no return
        BiConsumer<String, Integer> printNameAndAge = (name, age) ->
                System.out.println(name + " is " + age + " years old");
        printNameAndAge.accept("Hasan", 30);
    }
}
