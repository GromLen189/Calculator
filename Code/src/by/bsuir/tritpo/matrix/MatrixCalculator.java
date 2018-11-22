package by.bsuir.tritpo.matrix;


public class MatrixCalculator {

    public int[][] add (int[][] m1, int[][] m2) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        int[][] result = new int [columnNum][rowNum];
        for (int i = 0; i < columnNum; i++) {
           for (int j = 0; j < rowNum; j++) {
               result[i][j] = m1[i][j] + m2[i][j];
           }
        }
        return result;
    }

    public int[][] sub (int[][] m1, int[][] m2) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        int[][] result = new int [columnNum][rowNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    public int[][] mulNum (int[][] m1, int number) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        int[][] result = new int [columnNum][rowNum];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] * number;
            }
        }
        return result;
    }

    public int[][] mul (int[][] m1, int[][] m2) {
        int columnNum = m2.length;
        int rowNum = m1[0].length;
        int[][] result = new int[columnNum][rowNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                for (int z = 0; z < m2[0].length; z++) {
                    result[i][j] += m1[i][z] * m2[z][j];   //
                }
            }
        }
        return result;
    }

    public int[][] transpon (int[][] m1) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        int[][] result = new int[rowNum][columnNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                result[j][i] = m1[i][j];
            }
        }
        return result;
    }

    public double[][] revert (int[][] m1) {
        double temp;
        int size = m1.length;
        double[][] result = new double[size][size];
        if(determinant(m1) == 0) {return result;} ////
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               if(i == j) {
                   result[i][j] = 1;
               } else {
                   result[i][j] = 0;
               }
            }
        }
        for (int z = 0; z < size; z++) {
            temp = m1[z][z];
            for (int j = 0; j < size; j++) {
                m1[z][j] /= temp;
                result [z][j] /= temp;
            }
            for (int i = z + 1; i < size; i++) {
                temp = m1[i][z];
                for (int j = 0; j < size; j++) {
                    m1[i][j] -= m1[z][j] * temp;
                    result [i][j] -= result[z][j] * temp;
                }
            }
        }
        for (int z = size - 1; z > 0; z--) {
            for (int i = z - 1; i >= 0; i--) {
                temp = m1[i][z];
                for (int j = 0; j < size; j++) {
                    m1[i][j] -= m1[z][j] * temp;
                    result [i][j] -= result[z][j] * temp;
                }
            }
        }
        return result;
    }

    private double determinant(int m1[][]) {
        double det=0;
        int size = m1.length;
        if(size == 1)
        {
            det = m1[0][0];
        }
        else if (size == 2)
        {
            det = m1[0][0]*m1[1][1] - m1[1][0]*m1[0][1];
        }
        else
        {
            det=0;
            for(int j1=0;j1<size;j1++)
            {
                int[][] m = new int[size-1][];
                for(int k=0;k<(size-1);k++)
                {
                    m[k] = new int[size-1];
                }
                for(int i=1;i<size;i++)
                {
                    int j2=0;
                    for(int j=0;j<size;j++)
                    {
                        if(j == j1)
                            continue;
                        m[i-1][j2] = m1[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* m1[0][j1] * determinant(m);
            }
        }
        return det;
    }
}
