package exam02;

import exam01.Transportation;

public class Ex03 {
    public static void main(String[] args) {
        Transportation trans = Transportation.BUS;
        int fare = trans.getFare();
        System.out.println(fare);
    }
}