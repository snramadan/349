/*
 * Saba Ramadan
 */

import java.util.*;
import java.io.*;

public class Degrees
{
   public static void main(String[] args) throws IOException
   {
        try
        {
            String input = "directedGrpah.csv";
   
            if (args.length > 0)
            {
               input = args[0];
            }

            File f = new File(input);
            Scanner s = new Scanner(f).useDelimiter(" ");
            Scanner S = new Scanner(f).useDelimiter(" ");
            Scanner S2 = new Scanner(f).useDelimiter(" ");
            String s1 = "";
            String s2 = "";
            int i = 0;
            int outdegree = 0;            
            String[] words = new String[334037];
            int[] degs = new int[334037];

            //indegree calc
            //make array of words
            while (S.hasNextLine())
            {
                s1 = S.nextLine();
                s2 = S.next();
                words[i] = s2;
                i++;
            }

            s1 = "";
            s2 = "";
            String temp = "";
            //do the indegree check
            while (S2.hasNextLine())
            {
                s1 = S2.nextLine();
                //ignore first word
                s2 = S2.next();
                while (S2.hasNext())
                {
                   temp = S2.next();
                   //add to the degs array the number of times a word has an indegree
                   for (int j = 0; j < words.length; j++)
                   {
                       if ((words[j]).equals(temp))
                       {
                           degs[j] = degs[j] + 1;
                       }
                   }
                }
            }

            s1 = "";
            s2 = "";

            //outdegree calc
            int d = 0;
            System.out.println("Directed Graph Values: \n");
            while (s.hasNextLine())
            {
               s1 = s.nextLine();
               s2 = s.next();
               outdegree = 1;
               String t = s2;
               while (s.hasNext())
               {
                  s2 = s.next();
                  outdegree++;
               }
               System.out.println(t + " InDegree: " + degs[d] + " OutDegree: " + outdegree + "\n");
               d++;
            }

            s.close();
            S.close();
            S2.close();
        }
 
        catch (IOException e)
        {
            System.out.println("Error reading file 'directedGraph.csv'");
        }

        try
        {
            String input = "undirectedGrpah.csv";

            if (args.length > 0)
            {
               input = args[0];
            }

            File file = new File(input);
            Scanner scan = new Scanner(file).useDelimiter(" ");
            String s = "";
            String s2 = "";
            int vertex = 0;
   
            System.out.println("Vetices Degree for UnDirected: \n");
            while (scan.hasNextLine())
            {
               s = scan.nextLine();
               s2 = scan.next();
               vertex = 1;
               String temp = s2;
               while (scan.hasNext())
               {
                  s2 = scan.next();
                  vertex++;
               }
               System.out.println(temp + " " + vertex + " vertices\n");
            }
            
            scan.close();
        }

        catch (IOException e)
        {
            System.out.println("Error reading file 'undirectedGraph.csv'");
        }
   }
}
