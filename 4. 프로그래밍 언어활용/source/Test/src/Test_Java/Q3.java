package Test_Java;

public class Q3 {
    public static void main(String[] args) {
    for(int floor = 1; floor <= 5; floor++) {
        switch (floor) {
            case 1 :
                System.out.println("1층 약국");
                break;

            case 2 :
                System.out.println("2층 정형외과");
                break;

            case 3 :
                System.out.println("3층 피부과");
                break;

            case 4 :
                System.out.println("4층 치과");
                break;

            case 5 :
                System.out.println("5층 헬스클럽");
                break;    
                
            default :
                System.out.println("없는 층입니다");
            }
        }
    }
}
