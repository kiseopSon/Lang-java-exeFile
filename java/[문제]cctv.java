import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
 * 예시
 * 4 6
	0 0 0 0 0 0
	0 0 0 0 0 0
	0 0 1 0 6 0
	0 0 0 0 0 0
 * */
class Pair {
    int x;
    int y;
    int index;
    boolean u;
    boolean d;
    boolean l;
    boolean r;

    Pair(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }
    public void setDATA(boolean[] arr) {
        this.u = arr[0];
        this.d = arr[1];
        this.l = arr[2];
        this.r = arr[3];
    }
}

public class cctv {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int check = 0;

    public static void main(String args[]) throws IOException {
        run();
        br.close();
        bw.close();
    }

    static int N, M;
    static int[][] arr;
    static boolean[][] one = new boolean[][]{{true,false,false,false},{false,true,false,false},
    																	{false,false,true,false},{false,false,false,true}};
    static boolean[][] two = new boolean[][]{{true,true,false,false},{false,false,true,true}};
    static boolean[][] three = new boolean[][]{{true,false,true,false},{true,false,false,true},
    																	{false,true,true,false},{false,true,false,true}};
    static boolean[][] four = new boolean[][]{{true,true,true,false},{true,true,false,true},
    																	{true,false,true,true},{false,true,true,true}};
    static boolean[] five = new boolean[]{true,true,true,true};
    
    static int MIN = Integer.MAX_VALUE;
    public static void run() throws IOException {
    	Pair pair = new Pair(2, 2, 1);
    	pair.setDATA(one[2]);
    	System.out.println(pair.u);
    	System.out.println(pair.d);
    	System.out.println(pair.l);
    	System.out.println(pair.r);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        List<Pair> list = new ArrayList<Pair>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] >= 1 && arr[i][j] < 6) { //1에서 5까지 = 6이라서 2번째줄까지 무시 3번재 1을 발견, 위에서 한줄 씩 읽어드림.
                    list.add(new          Pair(i,j,arr[i][j])      ); //1이 1개밖에 없어서, 전체적으로 1번만돔
                }
            }
        }
        //---여기까진 문제 나열
        dfs(list,0);//문제를 제공해서 풀이 접근전, 1번의 위치 [2,2] 와 값 1 위아오왼 전부  false으로 시작
        //한번만 돈 dfs는 첫번째로 결과가 나온 값이 최종값으로 출력됨.
        bw.write(MIN+"\n");//결과값
    }
    
    public static void dfs(List<Pair> list, int cnt) {
    	System.out.println("dfs list 체크 : " + list.size());//같은곳을 계속 파고듬. 예시기준 : 2번 1번 1번 1번
        if (cnt == list.size()) {
        	System.out.println("dfs 회전 횟수 체크 : "+ (++cctv.check));
            int[][] copy = new int[N][M];
            for (int i = 0; i < list.size(); i++) {
                Pair p = list.get(i);
                check(copy,p);
            }
            
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) 
                    if (copy[i][j] == 0 && arr[i][j] != 6) ans++;
            }
            MIN = Math.min(MIN,ans);
            return;
        }
        
        Pair p = list.get(cnt);
        switch(p.index) {
        case 1:
            for (int i = 0; i < 4; i++) {
                p.setDATA(one[i]);
                dfs(list,cnt+1);
            }
            break;
        case 2:
            for (int i = 0; i < 2; i++) {
                p.setDATA(two[i]);
                dfs(list,cnt+1);
            }
            break;
        case 3:
            for (int i = 0; i < 4; i++) {
                p.setDATA(three[i]);
                dfs(list,cnt+1);
            }
            break;
        case 4:
            for (int i = 0; i < 4; i++) {
                p.setDATA(four[i]);
                dfs(list,cnt+1);
            }
            break;
        case 5:
            p.setDATA(five);
            dfs(list,cnt+1);
            break;
        }   
    }//dfs
    
    public static void check(int[][] copy, Pair p) {
        if (p.u) {
            for (int i = p.x; i >= 0; i--) {
                copy[i][p.y] = 1;
                if (arr[i][p.y] == 6) break;
            }
        } 
        if (p.d) {
            for (int i = p.x; i < N; i++) {
                copy[i][p.y] = 1;
                if (arr[i][p.y] == 6) break;
            }
        }
        if (p.l) {
            for (int i = p.y; i >= 0; i--) {
                copy[p.x][i] = 1;
                if (arr[p.x][i] == 6) break;
            }
        }
        if (p.r) {
            for (int i = p.y; i < M; i++) {
                copy[p.x][i] = 1;
                if (arr[p.x][i] == 6) break;
            }
        }
    }//check
}