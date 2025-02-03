import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        // 1. 그래프 생성 (승리 정보 저장)
        boolean[][] graph = new boolean[n + 1][n + 1];

        // 2. 주어진 경기 결과를 그래프에 저장
        for (int[] result : results) {
            int winner = result[0]; // 이긴 선수
            int loser = result[1];  // 진 선수
            graph[winner][loser] = true; // winner가 loser를 이겼다는 정보 저장
        }

        // 3. 플로이드-워셜 알고리즘 실행 (승패 관계 전파)
        for (int mid = 1; mid <= n; mid++) { // 중간 선수
            for (int i = 1; i <= n; i++) { // 출발 선수
                for (int j = 1; j <= n; j++) { // 도착 선수
                    // i 선수가 mid 선수를 이기고, mid 선수가 j 선수를 이기면,
                    // i 선수는 j 선수도 이긴 것이므로 정보를 업데이트
                    if (graph[i][mid] && graph[mid][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        // 4. 각 선수의 정확한 순위를 알 수 있는지 확인
        int answer = 0; // 정확한 순위를 알 수 있는 선수 수

        for (int i = 1; i <= n; i++) { // 각 선수별로 확인
            int count = 0; // 다른 선수들과의 승패 관계를 확인하는 변수

            for (int j = 1; j <= n; j++) {
                // i 선수가 j 선수를 이겼거나, j 선수가 i 선수를 이겼다면
                if (graph[i][j] || graph[j][i]) {
                    count++;
                }
            }

            // 자신을 제외한 모든 선수들과 승패 관계를 알고 있다면 순위 결정 가능
            if (count == n - 1) {
                answer++;
            }
        }

        // 5. 결과 반환
        return answer;
    }
}