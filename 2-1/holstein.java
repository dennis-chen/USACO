/*
ID: dennis.8
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;


class holstein {

    public static PrintWriter out;
    public static ArrayList<Integer> feedIndices = new ArrayList<Integer>();
    public static int vitaTypes;
    public static int feedTypes;
    public static int[][] feeds;
    public static int[] reqs;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        StringTokenizer st;
        //read input
        vitaTypes = Integer.parseInt(f.readLine()); 
        reqs = new int[vitaTypes];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < vitaTypes; i++){
            reqs[i] = Integer.parseInt(st.nextToken());
        }
        feedTypes = Integer.parseInt(f.readLine()); 
        feeds = new int[feedTypes][vitaTypes];
        for(int i = 0; i < feedTypes; i++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < vitaTypes; j++){
                feeds[i][j] = Integer.parseInt(st.nextToken());
            }
            feedIndices.add(i);
        }
        tryCombos();
        out.close();
    }

    public static void tryCombos(){
        for(int i = 1; i <= feedTypes; i++){
            ArrayList<ArrayList<Integer>> res = choose(feedIndices,i);
            for(ArrayList<Integer> a: res){
                int[] tryReq = new int[vitaTypes];
                for(Integer t: a){
                    for(int j = 0; j < vitaTypes; j++){
                        tryReq[j] += feeds[t][j];
                    }
                }
                boolean reqsMet = true;
                for(int k = 0; k < vitaTypes; k++){
                    reqsMet = reqsMet && (tryReq[k] >= reqs[k]);
                }
                if(reqsMet){
                    printRes(a);
                    return;
                }
            }
        }
        return;
    }

    public static void printRes(ArrayList<Integer> a){
        System.out.print(a.size());
        out.print(a.size());
        for(Integer t: a){
            System.out.print(" "+(t+1));
            out.print(" "+(t+1));
        }
        System.out.print("\n");
        out.print("\n");
    }

    public static ArrayList<ArrayList<Integer>> choose(ArrayList<Integer> nums, int numToChoose){
        //returns all possible sets of size numToChoose from nums
        if(numToChoose == 0){
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> empty = new ArrayList<Integer>();
            res.add(empty);
            return res;
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < nums.size()-numToChoose+1; i++){
            ArrayList<Integer> newNums = new ArrayList<Integer>(nums.subList(i+1,nums.size()));
            ArrayList<ArrayList<Integer>> prevChoose = choose(newNums,numToChoose-1);
            for(ArrayList<Integer> a: prevChoose){
                a.add(0,nums.get(i));
            }
            res.addAll(prevChoose);
        }
        return res;
    }

}
