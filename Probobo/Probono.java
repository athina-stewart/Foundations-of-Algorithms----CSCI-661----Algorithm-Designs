/**
 * CSCI-665: FOUNDATIONS OF ALGORITHMS
 * Assignment 4, Question 2.2
 * This program uses an O(nlogn) algorithm to determine if given a
 * set of jobs with start time, end time and pro bono potential,
 * the maximum number of jobs that can be undertaken given that one
 * job at minimum must be pro bono
 * Authors:
 * Katyani Mehra, km7872@rit.edu
 * Athina Stewart, as1896@rit.edu
 * **/
import java.util.*;
import java.util.Comparator;

public class Probono {

    /**
     * This class is used to create a job that can be sorted by end time
     * **/
    public static class Job implements Comparable<Job>{
        int start;
        int end;
        int isProbono;

        // constructor for job
        public Job (int start, int end, int isProbono){
            this.start = start;
            this.end = end;
            this.isProbono = isProbono;
        }

        @Override
        public int compareTo(Job J) {
            int result = 0;
            if (this.end != J.end){
                // if end time is not equal, sort by end time
                if (this.end < J.end){
                    result = -1;
                } else {
                    result = 1;
                }
            }
            else{
                // if end time is equal, sort by start time
                if (this.start < J.start){
                    result = -1;
                } else if (this.start > J.start){
                    result = 1;
                } else{
                    return 0;
                }
            }
            return result;
        }

        @Override
        public String toString(){
            return "start: " + this.start + ", end: " + this.end + ", isProbono: "
                    + this.isProbono + ".";
        }
    }

    /**
     * This class is used to override natural sorting order which provides
     * an alternate way of sorting the jobs (by start time)
     * **/
    public static class JobSecondOrder implements Comparator<Job> {

        @Override
        public int compare(Job o1, Job o2) {

            int result = 0;
            if (o1.start != o2.start){
                // if start time is not equal, sort by start time
                if (o1.start < o2.start){
                    result = -1;
                } else {
                    result = 1;
                }
            }
            else{
                // if end time is equal, sort by start time
                if (o1.end < o2.end){
                    result = -1;
                } else {
                    return 0;
                }
            }
            return result;
        }
    }

    static int numJobs = 0;

    /**
     * This method reads the file and stores the first value as the number
     * of jobs items being considered. Each job is then created as an object
     * with its start time, end time and pro bono potential stored
     * **/
    public static List<Job> readInput(){
        Scanner sc = new Scanner(System.in);
        numJobs = Integer.parseInt(sc.nextLine());
        List<Job> allJobs = new LinkedList<>();
        for (int i = 0; i < numJobs; i++){
            String current = sc.nextLine().strip();
            String[] currentJob = current.split(" ");
            int start = Integer.parseInt(currentJob[0]);
            int end = Integer.parseInt(currentJob[1]);
            int isProbono = Integer.parseInt(currentJob[2]);
            Job newJob = new Job(start, end, isProbono);
            allJobs.add(newJob);
        }
        return allJobs;
    }

    /**
     * This method finds the maximum number of jobs that can be taken without
     * considering the pro bono potential of the jobs. This greedy solution is
     * calculated on jobs sorted by their end time
     * **/
    private static Job[] greedyInterval(Job[] jobs){
        Job[] result = new Job[numJobs];
        int index = 0;
        // create dummy job
        Job previousJob = new Job(Integer.MAX_VALUE, -Integer.MAX_VALUE, 0);
        for (int i = 0; i < numJobs; i++){
            if (jobs[i].start >= previousJob.end){ // check if not overlapping
                result[index] = jobs[i];
                previousJob = jobs[i];
                index++;
            }
        }
        return result;
    }

    /**
     * This method finds the maximum number of jobs that can be taken without
     * considering the pro bono potential of the jobs. This greedy solution is
     * calculated on jobs sorted by their start time
     * **/
    private static Job[] greedyIntervalReverse(Job[] jobs){
        Job[] result = new Job[numJobs];
        int index = 0;
        // create dummy job
        Job previousJob = new Job(Integer.MAX_VALUE, -Integer.MAX_VALUE, 0);
        for (int i = numJobs - 1; i > -1; i--){
            if (jobs[i].end <= previousJob.start){ // check if not overlapping
                result[index] = jobs[i];
                previousJob = jobs[i];
                index++;
            }
        }
        for(int i = 0; i < numJobs / 2; i++) {
            // Swapping the elements
            Job j = result[i];
            result[i] = result[numJobs - i - 1];
            result[result.length - i - 1] = j;
        }

        return result;
    }

