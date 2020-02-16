package stream;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class FunctionalApp {
    public static void main(String[] args) {
        //PREDYKAT
        Predicate<String> longerThan5 = (str) -> str.length() > 5;
        System.out.println(longerThan5.test("ADAM"));
        List<String> names = Arrays.asList("ADAM", "KAROLINA","BOGDAN","WAWRZYNIEC");
        System.out.println(filter(names,longerThan5));
        System.out.println(filter(names,(str) -> str.startsWith("A")));
        System.out.println(filter(names, longerThan5.and((str) -> str.startsWith("K"))));
        //FUNCTIONS
        Function<String, Integer> len = (str) -> str.length();
        Function<Double, Double> metersToKilometers = meters -> meters/1000;
        System.out.println("5600 meters is " + metersToKilometers.apply(5600.0) + "km");
        //BIFUNCTIONS
        BiFunction<String, Integer, String> mulString = (str, count) -> {
            StringBuffer buffer = new StringBuffer();
            while(count-- > 0){
                buffer.append(str);
            }
            return buffer.toString();
        };
        System.out.println(mulString.apply("ABC", 10));
        //SUPPLIER
        Supplier<String> constStr = () -> "CONST";
        System.out.println(constStr.get());
        //CONSUMER
        Consumer<String> print = (str) -> System.out.println(str);
        print.accept("ALA");
    }

    static List<String> filter(List<String> list, Predicate<String> filter){
        List<String> result = new ArrayList<>();
        for(String item:list){
            if (filter.test(item)){
                result.add(item);
            }
        }
        return result;
    }
}
