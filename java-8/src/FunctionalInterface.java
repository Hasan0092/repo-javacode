import java.util.function.*;
import java.util.*;


public class FunctionalInterface {
    public static void main(String[] args) {

        System.out.println("=== Predicate Example ===");
        Predicate<String> isLongName = name -> name.length() > 5;
        Predicate<String> startsWithH = name -> name.startsWith("H");

        System.out.println("Is 'Hasan' long? " + isLongName.test("Hasan"));
        System.out.println("Starts with H AND long? " + isLongName.and(startsWithH).test("Hasanuddin"));
        System.out.println("Starts with H OR long? " + isLongName.or(startsWithH).test("Ali"));
        System.out.println("NOT long? " + isLongName.negate().test("Ali"));

        System.out.println("\n=== Function Example ===");
        Function<String, Integer> nameLength = String::length;
        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Length of 'Hasan': " + nameLength.apply("Hasan"));
        System.out.println("Length squared: " + nameLength.andThen(square).apply("Hasan"));
        System.out.println("Square then length: " + square.compose(nameLength).apply("Hasan"));

        System.out.println("\n=== Supplier Example ===");
        Supplier<String> randomId = () -> UUID.randomUUID().toString();
        System.out.println("Random ID: " + randomId.get());

        System.out.println("\n=== Consumer Example ===");
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        Consumer<String> printLower = s -> System.out.println(s.toLowerCase());
        printUpper.andThen(printLower).accept("Java8");

        System.out.println("\n=== BiFunction Example ===");
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("10 + 20 = " + add.apply(10, 20));

        System.out.println("\n=== UnaryOperator Example ===");
        UnaryOperator<String> addExclamation = s -> s + "!";
        System.out.println(addExclamation.apply("Hello"));

        System.out.println("\n=== BinaryOperator Example ===");
        BinaryOperator<Integer> multiply = (a, b) -> a * b;
        System.out.println("5 * 4 = " + multiply.apply(5, 4));

        // minBy & maxBy (static methods)
        Comparator<Integer> comparator = Integer::compareTo;
        BinaryOperator<Integer> minByOp = BinaryOperator.minBy(comparator);
        BinaryOperator<Integer> maxByOp = BinaryOperator.maxBy(comparator);
        System.out.println("Min of (10, 20): " + minByOp.apply(10, 20));
        System.out.println("Max of (10, 20): " + maxByOp.apply(10, 20));

        System.out.println("\n=== BiPredicate Example ===");
        BiPredicate<String, Integer> nameLengthCheck = (name, len) -> name.length() == len;
        System.out.println("Is 'Hasan' length 5? " + nameLengthCheck.test("Hasan", 5));

        System.out.println("\n=== BiConsumer Example ===");
        BiConsumer<String, Integer> printNameAndAge = (name, age) ->
                System.out.println(name + " is " + age + " years old");
        printNameAndAge.accept("Hasan", 30);

        System.out.println("\n=== Primitive Specializations ===");
        IntPredicate isEven = num -> num % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));

        DoubleFunction<String> doubleToString = val -> "Value: " + val;
        System.out.println(doubleToString.apply(3.14));

        ToIntFunction<String> lengthFunction = String::length;
        System.out.println("Length of 'Functional': " + lengthFunction.applyAsInt("Functional"));

        IntSupplier randomInt = () -> new Random().nextInt(100);
        System.out.println("Random int: " + randomInt.getAsInt());

        LongUnaryOperator doubleValue = val -> val * 2;
        System.out.println("Double of 10L: " + doubleValue.applyAsLong(10L));

        ObjIntConsumer<String> repeatName = (name, times) -> {
            for (int i = 0; i < times; i++) System.out.println(name);
        };
        repeatName.accept("Repeat", 3);
    }
}
