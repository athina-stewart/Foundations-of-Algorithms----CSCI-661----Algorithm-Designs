import java.util.*;

public class Colinear {

    static int numPoints = 0;

    public static int[][] readInput() {
        // this method reads the file and stores the first value as the number
        // of points being stored. The x nd y coordinate of each point is
        // stored in a nested array.
        Scanner sc = new Scanner(System.in);
        numPoints = Integer.parseInt(sc.nextLine());
        int[][] inputArray = new int[numPoints][2];
        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < 2; j++) {
                inputArray[i][j] += sc.nextInt();
            }
        }
        return inputArray;
    }

    public static int[][] sortPoints(){
        int[][] inputArray = readInput();
        Arrays.sort(inputArray, Comparator.comparingInt((int[] x) ->
                x[0]).thenComparingInt(x -> x[1]));
        return inputArray;
    }

    public static String checkAllPoints(){
        int[][] input = sortPoints();
        // get first point
        for (int i = 0; i < input.length; i++){
            double[][] slopes = new double[numPoints - 1][2];
            int[] firstPoint = input[i];
            // remove this point from the list
            int[][] newArray = new int[input.length - 1][2];
            System.arraycopy(input, 0, newArray, 0, i);
            System.arraycopy(input, i+1, newArray, i,
                    input.length - i - 1);
            // calculate slope of all other points to this point
            for (int j = 0; j < newArray.length; j++){
                int[] currentPoint = newArray[j];
                double slope = ((double)currentPoint[1] - (double)firstPoint[1]) /
                        ((double)currentPoint[0] - (double)firstPoint[0]);
                slopes[j][0] = slope;
                //calculate distance of each point from current point
                slopes[j][1] = Math.sqrt((currentPoint[1] - firstPoint[1]) *
                        (currentPoint[1] - firstPoint[1]) +
                        (currentPoint[0] - firstPoint[0]) *
                                (currentPoint[0] - firstPoint[0]));
            }
            Arrays.sort(slopes, Comparator.comparingDouble((double[] x) ->
                    x[0]).thenComparingDouble(x -> x[1]));
            for (int k = 0; k < slopes.length - 1; k++){
                if (slopes[k][0] == slopes[k+1][0]){
                    if (slopes[k][1] == slopes[k + 1][1]/2 || slopes[k + 1][1]
                            == slopes[k][1]/2) {
                        return ("YES");
                    }
                }
            }
        }
        return ("NO");
    }



    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
        System.out.println(checkAllPoints());
//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//        System.out.print(elapsedTime);

        // Runtime comparison

        // O(n^2logn) algorithm
        // test 1 = 880
        // test 2 = 843
        // test 3 = 1278
        // test 4 = 1187
        // test 5 = 2900

        // O(n^2) algorithm
        // test 1 = 977
        // test 2 = 873
        // test 3 = 902
        // test 4 = 1020
        // test 5 = 1435
    }
}


