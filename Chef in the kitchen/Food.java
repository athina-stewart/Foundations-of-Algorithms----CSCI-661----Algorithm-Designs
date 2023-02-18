
import java.util.Scanner;
import java.util.PriorityQueue;

public class Food {

    static int numFood = 0;
    static int placeInFoodArray = 0;
    public static int[][] readInput(){
        // this method reads the file and stores the first value as the number
        // of food items being stored. The day that each item is added, along
        // with its expiration date is stored in a nested array
        Scanner sc = new Scanner(System.in);
        numFood = Integer.parseInt(sc.nextLine());
        int[][] inputArray = new int[numFood][2];
        for (int i = 0; i < numFood; i++) {
            for (int j = 0; j < 2; j++) {
                inputArray[i][j] += sc.nextInt();
            }
        }
        return inputArray;
    }

    public static String checkAllFood() {
        int[][] foodArray = readInput();
        int currentDay = 0; // the current day
        int newDay = 0; // the new day
        PriorityQueue<Integer> heapForTheDay = new PriorityQueue<>();

        // this variable marks what item is currently being checked in the food
        // array. This is important to avoid having to check the whole length
        // of the food for every iteration of the while loop
        placeInFoodArray = 0;

        while (placeInFoodArray != numFood){

            // this count variable records how many food items are added on the
            // current day
            int count = 0;

            // for loop to check if the day the item is added matches the current
            // day
            for (int i = placeInFoodArray; i < foodArray.length; i++){
                if ((currentDay - foodArray[i][0]) == 0){
                    count++;
                } else {
                    newDay = currentDay + ((foodArray[i][0]) - currentDay);
                    break;
                }
            }

            // the items starting from the place in the food array up to the
            // current place plus the count are added to th heap. The heap
            // is a priority queue and with each addition, the min-heap
            // structure is preserved. Importantly, the food item is added
            // as its expiry plus the day that is added. This way, there is
            // no need to decrement the expiration values and instead compare
            // the value in the heap with the current day to see if the item
            // is expired.
            for (int i = placeInFoodArray; i < placeInFoodArray + (count); i++){
                heapForTheDay.add(foodArray[i][1] + (currentDay));
            }

            // updating the current place int the food array
            placeInFoodArray = placeInFoodArray + (count);

            // the highest priority element (the food the smallest expiration
            // is removed from the min heap
            if (!heapForTheDay.isEmpty()) {
                heapForTheDay.poll();
            }

            // check if the minimum element is equal to the day, once there are
            // elements in the heap. This step is skipped if the stack is
            // currently empty but there are still items in the food array.
            if (!heapForTheDay.isEmpty() && heapForTheDay.peek() == (currentDay + 1)) {
                return "NO";
            } else {
                currentDay = newDay;
            }
        }

        // When all food items have been added, it must also be checked that
        // the items still in the heap can be used before they expire.
        while (!heapForTheDay.isEmpty()){
            currentDay++;
            if (heapForTheDay.peek() == currentDay){
                return "NO";
            }
            heapForTheDay.poll();
        }
        return "YES";
    }

    public static void main(String[] args) {
        System.out.println(checkAllFood());
    }
}

