import java.util.*;
import java.lang.*;
import java.io.*;

public class Item
{
   int wgt, val, num;

   public Item(int val, int wgt, int num)
   {
      this.val = val;
      this.wgt = wgt;
      this.num = num;
   }

   public static class ItemComparator implements Comparator<Object>
   {
      public int compare(Object obj1, Object obj2)
      {
         Item it1 = (Item)obj1;
         Item it2 = (Item)obj2;
         double x = it1.val / (it1.wgt + 1.0);
         double y = it2.val / (it2.wgt + 1.0);

         if (x < y)
         {
            return 1;
         }
         else if (x > y)
         {
            return -1;
         }
         else
         {
            return 0;
         }
      }

      public boolean compare(Object obj)
      {
         boolean answer = true;
         if (obj == null || !obj.getClass().equals(this.getClass()))
         {        
            answer = false;
         }
         return answer;
      }
   }
}
