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
}
