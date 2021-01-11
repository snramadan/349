import java.util.ArrayList;

public class SubsetGen
{
   public SubsetGen()
   {
   }

   public ArrayList<String> getSubsets(String word)
   {
      ArrayList<String> subsets = new ArrayList<String>();
      ArrayList<String> temp = new ArrayList<String>();
      int len = word.length();
      int i, j;

      if (len > 0)
      {
         temp = getSubsets(word.substring(0, len-1));
         for (i = 0; i < temp.size(); i++)
         {
            subsets.add(temp.get(i));
         }
         for (j = 0; j < temp.size(); j++)
         {
            subsets.add(temp.get(j) + word.charAt(len-1));
         }
      }
      else
      {
         subsets.add("");
      }
      return subsets;
   }

   public ArrayList<String> getGrayCode(int n)
   {
       int i, j;

       //base case 
       if (n == 1)
       {
           ArrayList<String> gcodes = new ArrayList<String>();
       	   gcodes.add("0");
           gcodes.add("1");
           return gcodes;
       }

       //recursive call
       ArrayList<String> recur = getGrayCode(n-1);
       ArrayList<String> gcodes = new ArrayList<String>();
       for (i = 0; i < recur.size(); ++i)
       {
           gcodes.add(0, "1" + gcodes.get(i));
       }
       for (j = recur.size()  - 1; j >= 0; --j)
       {
           gcodes.add(0, "0" + recur.get(j));
       } 

       return gcodes;
   }
}
