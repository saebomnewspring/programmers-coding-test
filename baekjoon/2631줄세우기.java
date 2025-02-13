import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sequenceLength = scanner.nextInt();

        int[] sequence = new int[sequenceLength];
        int[] lisLengths = new int[sequenceLength];

        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = scanner.nextInt();
        }

        int longestLIS = 1; // 최소한 자기 자신 하나만 있는 경우도 LIS가 될 수 있으므로 1로 초기화

        for (int i = 0; i < sequenceLength; i++) {
            lisLengths[i] = 1; // 각 원소는 최소한 자기 자신만으로 LIS 가능
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) { // 증가하는 부분 수열인지 확인
                    lisLengths[i] = Math.max(lisLengths[i], lisLengths[j] + 1);
                }
            }
            longestLIS = Math.max(longestLIS, lisLengths[i]); // 전체 LIS 길이 갱신
        }

        System.out.println(sequenceLength - longestLIS); // LIS에 포함되지 않는 원소 수 출력
    }
}
