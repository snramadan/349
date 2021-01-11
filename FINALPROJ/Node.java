import java.util.*;
import java.lang.*;
import java.io.*;

public class Node implements Comparable<Node>
{
   int l, wgt, val;
   double upper;
   ArrayList<Integer> arr;

   public Node()
   {
      this.l = 0;
      this.val = 0;
      this.wgt = 0;
      this.upper = 0;
      this.arr = new ArrayList<Integer>();
   }

   public Node(Node node)
   {   
      this.l = node.l;
      this.val = node.val;
      this.wgt = node.wgt;
      this.upper = 0;
      arr = new ArrayList<Integer>();
      arr.addAll(node.arr);
   }

   public void upperBound(int c, int n, Item[] obj)
   {   
      int curr = this.l;
      int temp = this.wgt;
      this.upper = this.val;
      while(curr < n && temp + obj[curr].wgt <= c)
      {
         temp += obj[curr].wgt;
         this.upper += obj[curr].val;
         ++curr;
      }

      if(curr == n)
      {
         --curr;
      }

      upper += (c - temp) * 1.0 * obj[curr].val / obj[curr].wgt;
   }

   public int compareTo(Node node)
   {
      return (int)(node.upper - this.upper);
   }
}
