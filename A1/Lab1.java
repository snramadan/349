// Lab 1

public class Lab1
{
  public static int[][] iterativeMM(int[][] A, int[][] B)
  {     
    int s;
    int n = A.length;
    int[][] C = new int[n][n]; 
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int c = 0;
            for (s = 0; s < n; s++)
            {
               c = c + A[i][s] * B[s][j];
            }
            C[i][j] = c;
        }
    } 
    return C;
  }

  public static int[][] dividenConquer(int[][] A, int[][] B)
  {
    int n = A.length;  
 
    if (n == 1)
    {
        int[][] C = new int[1][1];
        C[0][0] = A[0][0] * B[0][0];
        return C;
    } 
    
    int[][] X = new int[n/2][n/2];
    int[][] Y = new int[n/2][n/2];
    int[][] Z = new int[n/2][n/2];
    int[][] W = new int[n/2][n/2];
    int[][] P = new int[n/2][n/2];
    int[][] Q = new int[n/2][n/2];
    int[][] R = new int[n/2][n/2];
    int[][] S = new int[n/2][n/2];

    matrixDivide(A, X, 0, 0);
    matrixDivide(A, Y, 0, n/2);
    matrixDivide(A, Z, n/2, 0);
    matrixDivide(A, W, n/2, n/2);    

    matrixDivide(B, P, 0, 0);
    matrixDivide(B, Q, 0, n/2);
    matrixDivide(B, R, n/2, 0);
    matrixDivide(B, S, n/2, n/2);

    int[][] XP = dividenConquer(X, P);
    int[][] XQ = dividenConquer(X, Q);
    int[][] YR = dividenConquer(Y, R);  
    int[][] YS = dividenConquer(Y, S);
    int[][] ZP = dividenConquer(Z, P);
    int[][] ZQ = dividenConquer(Z, Q);
    int[][] WR = dividenConquer(W, R);
    int[][] WS = dividenConquer(W, S);

    int[][] C1 = matrixAdd(XP, YR);
    int[][] C2 = matrixAdd(XQ, YS);
    int[][] C3 = matrixAdd(ZP, WR);
    int[][] C4 = matrixAdd(ZQ, WS);

    int[][] C = new int[n][n];

    matrixJoin(C, C1, 0, 0);
    matrixJoin(C, C2, 0, n/2);
    matrixJoin(C, C3, n/2, 0);
    matrixJoin(C, C4, n/2, n/2);
  
    return C;
  }
  
  public static int[][] strassensMM(int[][] A, int[][] B)
  {
    int n = A.length;    

    if (n == 1)
    {
        int[][] C = new int[1][1];
        C[0][0] = A[0][0] * B[0][0];
        return C;
    }

    int[][] X = new int[n/2][n/2];
    int[][] Y = new int[n/2][n/2];
    int[][] Z = new int[n/2][n/2];
    int[][] W = new int[n/2][n/2];
    int[][] P = new int[n/2][n/2];
    int[][] Q = new int[n/2][n/2];
    int[][] R = new int[n/2][n/2];
    int[][] S = new int[n/2][n/2];

    matrixDivide(A, X, 0, 0);
    matrixDivide(A, Y, 0, n/2);
    matrixDivide(A, Z, n/2, 0);
    matrixDivide(A, W, n/2, n/2);

    matrixDivide(B, P, 0, 0);
    matrixDivide(B, Q, 0, n/2);
    matrixDivide(B, R, n/2, 0);
    matrixDivide(B, S, n/2, n/2);
   
    int[][] P1 = strassensMM(X, matrixSub(Q, S));
    int[][] P2 = strassensMM(matrixAdd(X, Y), S);
    int[][] P3 = strassensMM(matrixAdd(Z, W), P);
    int[][] P4 = strassensMM(W, matrixSub(R, P));
    int[][] P5 = strassensMM(matrixAdd(X, W), matrixAdd(P, S));
    int[][] P6 = strassensMM(matrixSub(Y, W), matrixAdd(R, S));
    int[][] P7 = strassensMM(matrixSub(X, Z), matrixAdd(P, Q));

    int[][] C1 = matrixSub(matrixAdd(P5, P4), matrixAdd(P2, P6));
    int[][] C2 = matrixAdd(P1, P2);
    int[][] C3 = matrixAdd(P3, P4);
    int[][] C4 = matrixSub(matrixAdd(P1, P5), matrixSub(P3, P7));


    int[][] C = new int[n][n];

    matrixJoin(C, C1, 0, 0);
    matrixJoin(C, C2, 0, n/2);
    matrixJoin(C, C3, n/2, 0);
    matrixJoin(C, C4, n/2, n/2);

    return C;
  }

  /*
   * Helper Methods
   */

  private static int[][] matrixAdd(int[][] A, int[][] B)
  {
    //Assume A[n][n] & B[n][n]
    int n = A.length;
    int[][] C = new int[n][n];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
           C[i][j] =  A[i][j] + B[i][j];
        }
    }
    return C;
  }
 
  private static int[][] matrixSub(int[][] A, int[][] B)
  {
    //Assume A[n][n] & B[n][n]
    int n = A.length;
    int[][] C = new int[n][n];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
           C[i][j] =  A[i][j] - B[i][j];
        }
    }
    return C;
  }

  private static void matrixDivide(int[][] I, int[][] O, int r, int s)
  {
    int n = O.length;
    for (int i = 0, i2 = r; i < n; i++, i2++)
    {
        for (int j = 0, j2 = s; j < n; j++, j2++)
        {
            O[i][j] = I[i2][j2];
        }
    }
  }

  private static void matrixJoin(int[][] C, int[][] Ci, int r, int s)
  {
    int n = Ci.length;
    for (int i = 0, i2 = r; i < n; i++, i2++)
    {
        for (int j = 0, j2 = s; j < n; j++, j2++)
        {
            C[i2][j2] = Ci[i][j];
        }
    }
  }
}
