import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] studentStatus; // 학생들의 상태를 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int studentCount = Integer.parseInt(st.nextToken()); // 학생 수
        int sleepingCount = Integer.parseInt(st.nextToken()); // 잠든 학생 수
        int calledCount = Integer.parseInt(st.nextToken()); // 호출된 학생 수
        int intervalCount = Integer.parseInt(st.nextToken()); // 구간 수

        studentStatus = new int[studentCount + 3];

        // 잠든 학생 표시
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sleepingCount; i++) {
            studentStatus[Integer.parseInt(st.nextToken())] = -1;
        }

        // 호출된 학생 처리
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < calledCount; i++) {
            markAwake(Integer.parseInt(st.nextToken()));
        }

        // 깨어 있는 학생의 누적 합 계산
        int[] awakeSum = new int[studentCount + 3];
        for (int i = 3; i < awakeSum.length; i++) {
            awakeSum[i] = awakeSum[i - 1] + (studentStatus[i] != 1 ? 1 : 0);
        }

        // 각 구간별 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intervalCount; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(awakeSum[end] - awakeSum[start - 1]).append("\n");
        }
        System.out.println(sb);
    }

    public static void markAwake(int x) {
        if (studentStatus[x] == -1) return; // 잠든 학생은 넘어감
        int multiplier = 2;
        for (int i = x; i < studentStatus.length; i = x * multiplier++) {
            if (studentStatus[i] == 0) {
                studentStatus[i] = 1;
                markAwake(i); // 재귀적으로 깨우기 시도
            }
        }
    }
}
