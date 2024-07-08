package exam04;

import java.util.ArrayList;

public class Ex02 {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("이름3");
        names.add("이름2");
        names.add("이름1");
        names.add("이름5");
        names.add("이름4");

        for (int i = names.size() -1; i >= 0; i--) {
            names.remove(i);
        }

    }
}
