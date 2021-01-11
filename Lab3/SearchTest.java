import static java.lang.System.out;

public class SearchTest
{ 
   public static void main(String[] args) 
   {   
      int[][] board1 = new int[][]{{1,2,9,9}, {3,6,12,13}, {7,10,13,29}, {10,11,28,30}, {14,15,30,35}};
      int target = 15;
      int[] answer = new int[2];
      answer = ArraySearch.search(board1,target);

      System.out.println(answer[0]); 
      System.out.println(answer[1]);
   }

}
