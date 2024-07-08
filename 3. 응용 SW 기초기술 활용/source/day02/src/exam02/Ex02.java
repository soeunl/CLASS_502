package exam02;

public class Ex02 {
    public static void main(String[] args) {
        int[] nums = new int[4]; // int형 변수 4개를 생성
        nums[0] = 10;
        nums[1] = 20;
        nums[2] = 30;
        for (int i = 0; i <=3; i++) {
            System.out.println(nums[i]); // 0, 1, 2, 3
        }

        System.out.println(nums[0]);
        System.out.println(nums[1]);
        System.out.println(nums[2]);
    }
}
