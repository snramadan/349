import java.util.*;
import java.lang.*;
import java.io.*;

public class EditDistance 
{
   public static void main(String [] argv) 
   {
      try 
      {
         Scanner file = new Scanner(new File(argv[0]));
         String str1 = file.nextLine();
         String str2 = file.nextLine();
         Scanner scan = new Scanner(System.in);
         System.out.print("Print the alignment (y/n): ");
         int toggle = 0;
         if (scan.next().equals("y"))
         {
            toggle = 1;
         }
         editDistance(str1, str2, toggle);
      } 
      catch(Exception e)
      {
         System.out.println(e);
      }
   }

   public static void editDistance(String str1, String str2, int toggle) 
   {
      //create 2D arr with an extra 1 added to each dimension for row and col 
      int[][] arr = new int[str1.length()+1][str2.length() + 1];
      int i, j, x, y, m, sub, d;

      //setting first of all the rows = i*2 and all the other columns to i*2
      //to allow for empty string to string comparison
      for (i = 0; i <= str1.length(); i++) 
      {
         arr[i][0] = i * 2;
      }

      for (i = 0; i <= str2.length(); i++) 
      {
         arr[0][i] = i * 2;
      }

      
      for (i = 1; i <= str1.length(); i++) 
      {
         //inserting gaps in both strings and seeing if they are equal
         //calculate min of everything that was calulated
         for (j = 1; j <= str2.length(); j++) 
         {
            x = arr[i-1][j] + 2;
            y= arr[i][j-1] + 2;
            m = str1.charAt(i-1) == str2.charAt(j-1) ? 0 : 1;
            sub = arr[i-1][j-1] + m;
            arr[i][j] = Math.min(x, Math.min(sub, y));
         }
      }

      //calculate distance and print it out
      d = arr[str1.length()][str2.length()];
      System.out.println("Edit distance = " + d);
      //toggle condition checked 
      if (toggle == 1)
      {
         getAlignment(arr, str1, str2);
      }
   }
  
   public static void getAlignment(int[][] arr, String str1, String str2) 
   {
      ArrayList<Character> SC1 = new ArrayList<Character>();
      ArrayList<Character> SC2 = new ArrayList<Character>();
      ArrayList<Integer> vals = new ArrayList<Integer>();
      int r, c, curr, l, u, d, i;
      //row and col
      r = str1.length();
      c = str2.length();
      
      while (r > 0 && c > 0) 
      {
         //get values for up, left, diagonal and current
         curr = arr[r][c];
         l = arr[r][c - 1];
         u = arr[r - 1][c];
         d = arr[r - 1][c - 1];

         if (curr == d && (d+1 <= l+2 && d+1 <= u+2)) 
         {
            SC1.add(str1.charAt(--r));
            SC2.add(str2.charAt(--c));
            vals.add(0);
         }  

         else if (d+1 <= l+2 && d+1 <= u+2) 
         {
            SC1.add(str1.charAt(--r));
            SC2.add(str2.charAt(--c));
            vals.add(1);
         } 

         else if (l < u)  
         {
            SC1.add('-');
            SC2.add(str2.charAt(--c));
            vals.add(2);
         } 

         else 
         {
            SC1.add(str1.charAt(--r));
            SC2.add('-');
            vals.add(2);
         }
      }
       
      while (r > 0) 
      {
         SC1.add(str1.charAt(--r));
         SC2.add('-');
         vals.add(2);
      }
 
      while (c > 0) 
      {
         SC1.add('-');
         SC2.add(str2.charAt(--c));
         vals.add(2);
      }
    
      //print all the alignments
      for (i = vals.size() - 1; i >= 0; i--) 
      {
         System.out.println(SC1.get(i) + " " + SC2.get(i) + " " + vals.get(i));
      }
   }
}
