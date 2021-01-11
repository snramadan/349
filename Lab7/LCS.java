import java.util.*;
import java.util.Arrays;

public class LCS
{  

   /*public static void  main(String[] args) 
   { 
      String str1 = "AGGTAB"; 
      String str2 = "GXTXAYB"; 
      String a = getLCS(str1, str2); 
      System.out.println(a);
   }*/

   public static String getLCS (String str1, String str2)
   {
      int l1 = str1.length();
      int l2 = str2.length();
      int[][] temp = new int[l1+1][l2+1];
      int i, j, x;
      char[] LCS;


      //finds longest common sequence length
      for(i = 0; i <= l1; i++)
      {
         for(j= 0; j <= l2; j++)
         {
            if(i==0 || j==0)
            {
               temp[i][j]=0;
            }
            else if(str1.charAt(i-1) == str2.charAt(j-1))
            {
               temp[i][j] = 1 + temp[i-1][j-1];
            } 
            else 
            {
               temp[i][j] = Math.max(temp[i-1][j], temp[i][j-1]);
            } 
         }
      }
 
      // x  os the longest sequence
      x = temp[l1][l2];
      LCS = new char[x]; 
      //LCS[x] = ' ';
      i = l1;
      j = l2; 

      //loops through strings to find the actual longest string
      while (i > 0 && j > 0) 
      {
         if (str1.charAt(i-1) == str2.charAt(j-1)) 
         { 
            LCS[x-1] = str1.charAt(i-1);  
            i--; 
            j--; 
            x--;      
         }
         else if (temp[i-1][j] < temp[i][j-1])
         { 
            j--; 
         }
         else
         {
            i--;
         } 
      }      

      String lcs = Character.toString(LCS[0]);
      //gets string of the char array 
      for (i = 1; i < LCS.length; i++)
      {
         lcs = lcs + Character.toString(LCS[i]);
      }
      return lcs;
   }
}
