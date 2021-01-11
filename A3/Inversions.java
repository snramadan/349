public class Inversions 
{
   public static int invCounter(int [] arr) 
   {
      int [] temp = new int [arr.length];
      return mergeSort(arr, temp, 0, arr.length - 1);
    }
    
    private static int mergeSort(int [] arr, int [] temp, int l, int r) 
    {
       int c, iL, iR;
       if (l < r) 
       {
          c = (l + r) / 2;
          iL = mergeSort(arr, temp, l, c);
          iR = mergeSort(arr, temp, c + 1, r);
          return iL + iR + merge(arr, temp, l, c + 1, r);
       }
      
       return 0;
    }
    
    private static int merge(int [] arr, int [] temp, int l, int r, int x) 
    {
       int end = r - 1;
       int pos = l;
       int size = x - l + 1;
       int iC = 0; 
       int i;                                                                                       
    
       while (l <= end && r <= x) 
       {
          if (arr[l] < arr[r]) 
          {
             temp[pos++] = arr[l++];
          } 
          else 
          {
             temp[pos++] = arr[r++];
             iC += end + 1 - l;
          }
       }
                                                                                                                       while (l <= end) 
       {
          temp[pos++] = arr[l++];
       }

       while (r <= x) 
       {
          temp[pos++] = arr[r++];
       }
  
       for (i = 0; i < size; i++, x--) 
       {
          arr[x] = temp[x];
       }
                                                                                                                       return iC;
   }
}
