/*
ID: dennis.8
LANG: JAVA
TASK: nocows
*/
import java.io.*;
import java.util.*;


class nocows {

    public static PrintWriter out;
    public static long[][] ped;
    //num nodes
    public static int N;
    //depth
    public static int K;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        //read input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ped = new long[K+1][N+1];
        countPed();
        //System.out.println(Arrays.deepToString(ped));
        System.out.println(ped[K][N]);
        out.println(ped[K][N]);
        out.close();
    }

    public static void countPed(){
        ped[0][0] = 1;
        ped[1][1] = 1;
        for(int depth = 2; depth <= K; depth++){
            for(int nodes = 1; nodes <= N; nodes+=2){
                int leftDepth = depth-1;
                int maxNodes = nodes-1;
                for(int i = 1; i <= maxNodes; i++){
                    long makeLeft = ped[leftDepth][i];
                    int nodesLeft = maxNodes - i;
                    //number of ways to make a right subtree
                    for(int rightDepth = 1; rightDepth <= leftDepth; rightDepth++){
                        long makeRight = ped[rightDepth][nodesLeft];
                        if(rightDepth == leftDepth){
                            //considering same group of possibilities
                            //for left and right subtrees, avoid
                            //counting mirrors
                            ped[depth][nodes] += makeLeft*makeRight;
                        } else {
                            ped[depth][nodes] += 2*makeLeft*makeRight;
                        }
                    }
                }
                ped[depth][nodes] %= 9901;
            }
        }
    }

}
