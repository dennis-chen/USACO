/*
ID: dennis.8
LANG: JAVA
TASK: zerosum
*/
import java.io.*;
import java.util.*;


class zerosum {

    public static PrintWriter out;
    public static int N;
    public static char[] ops = new char[]{' ','+','-'};

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        //read input
        N = Integer.parseInt(f.readLine());
        char[] sum = new char[N-1];
        genSums(sum, 0);
        out.close();
    }

    public static void genSums(char[] sum, int index){
        if(index == N-1){
            if(addsZero(sum)){
                printSum(sum);
            }
            return;
        }
        for(int i = 0; i < ops.length; i++){
            char[] newSum = sum.clone();
            newSum[index] = ops[i];
            genSums(newSum,index+1);
        }
        return;
    }

    public static boolean addsZero(char[] sum){
        ArrayList<Character> ops = new ArrayList<Character>();
        ArrayList<String> vals = new ArrayList<String>();
        vals.add("1");
        for(int i = 0; i < sum.length; i++){
            int num = i+2;
            switch(sum[i]){
                case '+':
                    ops.add(sum[i]);
                    vals.add(Integer.toString(num));
                    break;
                case '-':
                    ops.add(sum[i]);
                    vals.add(Integer.toString(num));
                    break;
                case ' ':
                    String lastVal = vals.get(vals.size()-1);
                    vals.set(vals.size()-1, lastVal+Integer.toString(num));
                    break;
            }
        }
        int accum = Integer.parseInt(vals.get(0));
        for(int i = 0; i < ops.size(); i++){
            char op = ops.get(i);
            int val = Integer.parseInt(vals.get(i+1));
            if(op == '+'){
                accum += val;
            } else {
                accum -= val;
            }
        }
        return accum == 0;
    }

    public static void printSum(char[] sum){
        String res = "1";
        int index = 0;
        for(int i = 2; i <= N; i++){
            res += sum[index];
            res += i + "";
            index++;
        }
        System.out.println(res);
        out.println(res);
    }

}
