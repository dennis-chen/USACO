/*
ID: dennis.8
LANG: JAVA
TASK: runround
*/
import java.io.*;
import java.util.*;


class runround {

    public static PrintWriter out;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        //read input
        long M = Integer.parseInt(f.readLine());
        M++;
        while(!runRound(M)){
            M++;
        }
        System.out.println(M);
        out.println(M);
        out.close();
    }

    public static boolean runRound(long M){
        int[] run = run(M);
        if(run.length == 0){
            return false;
        }
        int length = run.length/9;
        int[] visited = new int[length];
        int index = 0;
        while(index <= run.length){
            if(visited[index%length] == 1){
                if(index%length != 0){
                    //we didn't end where we started
                    return false;
                }
                break;
            }
            visited[index%length] += 1;
            index += run[index];
        }
        for(int t: visited){
            if(t != 1){
                return false;
            }
        }
        return true;
    }

    public static int[] run(long val){
        String temp = Long.toString(val);
        int len = temp.length();
        int[] run = new int[len*9];
        //digs keeps track of unique digits
        boolean[] digs = new boolean[10];
        for (int i = 0; i < run.length; i++)
        {
            int t = temp.charAt(i%len) - '0';
            if(t == 0 || (digs[t] && (i < len))){
                return new int[]{};
            }
            digs[t] = true;
            run[i] = t;
        }
        return run;
    }

}
