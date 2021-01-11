/* 
 * Saba Ramadan
 */

import java.util.* ;
import java.io.* ;

public class buildGraphs
{

    public static void main(String[] args) throws IOException
    {
       try
       {
          String input = "wordgame_20170721.csv";

          if (args.length > 0)
          {
              input = args[0];
          }

          File file = new File(input);
          Scanner scan = new Scanner(file).useDelimiter(", ");
          String line = "";
          String word1 = "";
          String word2 = "";

          File output1 = new File("undirectedGraph.csv") ;
          FileWriter undir = new FileWriter(output1) ;
          File output2 = new File("directedGraph.csv") ;
          FileWriter dir = new FileWriter(output2) ;
 
          String[][] array = new String[334037][334037];
          int i = 0; 
          int j = 0;
          int h = 0;
          String temp;
          boolean temp2 = true;
          boolean contain1 = false;
          boolean contain2 = false;

          //test if word1 is phrase
          line = scan.nextLine();
          while (scan.hasNextLine())
          {
              line = scan.nextLine();
              //skip author ID to get word1
              word1 = scan.next();   
              word2 = scan.next(); 
              
              for(int g = 0; g < word1.length(); g++)
              {
                 if (word1.charAt(g) == ' ')
                 {
                      contain1 = true;
                 }
              }
              for(int g = 0; g < word2.length(); g++)
              {
                 if (word2.charAt(g) == ' ')
                 {
                      contain2 = true;
                 }
              }
              if (contain1 == false && contain2 == false)
              {
                  //first check if array is empty
                  if (array.length == 0)
                  {
                     array[i][0] = word1;
                     array[i][1] = word2;
                  }
                  else
                  { 
                    //first check if word1 is already in array
                    //if is in there then place word2 in the row 
                    while ((array[h][0] != null) && (temp2 == true))   
                    {
                      temp = array[h][0];

                      if (temp.equals(word1))
                      {
                          for (int x = 0; x < array.length; x++)
                          {
                              if (array[h][x] == null)
                              {
                                  array[h][x] = word2;
                                  temp2  = false;
                              }
                          }
                      }
                      //once we know word1 isnt in array we add it
                      //then we check where the first available spot is to put word2
                      //then we put word2 in array
                      if (array[h+1][0] == null)
                      {
                          array[i][0] = word1; 
                          for (int y = 0; y < array.length; y++)
                          {
                              if (array[i][y] == null)
                              {
                                  array[i][y] = word2;
                                  temp2  = false;
                              }
                          }
                      }
                      h++;
                    }
                  }
              }  
              i++;    
          }
     
          //making an undirected array
          String[][] arr2 = Arrays.copyOf(array, array.length);
          int w = 0;
	  int z = 0;
          int y = 0;
          int v = 1;
          String main, hold, hold2;
          temp2 = true;
          while (arr2[w][0] != null)
          {
              main = arr2[w][0];
              hold = arr2[w][z];
              //checking to see if hold is already in the array at colomn zero
              while (arr2[w][0] != null)
              {
                  hold2 = arr2[w][y];
                  if (hold2.equals(hold))
                  {
                      //checking if row contains word1
                      while ((arr2[w][v] != null) && (temp2 = true))
                      {
                         if ((arr2[w][v]).equals(main))
                         {
                             temp2 = false;
                         }
                         if (arr2[w][v+1] == null)
                         {
                             arr2[w][v+1] = main;
                             temp2 = false;
                         }
                         v++;
                      }
                  }
                  y++;
              }
              w++;
          }


          //putting undirected array in undirected graph file
          for (int f = 0, s = 0; f < arr2.length; f++)
          {
              while (arr2[f][s] != null)
              {
                  undir.write(arr2[f][s] + " ");
                  s++;
              }
              undir.write("\n");
          }

          //putting array in directed graph file
          for (int f = 0, s = 0; f < array.length; f++)
          {
              while (array[f][s] != null)
              {
                  dir.write(array[f][s] + " ");
                  s++;
              }
              dir.write("\n");
          } 

          scan.close();
          undir.close();
          dir.close();
       }
   
       catch(IOException ex)
       { 
          System.out.println("Error reading file 'wordgame_20170721.csv'") ;
       }
    }
}
