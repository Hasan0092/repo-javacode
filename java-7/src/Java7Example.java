import java.util.ArrayList;
import java.util.List;

public class Java7Example {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("Hasan");
        names.add("Amir");
        names.add("Akram");

        for (String name : names) {
            System.out.println(name.toUpperCase());
        }
    }
}
