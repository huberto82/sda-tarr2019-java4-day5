package optional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    static Optional<String> last(List<String> list){
        if (list.size() > 0){
            return Optional.ofNullable(list.get(list.size()-1));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("AA", "BB", "CC", "DD");
        last(list).ifPresent(System.out::println);
        Optional<String> result = last(list);
        System.out.println(result.orElse("Brak"));
        //Klasyczny kod testujący null'a
        if (result.isPresent()){
            System.out.println(result.get());
        } else {
            System.out.println("BRAK");
        }
    }
}
