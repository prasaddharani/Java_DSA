package main.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    public static void main(String[] args) {

    }

    /*
    54. Spiral Matrix

    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length;
        int left = 0, right = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        while (top < bottom && left < right) {
            // Move from top left to top right
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // Move from top right to bottom end
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            if (!(top < bottom && left < right)) {
                break;
            }

            // Move bottom right to bottom left
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;

            // Move left bottom to left top
            for (int i = bottom - 1; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }


    /*
    48. Rotate Image

    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [[7,4,1],[8,5,2],[9,6,3]]
     */

    public void rotate(int[][] matrix) {
        int left = 0, right = matrix.length - 1;
        while (left < right) {
            for (int i = 0; i < right - left; i++) {
                int top = left, bottom = right;

                // Save top left
                int topLeft = matrix[top][left + i];

                // Move bottom left to top left
                matrix[top][left + i] = matrix[bottom - i][left];

                // Move bottom right to bottom left
                matrix[bottom - i][left] = matrix[bottom][right - i];

                // Move top right to bottom right
                matrix[bottom][right - i] = matrix[top + i][right];

                // Move saved top left to top right
                matrix[top + i][right] = topLeft;
            }
            left++;
            right--;
        }
    }

    /*
    73. Set Matrix Zeroes

    Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
    Output: [[1,0,1],[0,0,0],[1,0,1]]
     */

    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        boolean firstRow = false, firstCol = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRow = true;
                    }
                    if (j == 0) {
                        firstCol = true;
                    }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
