import java.lang.*;
import java.util.*;

public class MyHeap 
{
   private int[] heap;
   private int size;

   public MyHeap() 
   {
      heap = new int[51];
      size = 0;
   }

   public MyHeap(int n) 
   {
      heap = new int[n+1];
      size = 0;
   }

   public boolean buildHeap(int[] el) 
   {
      int i;

      if(el.length > getHeapCap()) 
      {
         return false;
      }

      size = el.length;

      for(i = 0; i < el.length; i++) 
      {
         heap[i+1] = el[i];
      }
 
      for(i = size/2; i >= 1; i--) 
      {
         driftDown(i);
      }
		
      return true;
   }

   public int[] heapContents() 
   {
      int[] heapArr = new int[size];
      int i;

      for(i = 1; i <= size; i++) 
      {
         heapArr[i-1] = heap[i];
      }
	
      return heapArr;
   }

   public boolean insert(int x) 
   {
      if (isFull()) 
      {
         return false;
      }

      heap[++size] = x;

      driftUp(size);
		
      return true;
   }

   public int findMin() 
   {
      return heap[size-1];
   }

   public int deleteMin() 
   {
      int e = heap[size-1];

      heap[size-1] = heap[size--];
      driftUp(1);

      return e;
   }
   
   public boolean isEmpty() 
   {
      return size == 0;
   }

   public boolean isFull() 
   {
      return (size + 1 == heap.length);
   }

   public int getHeapCap() 
   {
      return heap.length - 1;
   }

   public int getHeapSize() 
   {
      return size;
   }

   public int[] heapSortDecreasing(int[] heapArr) 
   {
      int[] d = new int[heapArr.length];
      int i;

      if (d.length <= 1) 
      {
         return heapArr;
      }

      for (i = 0; i < heapArr.length; i++) 
      {
         maxHeapify(heapArr);
	 d[i] = heapArr[0];
	 heapArr[0] = Integer.MIN_VALUE;
      }

      return d;	
   }
   
   public void maxHeapify(int[] heapArr) 
   {
      int c = heapArr.length;
      int l;

      if (c <= 1) 
      {
         return;
      }
	   
      l = 1;
	   
      while (l < c) 
      {
         siftUp(heapArr, 0, l);
	 l++;
      }
   }

   public void siftUp(int[] heapArr, int start, int end) 
   {
      int l = end;
      int p = (l - 1) / 2;
      
      while (p >= start) 
      {
         if (heapArr[l] > heapArr[p]) 
         {
            swap(heapArr, l, p);
            l = p;
            p = (l - 1) / 2;
	 } 

         else 
         {
            return;
	 }
      }
   }
 
   public void swap(int[] heapArr, int x, int y) 
   {
      int temp = heapArr[x];
      heapArr[x] = heapArr[y];
      heapArr[y] = temp;
   }

   public void driftDown(int i) 
   {
      int ch;
      int first = heap[i];	
      while (2 * i <= size) 
      {
         ch = 2 * i;
         if (ch != size && heap[ch] < heap[ch + 1]) 
         {
            ch++;
         }
         if (first < heap[ch]) 
         {
            heap[i] = heap[ch];
         } 
         else 
         {
            break;
         }
         i = ch;
      }
     heap[i] = first;
   }

   public void driftUp(int i) 
   {
      int p = i / 2;
      int el = heap[i];

      while (i > 1 && heap[p] < el) 
      {
         heap[i] = heap[p];
         i = p;
         p = p/2;
      }

      heap[i] = el;
  }

}
