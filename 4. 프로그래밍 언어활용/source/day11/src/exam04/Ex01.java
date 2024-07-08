package exam04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ex01 {
    public static void main(String[] args) {
        List<String> names = new LinkedList<>();
        names.add("이름3");
        names.add("이름2");
        names.add("이름1");
        names.add("이름5");
        names.add("이름4");
        System.out.println(names); // names.toString(); -> 담긴 값을 확인할 수 있도록 재정의

        for(int i = 0; i < names.size(); i++) {
            names.remove(i);
        } // length는 왜안되나요? - ArrayList는 기본배열의 크기가 10개로 정해져 있는데, 이것은 늘어나거나 줄어들 수 있다. 그러므로 size()를 활용해 크기를 알아낼 수 있다.

        for(int i = 0; i < names.size(); i++) {
        String name = names.get(i);
            System.out.println(name);
        } // 배열은 제거할 때 계속 새로운 배열이 만들어진다.
        // 새로운 배열이 만들어지면서 순서가 계속 앞으로 밀리면서 제대로 제거가 되지 않는다.
        // 그러므로 뒤에서 제거를 해야 한다.
    }
}
