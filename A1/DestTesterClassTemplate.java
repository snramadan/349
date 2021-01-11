
import java.util.ArrayList;
// Feel free to add any other imports

public class DestTesterClassTemplate {  //need to change the class name
   /**
    * Expects to be returned an ArrayList<Integer> that has these in the following order:
    * 0 : height of the ladder (input parameter)
    * 1 : actual highest safe run (input parameter)
    * 2 : Highest safe rung determined by this algorithm
    * 3 : Total number of drops required to find the highest safe rung
    * 4 : The first rung from which the device was dropped
    * 5 : The second rung from which the device was dropped
    * 6 : The third rung from which the device was dropped
    * 7 : Rung where the first test device broke
    * 8 : Rung where the second test device broke
*/

   public static void main(String[] args) {

      ArrayList<Integer> result = new ArrayList<Integer>();
      DestructiveTest destTest = new DestructiveTest();

      result = destTest.findHighestSafeRung(100, 97);

      System.out.print("Test 1");
      System.out.println(" called with arguments (100, 97)   " );
      System.out.println("program computes: ");

      System.out.println("    ladder     safe   my safe  totdrps    drop1    drop2    drop3   dev1brk  dev2brk");
      for (int i = 0; i < result.size(); i++) {
         String numberAsString = String.format("% 9d", result.get(i));
                     //System.out.print(result.get(i));
         System.out.print(numberAsString);
      }
      System.out.println();
   }
}
