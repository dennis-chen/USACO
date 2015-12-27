/*
ID: dennis.8
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    HashMap<String,Integer> moneyGained = new HashMap<String,Integer>();
    int groupSize = Integer.parseInt(f.readLine());
    String[] names = new String[groupSize];
    //initialize dictionaries for keeping track of money spent
    for(int i = 0; i < groupSize; i++){
        String name = f.readLine();
        names[i] = name;
        moneyGained.put(name,0);
    }
    for(int i = 0; i < groupSize; i++){
        String giver = f.readLine();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int money = Integer.parseInt(st.nextToken());
        moneyGained.put(giver, moneyGained.get(giver) - money);
        int giftsGiven = Integer.parseInt(st.nextToken());
        int individualGift = giftsGiven == 0 ? 0 : money/giftsGiven;
        int leftOver = giftsGiven == 0? money : money % giftsGiven;
        moneyGained.put(giver, moneyGained.get(giver) + leftOver);
        for(int j = 0; j < giftsGiven; j++){
            String reciever = f.readLine();
            moneyGained.put(reciever, moneyGained.get(reciever) + individualGift);
        }
    }
    for(String name: names){
        int val = moneyGained.get(name);
        String res = name + " " + val;
        out.println(res);
    }
    out.close();
  }
}
