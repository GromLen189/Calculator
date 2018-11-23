package by.bsuir.tritpo.matrix;

import by.bsuir.tritpo.exception.CustomException;

/**
 * this class contains ;ogic work with matrix.
 *
 * @author Milena Vysotskaya
 * @version 1.0
 * @since 2018-11-20
 */
public class MatrixCalculator {

    /**
     * Matrix addition (firstMatrix + secondMatrix)
     *
     * @param firstMatrix is 2d array of double matrix values.
     * @param secondMatrix is 2d array of double matrix values.
     * @return 2d array with each element value result[i][j] = firstMatrix[i][j] + secondMatrix[i][j].
     */
    public double[][] add(final double[][] firstMatrix, final double[][] secondMatrix) {
        int columnNum = firstMatrix.length;
        int rowNum = firstMatrix[0].length;
        double[][] result = new double[columnNum][rowNum];

        for (int i = 0; i < columnNum; i++) {
           for (int j = 0; j < rowNum; j++) {
               result[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
           }
        }
        return result;
    }

    public double[][] sub(double[][] m1, double[][] m2) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        double[][] result = new double[columnNum][rowNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    public double[][] mulNum(double[][] m1, double number) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        double[][] result = new double[columnNum][rowNum];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] * number;
            }
        }
        return result;
    }

    public double[][] mul(double[][] m1, double[][] m2) {
        int columnNum = m2[0].length;
        int rowNum = m1.length;
        double[][] result = new double[columnNum][rowNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                for (int z = 0; z < m2.length; z++) {
                    result[i][j] += m1[j][z] * m2[z][i];   //
                }
            }
        }
        return result;
    }

    public double[][] transpon(double[][] m1) {
        int columnNum = m1.length;
        int rowNum = m1[0].length;
        double[][] result = new double[rowNum][columnNum];
        for (int i = 0; i < columnNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                result[j][i] = m1[i][j];
            }
        }
        return result;
    }

    public double[][] invert(double[][] m1) throws CustomException {
        int size = m1.length;
        double temp;
        double[][] E = new double[size][size];

        if(Double.compare(determinant(m1), 0) == 0) {
           throw new CustomException("Invert m1 not exist");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                E[i][j] = 0f;
                if (i == j) {
                    E[i][j] = 1f;
                }
            }
        }

        for (int k = 0; k < size; k++) {
            temp = m1[k][k];
            for (int j = 0; j < size; j++) {
                m1[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < size; i++) {
                temp = m1[i][k];
                for (int j = 0; j < size; j++) {
                    m1[i][j] -= m1[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = size - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = m1[i][k];
                for (int j = 0; j < size; j++) {
                    m1[i][j] -= m1[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
        return E;
    }

    private double determinant(double m1[][]) {
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
                double[][] m = new double[size-1][];
                for(int k=0;k<(size-1);k++)
                {
                    m[k] = new double[size-1];
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

    public double[][] div(double m1[][], double m2[][]) throws CustomException {
        return  mul(m1, invert(m2));
    }
}
