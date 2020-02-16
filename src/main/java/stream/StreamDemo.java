package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("ADAM", "KAROLINA","BOGDAN","WAWRZYNIEC");
        names.stream().forEach((str) -> System.out.println(str));
        names.stream().forEach(System.out::println);
        List<String> nc = names.stream()
                .filter((str) -> str.endsWith("C"))
                .sorted()
                .collect(Collectors.toList());
        String allNames = names.stream()
                .map(str -> str.toLowerCase())
                .reduce("",(a, name) -> a + name + ", ");
        System.out.println(nc);
        System.out.println(allNames.substring(0, allNames.length()-2));
    }
}
