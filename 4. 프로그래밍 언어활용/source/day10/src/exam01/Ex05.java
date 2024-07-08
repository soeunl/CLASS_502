package exam01;

public class Ex05 {
    public static void main(String[] args) {
        String trans1 = "BUS";

//        Transportation BUS = Enum.valueOf(Transportation.class, tran1);
        // 정보 확인을 위해 Class class가 필요하다
        // System.out.println();

        Transportation Bus = Transportation.valueOf(trans1);
    }
}