    /**
     * Given the greedy solution done on jobs sorted by end time, this method uses a
     * modified version of binary search to determine for each pro bono job, how many
     * jobs from the greedy solution can be undertaken before the given pro bono job
     * **/
    static int compatibleJobSearch1(Job[] greedySolution, Job probonoJob, int low, int high){
        int mid;
        if (low > high){
            return -1;
        }
        else {
            mid = (low + high) / 2;
            if (probonoJob.start >= greedySolution[mid].end) {
                for (int i = 0; i < greedySolution.length; i++){
                    if (greedySolution[mid].end > probonoJob.start){
                        break;
                    } else {
                        mid++;
                    }
                }
                return mid - 1;
            } else {
                return compatibleJobSearch1(greedySolution, probonoJob, low, mid - 1);
            }
        }
    }

    /**
     * Given the greedy solution done on jobs sorted by start time, this method uses a
     * modified version of binary search to determine for each pro bono job, how many
     * jobs from the greedy solution can be undertaken after the given pro bono job
     * **/
    static int compatibleJobSearch2(Job[] greedySolution, Job probonoJob, int low, int high){
        int mid;
        if (low > high){
            return greedySolution.length;
        }
        else {
            mid = (low + high) / 2;
            if (probonoJob.end <= greedySolution[mid].start) {
                for (int i = 0; i < greedySolution.length; i++){
                    if (greedySolution[mid].start < probonoJob.end){
                        break;
                    } else {
                        mid--;
                    }
                }
                return mid + 1;
            } else{
                return compatibleJobSearch2(greedySolution, probonoJob, mid + 1, high);
            }
        }
    }

    /**
     * This is the driver code for this program. Here the jobs are sorted by end and
     * start time. The pro bono jobs are collected in an array. If there are no pro
     * bono jobs to be considered then a value of 0 is simply returned. Else, the
     * greedy solutions are calculated and for each pro bono job, the compatible
     * greedy jobs are determined. The sum of compatible jobs plus the greedy
     * jobs are returned.
     * **/
    public static int findMaxJobs(List<Job> jobs) {
        // sort jobs on end time
        Collections.sort(jobs);
        // add jobs to array
        Job[] sortedByEndTime = jobs.toArray(new Job[0]);

        // sort jobs on start time
        jobs.sort(new JobSecondOrder());
        // add jobs to array
        Job[] sortedByStartTime = jobs.toArray(new Job[0]);

        // get pro bono jobs
        Job[] probonoJobs = new Job[numJobs];
        int count = 0;
        for (int i = 0; i < numJobs; i++){
            if (sortedByEndTime[i].isProbono == 1){
                probonoJobs[count] = sortedByEndTime[i];
                count++;
            }
        }
        // remove 'null' elements
        Job[] temp = new Job[count];
        System.arraycopy(probonoJobs, 0, temp, 0, count);
        probonoJobs = temp;

        if (probonoJobs.length == 0){
            return 0; // we must have at least one pro bono job
        } else {
            // compute greedy solution when sorted by end time and then start time
            // remove null values from list
            Job[] greedySolution1 = greedyInterval(sortedByEndTime);
            List<Job> list = new ArrayList<>(Arrays.asList(greedySolution1));
            list.removeAll(Collections.singleton(null));
            greedySolution1 = list.toArray(new Job[0]);

            Job[] greedySolution2 = greedyIntervalReverse(sortedByStartTime);
            List<Job> list1 = new ArrayList<>(Arrays.asList(greedySolution2));
            list1.removeAll(Collections.singleton(null));
            greedySolution2 = list1.toArray(new Job[0]);

            // iterate through pro bono jobs and fix one at time. Then use binary
            // search to find compatible job
            int[] jobCounts = new int[probonoJobs.length];
            for (int j = 0; j < probonoJobs.length; j++) {
                int compatibleIndex1 = compatibleJobSearch1(greedySolution1,
                        probonoJobs[j], 0, greedySolution1.length - 1);
                int compatibleIndex2 = compatibleJobSearch2(greedySolution2,
                        probonoJobs[j], 0,
                        greedySolution2.length - 1);
                int currentJobCount = 1 + compatibleIndex1 + 1 + (greedySolution1.length
                        - compatibleIndex2);
                jobCounts[j] = currentJobCount;
            }

            // Initialize maximum element
            int max = jobCounts[0];

            // Traverse array elements from second and
            // compare every element with current max
            for (int i = 1; i < jobCounts.length; i++) {
                if (jobCounts[i] > max) {
                    max = jobCounts[i];
                }
            }

            return max;
        }
    }

    /**
     * This is the main method.
     * **/
    public static void main(String[] args) {
        System.out.println(findMaxJobs(readInput()));
    }

}
