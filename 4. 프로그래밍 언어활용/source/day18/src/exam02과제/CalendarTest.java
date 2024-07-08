package exam02과제;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CalendarTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("년 월 입력 : ");
            String input = sc.nextLine(); // 2024 5

            if (input.equals("exit")) {
                System.out.println("종료합니다.");
                break;
            }

            try {
                String[] data = input.split("\\s+");
                int year = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);

                System.out.printf("---- %d년 %d월 ----%n", year, month);
                System.out.println("S  M  T  W  T  F  S");

                int[] days = getCalendar(year, month);
                for (int i = 0; i < days.length; i++) {
                    int day = days[i];
                    if (day > 0) {
                        System.out.printf("%02d  ", day);
                    } else {
                        System.out.print("    ");
                    }
                    if ((i+1) % 7 == 0) {
                        System.out.println();
                    }
                }
                System.out.println();

            } catch (Exception e) { // 숫자 형식 오류가 발생한 경우
                System.out.println("입력형식이 잘못되었습니다. 입력 예) 2024 5");
            }
        }
    }

    public static int[] getCalendar(int year, int month) {
        /**
         * 1. 매월의 1일이 어떤 요일에 시작하는지
         * 2. 매월의 종료요일이 무엇인지(28일인지 29일인지 30일인지 31일인지)
         */

        LocalDate today = LocalDate.now(); // 현재 날짜 정보 가져오기
        //System.out.println(today);

        LocalDate startDate = LocalDate.of(year, month, 1); // 시작 날짜 지정
//        System.out.println(startDate);

        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // 끝 날짜 지정
//        System.out.println(endDate);

        int day = endDate.getDayOfMonth(); // 끝 날짜의 일을 정수로 day 변수에 담기

        int yoil = startDate.getDayOfWeek().getValue() % 7; // 오늘의 요일 번호 가져오기
        int start = yoil * -1 + 1;
//        System.out.println(yoil);

        int[] days = IntStream.rangeClosed(start, endDate.getDayOfMonth()).toArray();

        return days;
    }
}

