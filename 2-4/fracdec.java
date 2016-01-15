/*
ID: dennis.8
LANG: JAVA
TASK: fracdec
*/
import java.io.*;
import java.util.*;

//NOTE: need special compile flags to avoid a stack overflow.
//Didn't have a problem running on USACO servers though.

class fracdec {

    public static PrintWriter out;
    public static int num;
    public static int den;
    public static HashMap<Integer,Integer> seen = new HashMap<Integer,Integer>();
    public static String answer;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        num = Integer.parseInt(st.nextToken());
        den = Integer.parseInt(st.nextToken());
        int ones = num/den;
        answer = ones+".";
        num = num%den;
        longDiv(num*10,new StringBuilder(),0);
        int printed = 0;
        for(int i = 0; i < answer.length(); i++){
            out.print(answer.charAt(i));
            printed++;
            if(printed == 76){
                out.print("\n");
                printed = 0;
            }
        }
        if(num == 0){
            out.print('0');
        }
        out.print("\n");
        out.close();
    }

    public static void longDiv(int dividend, StringBuilder sb, int depth){
        if(seen.containsKey(dividend)){
            int lastSeenIndex = seen.get(dividend);
            String res = sb.toString();
            res = res.substring(0,lastSeenIndex) + 
                "(" + res.substring(lastSeenIndex,res.length()) + ")";
            answer += res;
            return;
        }
        if(dividend == 0){
            answer += sb.toString();
            return;
        }
        seen.put(dividend,depth);
        if(num < dividend){
            int q = dividend/den;
            int r = dividend%den;
            sb.append(Integer.toString(q));
            longDiv(r*10,sb,depth+1);
        } else {
            sb.append("0");
            longDiv(dividend*10,sb,depth+1);
        }
    }

}
