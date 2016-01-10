/*
ID: dennis.8
LANG: JAVA
TASK: lamps
*/
import java.io.*;
import java.util.*;


class lamps {

    public static PrintWriter out;
    public static ArrayList<Integer> on = new ArrayList<Integer>();
    public static ArrayList<Integer> off = new ArrayList<Integer>();
    public static int[] switchStart;
    public static int[] switchInt;
    public static int N;
    public static int C;
    public static ArrayList<Res> res = new ArrayList<Res>();

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        //read input
        N = Integer.parseInt(f.readLine());
        C = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int indexOn = Integer.parseInt(st.nextToken());
        while(indexOn != -1){
            on.add(indexOn);
            indexOn = Integer.parseInt(st.nextToken()); 
        }
        st = new StringTokenizer(f.readLine());
        int indexOff = Integer.parseInt(st.nextToken());
        while(indexOff != -1){
            off.add(indexOff);
            indexOff = Integer.parseInt(st.nextToken()); 
        }
        //generate all 16 combos
        switchStart = new int[]{1,1,2,1};
        switchInt = new int[]{1,2,2,3};
        boolean[] lamps = new boolean[N+1];
        genCombos(lamps, 0, 0);
        if(res.size() == 0){
            System.out.println("IMPOSSIBLE");
            out.println("IMPOSSIBLE");
        } else {
            Collections.sort(res, new CustomComparator());
            for(Res r: res){
                System.out.println(r.s);
                out.println(r.s);
            }
        }
        out.close();
    }

    static class CustomComparator implements Comparator<Res> {
        @Override
        public int compare(Res r1, Res r2){
            String s1 = r1.s;
            String s2 = r2.s;
            for(int i = 0; i < s1.length(); i++){
                if(s1.charAt(i)=='0' && s2.charAt(i) == '1'){
                    return -1;
                } else if(s1.charAt(i)=='1' && s2.charAt(i) == '0'){
                    return 1;
                }
            }
            return 0;
        }
    }

    public static void genCombos(boolean[] lamps, int flipped, int index){
        if(index == 4){
            if(flipped <= C && (flipped%2 == C%2) && valid(lamps)){
                printOut(lamps);
            }
            return;
        }
        boolean[] switched = lamps.clone();
        flip(switchStart[index],switchInt[index],switched);
        genCombos(switched, flipped+1, index+1);
        boolean[] not = lamps.clone();
        genCombos(not, flipped, index+1);
    }

    public static void printOut(boolean[] lamps){
        String bin = "";
        for(int i = 1; i < lamps.length; i++){
            int pwr = lamps.length-1-i;
            if(lamps[i]){
                bin += "0";
            } else {
                bin += "1";
            }
        }
        res.add(new Res(bin));
    }

    static class Res{
        String s;
        public Res(String s){
            this.s = s;
        }
    }

    public static boolean valid(boolean[] lamps){
        for(Integer t: on){
            if(lamps[t]){
                return false;
            }
        }
        for(Integer t: off){
            if(!lamps[t]){
                return false;
            }
        }
        return true;
    }

    public static void flip(int start, int interval, boolean[] lamps){
        for(int i = start; i < lamps.length; i+=interval){
            lamps[i] = !lamps[i];
        }
    }

}
