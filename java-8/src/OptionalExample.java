import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {

        // 1. of() - creates Optional with a non-null value
        Optional<String> opt1 = Optional.of("Hasan");

        // 2. ofNullable() - allows null value
        Optional<String> opt2 = Optional.ofNullable(null);

        // 3. empty() - creates an empty Optional
        Optional<String> opt3 = Optional.empty();

        // 4. isPresent() - check if value exists
        System.out.println("opt1 has value? " + opt1.isPresent());

        // 5. isEmpty() - Java 11+ (not in Java 8, use !isPresent())
        System.out.println("opt2 is empty? " + !opt2.isPresent());

        // 6. get() - get value (unsafe if empty)
        System.out.println("Value in opt1: " + opt1.get());

        // 7. ifPresent() - run action if value exists
        opt1.ifPresent(value -> System.out.println("Uppercase: " + value.toUpperCase()));

        // 8. ifPresentOrElse() - Java 9+, not Java 8 (skip in pure Java 8)

        // 9. orElse() - return default if empty
        System.out.println("opt2 orElse: " + opt2.orElse("DefaultName"));

        // 10. orElseGet() - return default using Supplier
        System.out.println("opt2 orElseGet: " + opt2.orElseGet(() -> "GeneratedName"));

        // 11. orElseThrow() - throw exception if empty
        try {
            opt2.orElseThrow(() -> new RuntimeException("Value is missing!"));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // 12. filter() - conditionally keep value
        opt1.filter(name -> name.startsWith("H"))
                .ifPresent(val -> System.out.println("Starts with H: " + val));

        // 13. map() - transform value if present
        Optional<Integer> lengthOpt = opt1.map(String::length);
        System.out.println("Length of opt1 value: " + lengthOpt.orElse(0));

        // 14. flatMap() - flatten nested Optional
        Optional<String> nested = Optional.of("Hello");
        Optional<Integer> lengthFlat = nested.flatMap(v -> Optional.of(v.length()));
        System.out.println("FlatMap result: " + lengthFlat.get());

        // 15. equals() - compare two Optionals
        System.out.println("opt1 equals Optional.of(\"Hasan\")? " + opt1.equals(Optional.of("Hasan")));

        // 16. hashCode() - returns hash of value or 0 if empty
        System.out.println("HashCode of opt1: " + opt1.hashCode());

        // 17. toString() - string representation
        System.out.println("opt1 toString: " + opt1.toString());
    }
}
