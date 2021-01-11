import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Arrays;

public class knapsack
{
   public static void branchAndBound(int c, int n, int[] vals, int[] w, int t)
   {
      long s = System.currentTimeMillis();
      boolean finish = false;
      Node root = new Node();
      Node b = new Node();
      Item[] items = new Item[n];
      int i, temp;
      
      for (i = 0; i < n; ++i)
      {
         items[i] = new Item(vals[i], w[i], i + 1);
      }

      Arrays.sort(items, new Item.ItemComparator());
      root.upperBound(c, n, items);
      PriorityQueue<Node> que = new PriorityQueue<Node>();
      que.offer(root);

      while (!que.isEmpty() && !finish)
      {
         Node node = que.poll();

         if (System.currentTimeMillis() - s > t * 60000)
         {
            finish = true;
            System.out.println("Reached timeout of " + t + " minutes.");
         }    

         else if(node.upper > b.val)
         {
            Node l = new Node(node);

            temp = node.l;

            ++ l.l;

            l.wgt += items[temp].wgt;

            if (l.wgt <= c)
            {
               l.arr.add(items[temp].num);
               l.val += items[temp].val;

               if (l.l < n)
               {
                  l.upperBound(c, n, items);
               }

               if (l.val > b.val)
               {
                  b = l;
               }

               if (l.upper > b.val && l.l < n)
               {
                  que.offer(l);
               }
            }

            Node node2 = new Node(node);

            ++node2.l;

            if(node2.l < n)
            {
               node2.upperBound(c, n, items);
               if(node2.upper > b.val)
               {
                  que.offer(node2);
               }
            }
         }
      }
      BBprint(b.val, b.wgt, b.arr);
   }

   public static void BBprint(int val, int w, ArrayList<Integer> arr)
   {
      System.out.println("Using Branch and Bound the best feasible solution found:  Value " + val + " Weight " + w);
      Collections.sort(arr);
      for (int i : arr)
      {
         System.out.print(i + " ");
      }

      System.out.println();
   }

   public static void dynamic(int c, int n, int[] val, int[] w)
   {
      int [][] table = new int[n + 1][c + 1];
      int i, j;
      for (i = 0; i <= n - 1; ++i)
      {
         for (j = 0; j <= c; ++j)
         {
            if ( w[i] <= j)
            {
               table[i + 1][j] = Math.max(table[i][j], table[i][j - w[i]] + val[i]);
            }
            else
            {   
               table[i + 1][j] = table[i][j];
            }
         }
      } 
      trace(c, n, val, w, table);
   }

   public static void trace(int c, int n, int[] val, int[] w, int[][] table)
   {
      int pres  = n;
      int empt  = c;
      int Vtot = 0;
      int Wtot = 0;

      ArrayList<Integer> arr = new ArrayList<Integer>();

      while (pres > 0 && empt >= 0)
      {
         if (table[pres][empt] != table[pres - 1][empt])
         {
            arr.add(pres--);
            Vtot += val[pres];
            Wtot += w[pres];
            empt -= w[pres];
         }
         else
         {   
            --pres;
         }
      }

      DYprint(Vtot, Wtot, arr);
   }

   public static void DYprint(int val, int w, ArrayList<Integer> arr)
   {
      Collections.sort(arr);
      System.out.println("Dynamic Programming solution:  Value " + val + ", Weight " + w);
      for (int i : arr)
      {
         System.out.print(i + " ");
      }
      System.out.println();
   }

   public static void greedy(int c, int n, int[] val, int[] w)
   {
      Item [] items = new Item[n];
      int cap, wgt, max, i;
      ArrayList<Integer> arr = new ArrayList<Integer>();
      cap = c;
      wgt = 0;
      max = 0;

      for (i = 0; i < n; ++i)
      {   
         items[i] = new Item(val[i], w[i], i + 1);
      }

      Arrays.sort(items, new Item.ItemComparator());

      for (Item curr : items)
      {
         if (cap > curr.wgt)
         {
            cap -= curr.wgt;
            max += curr.val;
            wgt += curr.wgt;
            arr.add(curr.num);
         }
         if (cap <= 0)
         {
            break;
         }
      }

      Collections.sort(arr);

      GRprint(max, wgt, arr);
   }

   private static void GRprint(int max, int w, ArrayList<Integer> arr)
   {
      System.out.println("Greedy solution (not necessarily optimal):  Value " + max + ", Weight " + w);
      for (Integer op : arr)
      {
         System.out.print(op + " ");
      }
      System.out.println();
   }

   public static void brute(int c, int n, int[] val, int[] w)
   {
      int max, wgt, cv, cw, i; 
      String opt = "";
      ArrayList<String> arr = binStr(n, "", new ArrayList<String>());
      max = 0;
      wgt = 0;
      cw = 0;
      cv = 0;

      for (String s : arr)
      {
         cv = 0;
         cw = 0;

         for (i = 0; i < n; ++i)
         {
            if (s.charAt(i) == '1')
            {
               cv += val[i];
               cw += w[i];
            }
         }
         if (cw <= c && cv > max)
         {
            max = cv;
            wgt = cw;
            opt = s;
         }
      }

      BRprint(n, max, wgt, opt);
   }

   private static void BRprint(int n, int max, int w, String opt)
   {
      int i;
      System.out.println("Using Brute force the best feasible solution found:  Value " + max + ", Weight " + w);
      for (i = 0; i < n; ++i)
      {
         if (opt.charAt(i) == '1')
         {
            System.out.print((i + 1) + " ");
         }
      }
      System.out.println();
   }

   private static ArrayList<String> binStr(int l, String in, ArrayList<String> str)
   {
      if (l < 1)
      {
         str.add(in);
      }
      else
      {
         binStr(l - 1, in + "0", str);
         binStr(l - 1, in + "1", str);
      }
      return str;
   }

   private static Scanner scan(String[] argv)
   {
      Scanner input;
      if(argv.length < 1)
      {
         System.out.println("File name not inputted, exitting program.");
         input = null;
      }
      try
      {
         input = new Scanner(new File(argv[0]));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found, exiting program.");
         input = null;
      }
      return input;
   }

   private static void print(int[] arr)
   {
      int i;
      for (i = 0; i < arr.length; ++i)
      {
         System.out.print(arr[i] + " ");
      }
      System.out.println();
   }

   public static void main(String[] argv)
   {
      int n, count, c, time, i, limit;
      int[] val, w;
      Scanner scan = scan(argv);
      String[] l;

      time = 1;
      if (argv.length > 1)
      {
         time = Integer.parseInt(argv[1]);
      }

      n = Integer.parseInt(scan.next());
      val = new int[n];
      w = new int[n];
      scan.nextLine();

      for (i = 0; i <= n - 1; ++i)
      {
         l = scan.nextLine().split("\\s+");
         limit = l.length - 1;

         val[i] = Integer.parseInt(l[limit - 1]);
         w[i] = Integer.parseInt(l[limit]);
      }

      c = Integer.parseInt(scan.next());

      if(n <= 25)
      {
         brute(c, n, val, w);
      }

      greedy(c, n, val, w);
      dynamic(c, n, val, w);
      branchAndBound(c, n, val, w, time);
   }
}
