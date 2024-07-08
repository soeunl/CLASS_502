package exam02과제;

import java.time.LocalDate;
import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("년 월 입력 : ");
            String input = sc.nextLine(); // 2024 5

            if (input.equals("exit")) {
                System.out.println("종료합니다.");
                break;
            }

            try {
                String[] data = input.split("\\s+");
                int year = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);

                System.out.printf("------ ♡%d년 %d월♡ ------%n", year, month);
                System.out.println("  일  월  화  수  목  금  토");
                getCalendar(year,month);
                System.out.println();
            } catch (Exception e) { // 숫자 형식 오류가 발생한 경우
                System.out.println("입력형식이 잘못되었습니다. 입력 예) 2024 5");
            }
        }
    }

    public static Object getCalendar(int year, int month) {
        /**
         * 1. 매월의 1일이 어떤 요일에 시작하는지
         * 2. 매월의 종료요일이 무엇인지(28일인지 29일인지 30일인지 31일인지)
         */
        LocalDate today = LocalDate.now(); // 현재 날짜 정보 가져오기
        //System.out.println(today);

        LocalDate startDate = LocalDate.of(year, month, 1); // 시작 날짜 지정
//        System.out.println(startDate);

        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // 끝 날짜 찾기
//        System.out.println(endDate);

        int day = endDate.getDayOfMonth(); // 끝 날짜의 일을 정수로 day 변수에 담기

        int yoil = startDate.getDayOfWeek().getValue(); // 시작 날짜의 요일 번호 가져오기
//        System.out.println(yoil);

        System.out.print("    ".repeat(yoil)); // 숫자 앞에 공백 넣기(요일의 숫자만큼)

        for (int i = 1; i <= day; i++) {

            if (i < 10) {
                System.out.print("  " + i + " ");
            }

            if (i >= 10) {
                System.out.print(" " + i + " ");
            }

            yoil++; // 시작 요일에서부터 요일의 숫자 증가
            if (yoil % 7 == 0) {
                System.out.println();
           }
        }
        return null;
    }
}
