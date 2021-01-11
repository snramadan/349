//Lab 1

import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.Random;
import java.util.Arrays;
import org.junit.runners.MethodSorters;
import org.junit.rules.*;
import org.junit.runner.Description;
import java.util.concurrent.TimeUnit;

public class Lab1Test
{
     @Test
     public void test_multiply()
     {
         int[][] m1 = new int[][]{{1, 2},{-3, 4}};
         int[][] m2 = new int[][]{{-1, 3}, {0, 9}};
         int[][] res = new int[2][2];
         res = Lab1.iterativeMM(m1, m2);
         assertArrayEquals(new int[][]{{-1, 21}, {3, 27}}, res);
     }

     @Test
     public void test_multiply2()
     {
         int[][] m1 = new int[][]{{1, 2, 4, 5}, {-3, 4, 5, 6}, {4, 5, 6, 7}, {8, 9, 3, 2}};
         int[][] m2 = new int[][]{{-1, 3, 5, 7}, {0, 9, 4, 7}, {0, 5, 2, 4}, {6, 1, 5, 4}};
         assertArrayEquals(new int[][]{{29, 46, 46, 57}, {39, 58, 41, 51}, {38, 94, 87, 115}, {4, 122, 92, 139}}, Lab1.iterativeMM(m1, m2)); 
     }

     @Test
     public void test_multiply3()
     {   
         int[][] m1 = new int[][]{{1, 2, -3, 4, 4}, {5, 8, 9, -1, 3}, {0, 9, 0, 5, 6}, {1, 1, 2, -3, 4}, {4, 5, 8, 9, -1}};
         int[][] m2 = new int[][]{{1, 2, 3, 7, 8}, {5, 8, 5, -1, 3}, {4, 9, 1, 5, 6}, {1, 8, 2, 3, 4}, {4, 5, 3, 9, -1}};
         assertArrayEquals(new int[][]{{19, 43, 30, 38, 8}, {92, 162, 71, 96, 111}, {74, 142, 73, 60, 41}, {27, 24, 16, 43, 7}, {66, 187, 60, 81, 132}}, Lab1.iterativeMM(m1, m2));
     }

     @Test
     public void test_divandcon()
     {
         int[][] m1 = new int[][]{{1, 2}, {-3, 4}};
         int[][] m2 = new int[][]{{-1, 3}, {0, 9}};
         assertArrayEquals(new int[][]{{-1, 21}, {3, 27}}, Lab1.dividenConquer(m1, m2));
     }

     @Test
     public void test_divandcon2()
     {
        int[][] m1 = new int[][]{{1, 2, 4, 5}, {-3, 4, 5, 6}, {4, 5, 6, 7}, {8, 9, 3, 2}};
        int[][] m2 = new int[][]{{-1, 3, 5, 7}, {0, 9, 4, 7}, {0, 5, 2, 4}, {6, 1, 5, 4}};
        assertArrayEquals(new int[][]{{29, 46, 46, 57}, {39, 58, 41, 51}, {38, 94, 87, 115}, {4, 122, 92, 139}}, Lab1.dividenConquer(m1, m2));
     }

     @Test
     public void test_divandcon3()
     {
         int[][] m1 = new int[][]{{1, 2, -3, 4, 4}, {5, 8, 9, -1, 3}, {0, 9, 0, 5, 6}, {1, 1, 2, -3, 4}, {4, 5, 8, 9, -1}};
         int[][] m2 = new int[][]{{1, 2, 3, 7, 8}, {5, 8, 5, -1, 3}, {4, 9, 1, 5, 6}, {1, 8, 2, 3, 4}, {4, 5, 3, 9, -1}};
         assertArrayEquals(new int[][]{{19, 43, 30, 38, 8}, {92, 162, 71, 96, 111}, {74, 142, 73, 60, 41}, {27, 24, 16, 43, 7}, {66, 187, 60, 81, 132}}, Lab1.dividenConquer(m1, m2));
     }

     @Test
     public void test_strassen()
     {
        int[][] m1 = new int[][]{{1, 2}, {-3, 4}};
        int[][] m2 = new int[][]{{-1, 3}, {0, 9}};
        assertArrayEquals(new int[][]{{-1, 21}, {3, 27}}, Lab1.strassensMM(m1, m2));
     }

     @Test
     public void test_strassen2()
     {
        int[][] m1 = new int[][]{{1, 2, 4, 5}, {-3, 4, 5, 6}, {4, 5, 6, 7}, {8, 9, 3, 2}};
        int[][] m2 = new int[][]{{-1, 3, 5, 7}, {0, 9, 4, 7}, {0, 5, 2, 4}, {6, 1, 5, 4}};
        assertArrayEquals(new int[][]{{29, 46, 46, 57}, {39, 58, 41, 51}, {38, 94, 87, 115}, {4, 122, 92, 139}}, Lab1.strassensMM(m1, m2));
     }

