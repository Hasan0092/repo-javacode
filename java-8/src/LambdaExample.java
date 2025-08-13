import java.util.Arrays;
import java.util.List;

public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Hasan", "Amir", "Akram");

        // Using Lambda expression with map() and forEach()
        names.stream()
                .map(name -> name.toUpperCase())  // Lambda to convert to uppercase
                .forEach(name -> System.out.println(name)); // Lambda to print
    }
}
