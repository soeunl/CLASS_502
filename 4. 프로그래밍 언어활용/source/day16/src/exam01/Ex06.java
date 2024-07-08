package exam01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ex06 {
    public static void main(String[] args) {
        List<String> fruit = Arrays.asList("Apple", "Orange", "Melon", "Mango");
        String str = fruit.stream().collect(Collectors.joining(","));
        System.out.println(str);
        String str2 = fruit.stream().collect(Collectors.joining("#", "**", "^^"));
        System.out.println(str2);
    }
}
