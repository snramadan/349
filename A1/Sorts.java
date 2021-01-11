public class Sorts
{
   public static long insertSort(int[] data)
   {
       int count = 0;
       int temp;
       int i, j;
       
       for (i = 1; i < data.length; i++)
       {
           //inner for loop to help compare the numbers after the the selected index
           for (j = i; j > 0; j--)
           {
              if (data[j] < data[j-1])
              {
                 //swap the ints
                 temp = data[j]; 
                 data[j] = data[j-1];
                 data[j-1] = temp;
                 count++;
              }
           } 
       }

       return (long)count;
   }

   public static long selectSort(int[] data)
   {
      int count = 0;
      int temp, hold;
      int i, j;

      for (i = 0; i < data.length-1; i++)
      {
         hold = i;
         for (j = i+1; j < data.length; j++)
         {
            if (data[j] < data[hold])
            {
               hold = j;
            }
            count++;
         }
         //swap ints
         temp  = data[hold];
         data[hold] = data[i];
         data[i] = temp;
      }
    
      return (long)count;
   }


   public static int[] merge(int[] a, int[] b, int count)
   {
      int aS = a.length;
      int bS = b.length;
      int[] arr = new int[aS + bS];
      int i, j, k;
      i = 0;
      j = 0;

      for (k = 0; k < arr.length; k++)
      {
         if (i >=  aS)
         {
            arr[k] = b[j];
            j++;
         }
         else if (j >= bS)
         {
            arr[k] = a[i];
            i++;
         }
         else if (a[i] <= b[j])
         {
            arr[k] = a[i];
            i++;
         }
         else
         {
            arr[k] = b[j];
            j++;
         }
         
         count++;
      }


      return arr;
   }

   public static int[] mSort(int[] data, int count)   
   {
      int size = data.length;

      //create 2 arrays 
      int a[] = new int[size/2];
      int b[] = new int[size - size/2];
      int i, j;

      //fill out the copied arrays
      for (i = 0; i < a.length; i++)
      {
         a[i] = data[i];
      }
      for (j = 0; j < b.length; j++)
      {
         b[j] = data[j + size/2];
      }

      return merge(mSort(a, count), mSort(b, count), count); 
   }

   public static long mergeSort(int[] data)
   {
      int count = 0;
      int[] temp;

      if (data.length <= 1)
      {
         count = 0;
         return (long)count;
      }

      temp = mSort(data, count);

      return (long)count;
   }
}
