
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class OneMatch {
    static int size = 0;

    public static int[] findMatches(int[][] preferenceList, int setOneStart, int setTwoStart) {
        // create stack of all the askers
        Stack<Integer> stk = new Stack<>();
        for (int i = size - 1; i > -1 ; i--) {
            stk.push(i);
        }

        // array holding default value for each preference of the asker
        // this array if used to check if the asker has already asked
        // a specific responder
        int[] currentAsked = new int[size];

        //array holding the match for the askers
        int[] isAskerMatched = new int[size];
        Arrays.fill(isAskerMatched, -1);

        //array holding the match for responders
        int[] isResponderMatched = new int[size];
        Arrays.fill(isResponderMatched, -1);

        // create inverse preference list for responders
        int[][] inverseResponderPreferenceList = new int[size][size];
        int m = 0;
        for (int j = setTwoStart; j < setTwoStart + (size); j++) {
            for (int k = 0; k < size; k++) {
                int currentValue = preferenceList[j][k];
                inverseResponderPreferenceList[m][currentValue] = k;
            }
            m++;
        }

        while (!stk.empty()) {
            // find the next free asker
            int freeAsker = stk.pop();

            // access the preference list of the asker and find the next
            // available responder then check if the responder is matched
            int[] askerPreferenceList = preferenceList[freeAsker + setOneStart];
            int nextAsked = askerPreferenceList[currentAsked[freeAsker]];
            if (isResponderMatched[nextAsked] == -1) {
                isResponderMatched[nextAsked] = freeAsker;
                isAskerMatched[freeAsker] = nextAsked;
            } else {
                // the responder is already matched, so we see how high the
                // responder rates the asker to determine if the responder
                // should upgrade
                int currentMatch = isResponderMatched[nextAsked];
                if (inverseResponderPreferenceList[nextAsked][freeAsker] <
                        inverseResponderPreferenceList[nextAsked][currentMatch]) {
                    isResponderMatched[nextAsked] = freeAsker;
                    isAskerMatched[freeAsker] = nextAsked;
                    stk.push(currentMatch);
                } else {
                    stk.push(freeAsker);
                }
            }
            currentAsked[freeAsker]++;
        }
        int[] result = new int[2 * size];
        System.arraycopy(isAskerMatched, 0, result, 0, size);
        System.arraycopy(isResponderMatched, 0, result, size, size);
        return(result);
    }

    public static int[][] readInput(){
        Scanner sc = new Scanner(System.in);
        size = Integer.parseInt(sc.nextLine());
        int[][] inputArray = new int[2 * size][size];
        for (int i = 0; i < 2 * size; i++) {
            for (int j = 0; j < size; j++) {
                inputArray[i][j] += sc.nextInt();
            }
        }
        return inputArray;
    }

    public static void compareMatches(int[] firstResult, int[] secondResult){
        int counter = 0;
        for (int i = 0; i < size; i ++) {
            if (firstResult[i] == secondResult[size + i]) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        int[][] list = readInput();
        int[]resultOne = (findMatches(list, 0, size));
        int[]resultTwo = (findMatches(list, size, 0));

        compareMatches(resultOne, resultTwo);
    }
}
