/*
ID: dennis.8
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;


class concom {

    public static PrintWriter out;
    public static int[][] t;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("concom.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        //initialize ownership array
        t = new int[101][101];
        //read input
        int N = Integer.parseInt(f.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(f.readLine());
            int owner = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int pcnt = Integer.parseInt(st.nextToken());
            t[owner][d] = pcnt;
        }
        findPairs();
        out.close();
    }

    public static void findPairs(){
        for(int i = 1; i <= 100; i++){
            int[] ownership = new int[101];
            boolean[] considered = new boolean[101];
            ownership[i] = 100;
            considered[i] = true;
            addTo(ownership,t[i]);
            boolean found = true;
            while(found){
                found = false;
                for(int j = 1; j <= 100; j++){
                    if(!considered[j] && ownership[j] > 50){
                        considered[j] = true;
                        found = true;
                        addTo(ownership,t[j]);
                    }
                }
            }
            for(int j = 1; j <= 100; j++){
                if(j != i && ownership[j] > 50){
                    System.out.println(i+" "+j);
                    out.println(i+" "+j);
                }
            }
        }
        return;
    }

    public static void addTo(int[] a, int[] b){
        //element wise addition of a and b, putting
        //results into a
        assert(a.length == b.length);
        for(int i = 0; i < a.length; i++){
            a[i] += b[i];
        }
    }

}
