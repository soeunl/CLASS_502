package exam02;

public class Ex04 {
    public static void main(String[] args) {
        A ad = new D();

        A ac = new C();
        
        // C c = (C)ad; 오류 발생

        if (ad instanceof C) {
            C c = (C) ad;
        } // 체크하는 용도

        if (ac instanceof C) {
            C cc = (C) ac;
            System.out.println(cc.numC);
        }
    }
}
