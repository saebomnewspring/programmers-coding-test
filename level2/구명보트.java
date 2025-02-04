import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public int solution(int[] people, int limit) {
        // 사람들의 몸무게를 오름차순으로 정렬하기
        Arrays.sort(people);
        // 포인터 두 개를 초기화: 하나는 배열의 시작에, 다른 하나는 끝에 위치
        int left = 0, right = people.length - 1;
        int boats = 0; // 사용된 보트의 수

        // 양 끝에서 중앙으로 진행하면서 보트 수 계산하기
        while (left <= right) {
            // 가장 가벼운 사람과 가장 무거운 사람의 합이 제한 이하인 경우 함께 태운다
            if (people[left] + people[right] <= limit) {
                left++; // 가벼운 사람을 보트에 태우고 인덱스를 옮김
            }
            right--; // 무거운 사람은 항상 보트에 태우고 인덱스를 옮김
            boats++; // 보트 하나 추가 사용하기
        }
        return boats; // 계산된 보트 수 반환한다
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 입력을 위한 스캐너 객체 생성

        System.out.print("Enter the number of people: "); // 사용자에게 사람 수 입력 요청
        int n = scanner.nextInt(); // 입력 받은 사람 수
        int[] people = new int[n]; // 사람들의 몸무게를 저장할 배열

        System.out.println("Enter the weights of each person:"); // 몸무게 입력 안내
        for (int i = 0; i < n; i++) {
            people[i] = scanner.nextInt(); // 각 사람의 몸무게를 배열에 저장
        }

        System.out.print("Enter the limit of the boat: "); // 보트의 용량 입력 요청
        int limit = scanner.nextInt(); // 입력 받은 보트의 최대 용량

        Solution solution = new Solution(); // Solution 객체 생성
        int result = solution.solution(people, limit); // 보트 수 계산
        System.out.println(result); // 필요한 최소 보트 수 출력

        scanner.close(); // 스캐너 자원 해제
    }
}
