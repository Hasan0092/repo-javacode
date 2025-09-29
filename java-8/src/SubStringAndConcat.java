import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.reverse;

public class SubStringAndConcat {

    public static void main(String[] args) {

        String str = "amir";


        //Not recomended using multiple time logic for revserinf string
        /*int mid = str.length() / 2;
        String firstHalf = str.substring(0, mid);
        String secondHalf = str.substring(mid);

        // Reverse first half using streams
        String reversedFirst = IntStream.range(0, firstHalf.length())
                .mapToObj(i -> firstHalf.charAt(firstHalf.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());

        // Reverse second half using streams
        String reversedSecond = IntStream.range(0, secondHalf.length())
                .mapToObj(i -> secondHalf.charAt(secondHalf.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());

        // Concatenate
        String result = reversedFirst + reversedSecond;

        System.out.println(result); // mari*/

        // recommended using common for all reverse string


        int mid = str.length() / 2;
        String firstHalf = str.substring(0, mid);
        String secondHalf = str.substring(mid);

        // Use helper method
        String reversedFirst = reverseWithStream(firstHalf);
        String reversedSecond = reverseWithStream(secondHalf);

        String result = reversedFirst + reversedSecond;
        System.out.println(result); // mari
    }

    // Reusable reverse method using streams
    private static String reverseWithStream(String input) {
        return IntStream.range(0, input.length())
                .mapToObj(i -> input.charAt(input.length() - 1 - i))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}


