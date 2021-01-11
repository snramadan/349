public class ArraySearch
{
   public static int[] search(int[][] currArray, int target)
   {
      int[] answer = new int[]{-1, -1};
      int row, col;
      boolean found = false;
      row = 0;
      col = 0;

      while (found == false && row < currArray.length)
      {
         for (col = 0; col < currArray[0].length; col++)
         {
            if (currArray[row][col] == target)
            {
               found = true;
               break;
            }
         }
         row++;
      }

      if (found == true)
      {
         answer[0] = row-1;
         answer[1] = col;
      }

      return answer;
   }
}
