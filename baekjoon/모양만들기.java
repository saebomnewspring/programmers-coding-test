import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int index;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        Queue<Pos> zeroQ = new LinkedList<>();
        isVisited = new boolean[N][M];
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    zeroQ.add(new Pos(i, j));
                }
            }
        }

        list = new ArrayList<>();
        list.add(0);
        int maxSize = 0;
        int newSize = 0;
        int mergeSize = 0;
        index = 1;
        int[][] nMap = copyMap();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1 && !isVisited[i][j]) {
                    newSize = bfs(i, j, nMap, index);
                    index++;
                    list.add(newSize);
                }
            }
        }

        while(!zeroQ.isEmpty()) {
            Pos p = zeroQ.poll();

            int curX = p.x;
            int curY = p.y;

            mergeSize = mergeBfs(curX, curY, nMap);

            maxSize = Math.max(maxSize, mergeSize);
        }

        System.out.println(maxSize);
    }

    static int bfs(int x, int y, int[][] nMap, int index) {
        Queue<Pos> que = new LinkedList<>();

        int size = 1;
        que.add(new Pos(x, y));
        isVisited[x][y] = true;
        nMap[x][y] = index;

        while(!que.isEmpty()) {
            Pos p = que.poll();

            int curX = p.x;
            int curY = p.y;

            for(int t=0; t<4; t++) {
                int nx = curX + dx[t];
                int ny = curY + dy[t];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(!isVisited[nx][ny] && nMap[nx][ny] == 1) {
                    que.add(new Pos(nx, ny));
                    isVisited[nx][ny] = true;
                    nMap[nx][ny] = index;
                    size++;
                }
            }

        }

        return size;
    }

    static int mergeBfs(int x, int y, int[][] nMap) {
        HashSet<Integer> set = new HashSet<>();
        int mergeSize = 0;

        for(int t=0; t<4; t++) {
            int nx = x + dx[t];
            int ny = y + dy[t];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            set.add(nMap[nx][ny]);
        }

        for(int idx : set) {
            mergeSize += list.get(idx);
        }

        return mergeSize + 1;
    }

    static int[][] copyMap() {
        int[][] nMap = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                nMap[i][j] = map[i][j];
            }
        }

        return nMap;
    }

    static class Pos{
        int x, y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

}
