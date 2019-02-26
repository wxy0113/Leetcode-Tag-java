import java.io.*;
import java.util.*;

// 大概就是给一堆未排序好的顾客购买记录(cistomID, itemID, timeStamp(购买时间))，
// 然后要求出现次数最多的，连续3次购买的物品(需要是同一个顾客)。

// 举个例子，(1, A, 0), (1, B, 2), (1, C, 4), (1,D, 5), (2, A, 0), (2, B, 2), (2, C, 4).

// 那么A-B-C出现过两次，B-C-D出现过一次。
class Record {
  int customerID;
  String itemID;
  int timeStamp;
  public Record(int a, String b, int c) {
    customerID = a;
    itemID = b;
    timeStamp = c;
  }
  
}
class Solution {
  public static void main(String[] args) {
    Record[] list = new Record[7];
    // (1, A, 0), (1, B, 2), (1, C, 4), (1,D, 5), (2, A, 0), (2, B, 2), (2, C, 4)
    list[0] = new Record(1, "A", 0);
    list[1] = new Record(1, "B", 2);
    list[2] = new Record(1, "C", 4);
    list[3] = new Record(1, "A", 5);
    list[4] = new Record(2, "A", 0);
    list[5] = new Record(2, "B", 1);
    list[6] = new Record(2, "C", 3);
    System.out.print(mostBuy(list));
                         
  }
  
  public static String mostBuy(Record[] list) {
    Arrays.sort(list, new Comparator<Record>() {
      public int compare(Record r1, Record r2) {
        return r1.timeStamp-r2.timeStamp;
      }
    });
    System.out.print(list[0].timeStamp);
    Map<Integer, StringBuilder> map = new HashMap<>();
    for (Record r : list) {
      if (!map.containsKey(r.customerID)) {
        map.put(r.customerID, new StringBuilder());
      }
      
      map.get(r.customerID).append(r.itemID);
    }
    
    Map<String, Integer> cnt = new HashMap<>();
    
    int max = 0;
    String res = "";
    for (int id : map.keySet()) {
      String s = map.get(id).toString();
      if (s.length() < 3) continue;
      for (int i = 0; i <= s.length()-3; i++) {
        String sub = s.substring(i, i+3);
        cnt.put(sub, cnt.getOrDefault(sub, 0)+1);
        if (cnt.get(sub) > max) {
          max = cnt.get(sub);
          res = sub;
        }
      }
    }
    return res;
  }
}