/*
ID: dennis.8
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;


class hamming {

    public static PrintWriter out;
    public static int setSize;
    public static int bitLen;
    public static int hamReq;
    public static ArrayList<Integer> res = new ArrayList<Integer>();

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st;
        //read input
        st = new StringTokenizer(f.readLine());
        setSize = Integer.parseInt(st.nextToken());
        bitLen = Integer.parseInt(st.nextToken());
        hamReq = Integer.parseInt(st.nextToken());
        res.add(0);
        while(res.size() < setSize){
            int largest = res.get(res.size()-1);
            int nextLargest = getNextLargest(largest);
            res.add(nextLargest);
        }
        System.out.println(res.toString());
        printRes();
        out.close();
    }

    public static void printRes(){
        for(int i = 0; i < res.size(); i++){
            if((i+1)%10 == 0){
                System.out.print(res.get(i)+"\n");
                out.print(res.get(i)+"\n");
            } else {
                if(i != res.size()-1){
                    System.out.print(res.get(i)+" ");
                    out.print(res.get(i)+" ");
                } else {
                    System.out.println(res.get(i));
                    out.println(res.get(i));
                }
            }
        }
    }

    public static int getNextLargest(int largest){
        for(int i = largest+1; i <= 255; i++){
            boolean isFar = true;
            //check if i is correct hamming dist away from all elem in set
            //so far
            for(int t: res){
                isFar = isFar && (hamDist(t,i) >= hamReq);
            }
            if(isFar){
                return i;
            }
        }
        return -1;
    }

    public static int hamDist(int b1, int b2){
        int dist = 0;
        int xor = b1^b2;
        for(int i = 0; i < 8; i++){
            if((xor&0x01) > 0){
                dist++;
            }
            xor = xor >> 1;
        }
        return dist;
    }

}
