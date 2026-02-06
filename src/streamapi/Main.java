package streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] args){

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,2,3,4,6,1));
//        numbers.stream().filter(n -> { System.out.println("Filtering " + n); return n > 2; }).forEach(System.out::println);

        numbers.stream().
                filter((Integer n) ->n%2==0).
                forEach(n-> System.out.println(n));

        List<String> names = new ArrayList<>(List.of("jame","joji","jojo","khema","koko"));

        names.stream().filter(s->s.startsWith("k")).map(s->s.toUpperCase()).forEach(s-> System.out.println(s));

        System.out.println(names);
    }
}
