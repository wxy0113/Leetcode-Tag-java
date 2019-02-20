import java.io.*;
import java.util.*;


// 城市连接问题，即MST
// 题目内容是这样的，给十几个城市供电，连接不同城市的花费不同，让花费最小同时连到所有的边。给出一系列connection类，里面是edge两端的城市名和它们之间的一个cost，找出要你挑一些边，把所有城市连接起来并且总花费最小。不能有环，最后所以城市要连成一个连通块。
// 输入:
// {"Acity","Bcity",1}

// ("Acity","Ccity",2}

// ("Bcity","Ccity",3}

// 输出：
// ("Acity","Bcity",1}

// ("Acity","Ccity",2}


class Connection{
    String node1;
    String node2;
    int cost;
    public Connection(String a, String b, int c){
        node1 = a;
        node2 = b;
        cost = c;
    }
}
class Solution {
  public static void main(String[] args) {
    ArrayList<Connection> connections = new ArrayList<>();
    connections.add(new Connection("A","B",6));
    connections.add(new Connection("B","C",4));
    connections.add(new Connection("C","D",5));
    connections.add(new Connection("D","E",8));
    connections.add(new Connection("E","F",2));
    connections.add(new Connection("B","F",10));
    connections.add(new Connection("E","C",9));
    connections.add(new Connection("F","C",7));
    connections.add(new Connection("B","E",3));
    connections.add(new Connection("A","F",16));
    List<Connection> res = getLowCost(connections);
    for (Connection c : res){
        System.out.println(c.node1 + " -> " + c.node2 + " " + c.cost);
    }
  }
  // Union Find + Kru: 从最小cost的线开始，合并两端，最终形成一个MST
  public static int num = 0;
  public static List<Connection> getLowCost(ArrayList<Connection> connections){
    List<Connection> res = new ArrayList<Connection>();
    if (connections == null || connections.size() == 0) return res;
    
    Map<String, Integer> map = new HashMap<>();
    
    Collections.sort(connections, new Comparator<Connection>() {
      public int compare(Connection c1, Connection c2) {
        return c1.cost-c2.cost;
      }
    });
    
    for (Connection c : connections) {
      if (unionFind(map, c.node1, c.node2)) {
        res.add(c);
      }
    }
    
    return res;
  }
  public static boolean unionFind(Map<String, Integer> map,
                                 String node1, String node2) {
    
    // 两端都不属于union，建立新union
    if (!map.containsKey(node1) && !map.containsKey(node2)) {
      map.put(node1, num);
      map.put(node2, num);
      num++;
      return true;
    }
    
    // 其中一端加入另一端
    if (!map.containsKey(node1) && map.containsKey(node2)) {
      int temp = map.get(node2);
      map.put(node1, temp);
      return true;
    }
    if (map.containsKey(node1) && !map.containsKey(node2)) {
      int temp = map.get(node1);
      map.put(node2, temp);
      return true;
    }
    
    // 合并两个union
    int a = map.get(node1);
    int b = map.get(node2);
    // 若两端属于一个union，则形成环
    if (a == b) {
      return false;
    }
    for (String s : map.keySet()) {
      if (map.get(s) == a) {
        map.put(s, b);
      }
    }
    return true;
  }
}