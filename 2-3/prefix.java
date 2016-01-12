/*
ID: dennis.8
LANG: JAVA
TASK: prefix
*/
import java.io.*;
import java.util.*;

class prefix {

    public static PrintWriter out;
    public static ArrayList<String> prims = new ArrayList<String>();
    public static String seq;
    public static boolean[] canMake;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        //input
        StringTokenizer st = new StringTokenizer(f.readLine());
        String primitive = st.nextToken();
        while(!primitive.equals(".")){
            prims.add(primitive);
            if(!st.hasMoreTokens()){
                st = new StringTokenizer(f.readLine());
            }
            primitive = st.nextToken();
        }
        String temp = f.readLine();
        //NOTE: using += is a bottleneck for large inputs. Use stringbuilder
        //instead, Dennis you doozy. :')
        StringBuilder sb = new StringBuilder();
        while(temp != null){
            sb.append(temp);
            temp = f.readLine();
        }
        seq = sb.toString();
        //dp part
        canMake = new boolean[seq.length()+1];
        int maxLen = tryPrims() + 1;
        //output
        System.out.println(maxLen);
        out.println(maxLen);
        out.close();
    }

    public static int tryPrims() {
        int lastTrue = -1;
        int seqLen = seq.length();
        for(String p: prims){
            String temp = seq.substring(0,p.length());
            if(temp.equals(p)){
                canMake[p.length()-1] = true;
            }
        }
        for(int i = 0; i < seqLen; i++){
            if(canMake[i]){
                for(String p: prims){
                    int len = p.length();
                    if(i+len < seqLen){
                        String temp = seq.substring(i+1,i+len+1);
                        if(temp.equals(p)){
                            canMake[i+len] = true;
                            lastTrue = i+len;
                        }
                    }
                }
            }
        }
        return lastTrue;
    }

}
