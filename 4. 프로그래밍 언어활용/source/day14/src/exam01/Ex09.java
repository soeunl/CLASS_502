package exam01;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Ex09 {
    public static void main(String[] args) {
        List<String> alpha = Arrays.asList("abc", "def", "ghi");

//        String[] upperAlpha = alpha.stream().map(String::toUpperCase).toArray(i -> new String[i]);
//        System.out.println(Array.toString(upperAlpha));

        String[] upperAlpha = alpha.stream().map(String::toUpperCase).toArray(String[]::new);
        System.out.println(Arrays.toString(upperAlpha));
    }
}
