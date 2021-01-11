import java.util.ArrayList;

public class SubsetGrayTest
{
   public static void main(String[] args)
   {
      ArrayList<String> answer = new ArrayList<String>();
      answer.add("0000");
      answer.add("0001");
      answer.add("0011");
      answer.add("0010");
      answer.add("0110");
      answer.add("0111");
      answer.add("0101");
      answer.add("0100");
      answer.add("1100");
      answer.add("1101");
      answer.add("1111");
      answer.add("1110");
      answer.add("1010");
      answer.add("1011");
      answer.add("1001");
      answer.add("1000");

      SubsetGen gener = new SubsetGen();
      ArrayList<String> subsets = gener.getGrayCode(4);
      boolean match = true;
      int i = 0;
      while ((match == true) && (i<8))
      {
         if (!answer.get(i).equals(subsets.get(i)))
         {
            match = false;
         }
         i++;
      }
      if (match == true)
      {
         System.out.println("YAY");
      }
      else
      {
         System.out.println("BOO");
         for(i = 0; i < subsets.size(); i++)
         {
            System.out.println(subsets.get(i));
         }
      }
   }
}