     @Test
     public void test_strassen3()
     {
         int[][] m1 = new int[][]{{1, 2, -3, 4, 4}, {5, 8, 9, -1, 3}, {0, 9, 0, 5, 6}, {1, 1, 2, -3, 4}, {4, 5, 8, 9, -1}};
         int[][] m2 = new int[][]{{1, 2, 3, 7, 8}, {5, 8, 5, -1, 3}, {4, 9, 1, 5, 6}, {1, 8, 2, 3, 4}, {4, 5, 3, 9, -1}};
         assertArrayEquals(new int[][]{{19, 43, 30, 38, 8}, {92, 162, 71, 96, 111}, {74, 142, 73, 60, 41}, {27, 24, 16, 43, 7}, {66, 187, 60, 81, 132}}, Lab1.strassensMM(m1, m2));
     }

     /*@Test
     public void test_2()
     {
        Random rand = new Random();
        int[][] M1 = new int[2][2];
        int[][] M2 = new int[2][2];

        for (int i = 0; i <2; i++)
        {
           for (int j = 0; j <2; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("2 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("2 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("2 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_4()
     {
        Random rand = new Random();
        int[][] M1 = new int[4][4];
        int[][] M2 = new int[4][4];

        for (int i = 0; i <4; i++)
        {
           for (int j = 0; j <4; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }
        
        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("4 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("4 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("4 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_8()
     {
        Random rand = new Random();
        int[][] M1 = new int[8][8];
        int[][] M2 = new int[8][8];

        for (int i = 0; i <8; i++)
        {
           for (int j = 0; j <8; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("8 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("8 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("8 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_16()
     {
        Random rand = new Random();
        int[][] M1 = new int[16][16];
        int[][] M2 = new int[16][16];

        for (int i = 0; i <16; i++)
        {
           for (int j = 0; j <16; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("16 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("16 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("16 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_32()
     {
        Random rand = new Random();
        int[][] M1 = new int[32][32];
        int[][] M2 = new int[32][32];

        for (int i = 0; i <32; i++)
        {
           for (int j = 0; j <32; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("32 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("32 DivAndCon Time Test: " + (after1 - before1));

         long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("32 Strassens Time Test: " + (after2 - before2));

     }

     @Test
     public void test_64()
     {
        Random rand = new Random();
        int[][] M1 = new int[64][64];
        int[][] M2 = new int[64][64];

        for (int i = 0; i <64; i++)
        {
           for (int j = 0; j <64; j++)
           {
              //System.out.println("i =" + i + "j=" + j);
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("64 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("64 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("64 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_128()
     {
        Random rand = new Random();
        int[][] M1 = new int[128][128];
        int[][] M2 = new int[128][128];

        for (int i = 0; i <128; i++)
        {
           for (int j = 0; j <128; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("128 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("128 DivAndCon Time Test: " + (after1 - before1));
   
        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("128 Strassens Time Test: " + (after2 - before2));

     }
    
     @Test
     public void test_256()
     {
        Random rand = new Random();
        int[][] M1 = new int[256][256];
        int[][] M2 = new int[256][256];

        for (int i = 0; i <256; i++)
        {
           for (int j = 0; j <256; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("256 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("256 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("256 Strassens Time Test: " + (after2 - before2));
     }

     @Test
     public void test_512()
     {
        Random rand = new Random();
        int[][] M1 = new int[512][512];
        int[][] M2 = new int[512][512];

        for (int i = 0; i <512; i++)
        {
           for (int j = 0; j <512; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("512 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("512 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("512 Strassens Time Test: " + (after2 - before2));
     }*/

     @Test
     public void test_1024()
     {
        Random rand = new Random();
        int[][] M1 = new int[1024][1024];
        int[][] M2 = new int[1024][1024];

        for (int i = 0; i <1024; i++)
        {
           for (int j = 0; j <1024; j++)
           {
              M1[i][j] = rand.nextInt();
              M2[i][j] = rand.nextInt();
           }
        }

        long before = System.currentTimeMillis();
        Lab1.iterativeMM(M1, M2);
        long after = System.currentTimeMillis();
        System.out.println("1024 Iterative Time Test: " + (after - before));

        long before1 = System.currentTimeMillis();
        Lab1.dividenConquer(M1, M2);
        long after1 = System.currentTimeMillis();
        System.out.println("1024 DivAndCon Time Test: " + (after1 - before1));

        long before2 = System.currentTimeMillis();
        Lab1.strassensMM(M1, M2);
        long after2 = System.currentTimeMillis();
        System.out.println("1024 Strassens Time Test: " + (after2 - before2));
     }
}
