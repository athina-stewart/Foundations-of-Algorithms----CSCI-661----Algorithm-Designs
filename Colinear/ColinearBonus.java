import java.util.*;

public class ColinearBonus {

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

    public static String checkEvenlySpacedColinear() {
        int[][] allPoints = readInput();
        HashMap<List<Integer>, Integer> map = new HashMap<>();

        for (int i = 0; i < numPoints; i++) {
            for (int j = i+1; j < numPoints; j++) {
                int dx = allPoints[j][0] - allPoints[i][0];
                int dy = allPoints[j][1] - allPoints[i][1];
                int d = dx * dx + dy * dy;
                int gcd = gcd(dx, dy);
                List<Integer> key = Arrays.asList(dy / gcd, -dx / gcd, d);
                map.put(key, map.getOrDefault(key, 0) + 1);
                if (map.get(key) >= 2) {
                    return ("YES");
                }
            }
        }
        return "NO";
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(checkEvenlySpacedColinear());
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.print(elapsedTime);
    }
}
