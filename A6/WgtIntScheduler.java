import java.util.*;
import java.lang.*;
import java.io.File;

public class WgtIntScheduler
{
   public static int[] getOptSet(int[] start, int[] end, int[] weight)
   {
      Sets[] sets = new Sets[start.length];
      int i, j;
      int[] jobs, times, opt;
      String[] S;

      for(i = 0; i < start.length; ++i)
      {
         sets[i] = new Sets(i + 1, start[i], end[i], weight[i]);
      }
		
      Arrays.sort(sets, new compSets());

      jobs = new int[start.length];

      for(i = 0; i < start.length; ++i)
      {
         jobs[i] = 0;

	 for(j = i - 1; j >= 0; --j)
	 {
	    if(sets[i].start >= sets[j].end)
	    {
	       jobs[i] = j + 1;
	       j = 0;
	    }
	 }
      }

      times = new int[start.length + 1];

      for(i = 1; i < start.length; ++i)
      {
         times[i] = Math.max(sets[i - 1].weight + times[jobs[i - 1]], times[i - 1]);
      }

      S = new StringBuilder(trace(start.length, sets, times, jobs)).reverse().toString().split(" ");

      opt = new int[S.length - 1];

      for(i = 0; i < opt.length; ++i)
      {
         opt[i] = Integer.parseInt(S[i + 1]);
      }

      Arrays.sort(opt);

      return opt;
   }

   private static String trace(int inc, Sets[] sets, int[] t, int[] jobs) 
   {
      int j;
      if(inc > 0)
      {
         j = jobs[inc - 1];

	 if((sets[inc - 1].weight + t[j]) >= t[inc - 1])
	 {
	    return (sets[inc - 1].job + " " + trace(j, sets, t, jobs));
	 }
	 else
	 {
	    return "" + trace(inc - 1, sets, t, jobs);
	 }
      }
      return "";
   }

   private static class Sets
   {
      int job;
      int start;
      int end;
      int weight;

      Sets(int job, int start, int end, int weight)
      {
         this.job = job;
	 this.start = start;
	 this.end = end;
	 this.weight = weight;
      }
   }

   public static class compSets implements Comparator<Sets>
   {
      public int compare(Sets x, Sets y)
      {
         if(x.end < y.end)
	 {
	    return -1;
	 }
	 else if(x.end > y.end)
	 {
	    return 1;
	 }
	 else
	 {
	    return 0;
	 }
      }
   }  
} 
