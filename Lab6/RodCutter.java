import java.lang.*;
import java.util.*;
import java.io.*;

public class RodCutter
{
   public static int [] cut(int [] p, int l) 
   {
      int [] v = new int [l + 1];
      int [] c = new int [l + 1];
      int i, j, max;

      v[0] = 0;
      for (i = 1; i <= l; i++) 
      {
         max = -1;
      	 for (j = 0; j < i; j++) 
      	 {
            if (max < p[j] + v[i-j-1]) 
            {        
               max = p[j] + v[i-j-1];
               c[i] = j+1;
            }
      	 }
         
         System.out.println("total for length " + i + "\t= " + max);
         v[i] = max;
      }

      return cuts(c, l);
   }

   private static int [] cuts(int [] c, int l) 
   {
      int [] cuts = new int [l + 1]; 
      int left = l;
      int cl;

      while (left > 0) 
      {
         cl = c[left];
         cuts[cl]++;
         left = left - c[left];
      }

      return cuts;
   }
 
   private static void printCutsMade(int [] cuts) 
   {
      int i;
      for (i = 0; i < cuts.length; i++) 
      {
         if (cuts[i] != 0) 
      	 {
            System.out.println("Number of rods of length " + i + "\t= "+ cuts[i]);
      	 }
      }
    	
      System.out.println();
   }

   public static void main(String[] argv)
   {
      Scanner scan = new Scanner(System.in);
      System.out.print("File name: "); 
      String filename = scan.next();
      int test, i, l, j;
      int[] p;
      String[] temp;

      try
      {
         Scanner fileScanner = new Scanner(new File(filename));

	 test = Integer.parseInt(fileScanner.nextLine().split("\\s+")[0]);

	 for(i = 1; i <= test; ++i)
	 {
	    System.out.println("Case " + i + ": ");
	    System.out.println();

	    l = Integer.parseInt(fileScanner.nextLine().split("\\s+")[0]);
				
	    p = new int[l];
	    temp = fileScanner.nextLine().split(" ");
	    for(j = 0; j < l; ++j)
	    {
	       p[j] = Integer.parseInt(temp[j]);
	    }

	    printCutsMade(cut(p, l));
	 
	 }
      }  
 
      catch(Exception e)
      {
         System.out.println(e);
      } 
   }
}
